package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.exception.UserNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getById(Long id) {
        Optional<Employee> employee  = employeeRepository.findById(id);
        return employee.orElseThrow(UserNotFoundException::new);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = getById(employeeId);
        employee.setAvailableDays(daysAvailable);
        save(employee);
    }

    public List<Employee> getByAvailableForService(EmployeeRequestDTO employeeRequestDTO) {
        /** get all requirements for service: available days and skills **/
        DayOfWeek requestDay = employeeRequestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> requestSkills = employeeRequestDTO.getSkills();

        /**
         * find available employees:
         * 1. get all employees available at day $,
         * 2. get all employees with skills set in this middle list.
         * **/
        List<Employee> employees = employeeRepository
                .findByAvailableDaysContaining(requestDay)
                .stream().filter(employee -> employee.getSkills().containsAll(requestSkills))
                .collect(Collectors.toList());

        return employees;
    }
}
