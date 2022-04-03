package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByPetListContaining(Pet pet);
    List<Schedule> findByEmployeeListContaining(Employee employee);
    List<Schedule> findByPetListIn(List<Pet> pets);
}
