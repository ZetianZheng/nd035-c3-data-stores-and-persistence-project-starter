package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.transfer.ScheduleTransfer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private ScheduleService scheduleService;
    private EmployeeService employeeService;
    private PetService petService;
    private ScheduleTransfer scheduleTransfer;
    private CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleTransfer.convertToSchedule(scheduleDTO);
        /** * id match 0(Long) - 1(JPA): **/
        Schedule JPASchedule = scheduleService.save(schedule);

        return scheduleTransfer.convertToScheduleDTO(JPASchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        List<ScheduleDTO>  scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getSchedulesByPet(petService.getPetById(petId));
        List<ScheduleDTO> scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployee(employeeService.getById(employeeId));
        List<ScheduleDTO>  scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCustomer(customerService.getById(customerId));
        List<ScheduleDTO>  scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }
}
