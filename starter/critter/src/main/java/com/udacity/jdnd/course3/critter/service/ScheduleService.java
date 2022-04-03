package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPet(Pet pet) {
        return scheduleRepository.findByPetListContaining(pet);
    }

    public List<Schedule> getSchedulesByEmployee(Employee employee) {
        return scheduleRepository.findByEmployeeListContaining(employee);
    }

    public List<Schedule> getSchedulesByPetList(List<Pet> pets) {
        return scheduleRepository.findByPetListIn(pets);
    }
}
