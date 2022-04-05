package com.udacity.jdnd.course3.critter.transfer;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleTransfer {
    UserTransfer userTransfer;
    PetTransfer petTransfer;

    public ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        /** translate pets to petIds, employee to employeeIds **/
        List<Long> petIds = petTransfer.convertToPetIds(schedule.getPetList());
        List<Long> employeeIds = userTransfer.convertToEmployeeIds(schedule.getEmployeeList());
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        return scheduleDTO;
    }

    public Schedule convertToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        /** translate petIds to pets, employeeIds to employee **/
        List<Pet> pets = petTransfer.convertToPetList(scheduleDTO.getPetIds());
        List<Employee> employees = userTransfer.convertToEmployeeList(scheduleDTO.getEmployeeIds());
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
