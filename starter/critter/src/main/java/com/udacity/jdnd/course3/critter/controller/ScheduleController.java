package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.transfer.ScheduleTransfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final EmployeeService employeeService;
    private final PetService petService;
    private final ScheduleTransfer scheduleTransfer;
    private final CustomerService customerService;
    private Logger logger= LoggerFactory.getLogger(ScheduleController.class);

    public ScheduleController(ScheduleService scheduleService,
                              EmployeeService employeeService,
                              PetService petService,
                              ScheduleTransfer scheduleTransfer,
                              CustomerService customerService) {
        this.scheduleService = scheduleService;
        this.employeeService = employeeService;
        this.petService = petService;
        this.scheduleTransfer = scheduleTransfer;
        this.customerService = customerService;
    }

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
        logger.info(String.valueOf(schedules.get(0).getId()));
        List<ScheduleDTO> scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployee(employeeService.getById(employeeId));
        List<ScheduleDTO> scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCustomer(customerService.getById(customerId));
        List<ScheduleDTO>  scheduleDTOS = scheduleTransfer.convertToScheduleDTOList(schedules);
        return scheduleDTOS;
    }
}
