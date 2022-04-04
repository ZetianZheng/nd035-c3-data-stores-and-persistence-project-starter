package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleUtils {
    UserUtils userUtils;
    PetUtils petUtils;

    public ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        /** translate pets to petIds, employee to employeeIds **/
        List<Long> petIds = petUtils.convertToPetIds(schedule.getPetList());
        List<Long> employeeIds = userUtils.convertToEmployeeIds(schedule.getEmployeeList());
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;
    }

    public Schedule convertToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        /** translate petIds to pets, employeeIds to employee **/
        List<Pet> pets = petUtils.convertToPetList(scheduleDTO.getPetIds());
        List<Employee> employees = userUtils.convertToEmployeeList(scheduleDTO.getEmployeeIds());
        schedule.setEmployeeList(employees);
        schedule.setPetList(pets);
        return schedule;
    }

    public List<ScheduleDTO> convertToScheduleDTOList(List<Schedule> scheduleList) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            scheduleDTOS.add(this.convertToScheduleDTO(schedule));
        }
        return scheduleDTOS;
    }
}
