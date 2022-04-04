package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {
    PetUtils petUtils;
    EmployeeService employeeService;

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer, customerDTO);

        /** set pet list **/
        List<Pet> pets = petUtils.convertToPetList(customerDTO.getPetIds());
        customer.setPets(pets);

        return customer;
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customerDTO, customer);

        /** set petIds **/
        List<Long> petIds = petUtils.convertToPetIds(customer.getPets());
        customerDTO.setPetIds(petIds);

        return customerDTO;
    }



    public EmployeeDTO convertToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        return employeeDTO;
    }

    public Employee convertToEmployee(EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        return employee;
    }

    public List<EmployeeDTO> convertToEmployeeDTOList(List<Employee> employeeList) {
        if (employeeList == null) {
            return null;
        }

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDTOList.add(this.convertToEmployeeDTO(employee));
        }
        return employeeDTOList;
    }

    /** employee Id <- employee **/
    public List<Long> convertToEmployeeIds(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

    /** employee Id -> employee **/
    public List<Employee> convertToEmployeeList(List<Long> employeeIds) {
        if (employeeIds == null) {
            return null;
        }
        List<Employee> employees = new ArrayList<>();
        for (Long employeeId : employeeIds) {
            employees.add(employeeService.getById(employeeId));
        }
        return employees;
    }
}
