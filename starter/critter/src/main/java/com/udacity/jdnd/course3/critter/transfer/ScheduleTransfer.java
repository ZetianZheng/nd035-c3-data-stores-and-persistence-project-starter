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
    private final UserTransfer userTransfer;
    private final PetTransfer petTransfer;

    public ScheduleTransfer(UserTransfer userTransfer, PetTransfer petTransfer) {
        this.userTransfer = userTransfer;
        this.petTransfer = petTransfer;
    }

    public ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        /** translate pets to petIds, employee to employeeIds **/
        List<Pet> petList = schedule.getPetList();
        List<Employee> employeeList = schedule.getEmployeeList();

        if (petList != null) {
            List<Long> petIds = petTransfer.convertToPetIds(petList);
            scheduleDTO.setPetIds(petIds);
        }
        if (employeeList != null) {
            List<Long> employeeIds = userTransfer.convertToEmployeeIds(employeeList);
            scheduleDTO.setEmployeeIds(employeeIds);
        }
        return scheduleDTO;
    }

    public Schedule convertToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        /** translate petIds to pets, employeeIds to employee **/
        List<Long> petIds = scheduleDTO.getPetIds();
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        if (petIds != null) {
            List<Pet> pets = petTransfer.convertToPetList(petIds);
            schedule.setPetList(pets);
        }
        if (employeeIds != null) {
            List<Employee> employees = userTransfer.convertToEmployeeList(employeeIds);
            schedule.setEmployeeList(employees);
        }

        return schedule;
    }

    public List<ScheduleDTO> convertToScheduleDTOList(List<Schedule> scheduleList) {
        if (scheduleList == null) {
            return null;
        }

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            scheduleDTOS.add(this.convertToScheduleDTO(schedule));
        }
        return scheduleDTOS;
    }
}
