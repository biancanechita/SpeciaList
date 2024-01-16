package ro.uaic.info.apigateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.apigateway.services.AppointmentService;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.AvailableTimes;
import ro.uaic.info.appointmentschedulingservice.SchedulingParameters;

import java.util.List;

@RequestMapping("/appointments")
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class AppointmentSchedulingController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDto> findAllByPatientId() {
        return appointmentService.findAllByUserId();
    }

    @PostMapping("/fetch-available-times")
    public List<AvailableTimes> fetchAvailableTimes(@RequestBody SchedulingParameters schedulingParameters) {
        return appointmentService.fetchAvailableTimes(schedulingParameters);
    }

    @PostMapping("/restricted")
    public AppointmentDto save(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.save(appointmentDto);
    }

    @PostMapping
    public void scheduleVisitorMode(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.scheduleVisitorMode(appointmentRequest);
    }

    @PutMapping("/{id}")
    public AppointmentDto update(@RequestBody AppointmentDto appointmentDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteById(id);
    }
}
