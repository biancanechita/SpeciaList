package ro.uaic.info.doctoravailabilitymanagementmicroservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DoctorAvailabilityManagementMicroserviceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFilterAndSortList() throws Exception {
        Map<String, String> filters = new HashMap<>();
        filters.put("fullName", "Andrei");
        List<SortExpression> sortingList = new ArrayList<>();

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setFilters(filters);
        queryOptions.setSortingList(sortingList);

        mockMvc.perform(MockMvcRequestBuilders.post("/doctors/filter-and-sort-list")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(queryOptions)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetAll() throws Exception {
        Map<String, String> filters = new HashMap<>();
        List<SortExpression> sortingList = new ArrayList<>();

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setFilters(filters);
        queryOptions.setSortingList(sortingList);

        mockMvc.perform(MockMvcRequestBuilders.post("/doctors/filter-and-sort-list")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsInVzZXJJZCI6MCwicm9sZSI6IkdBVEVXQVkifQ.I3_PxafH0k6yRuq0ARq6-34psRIszuLExcGsozXajHQ")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(queryOptions)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(27));
    }

}
