package ro.uaic.info.apigateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.uaic.info.apigateway.QueryOptions;
import ro.uaic.info.apigateway.services.AbstractService;

import java.util.List;

public abstract class AbstractController<T> {

    protected AbstractService<T> service;

    public void setService(AbstractService<T> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping("/filter-and-sort-list")
    public List<T> getAll(@RequestBody QueryOptions queryOptions) {
        return service.filterAndSortList(queryOptions);
    }

    @PostMapping
    public T create(@RequestBody T t) {
        return service.create(t);
    }
}
