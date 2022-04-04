package com.udacity.jdnd.course3.critter.util;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserUtils {
    PetUtils petUtils;
    PetService petService;

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer, customerDTO);

        /** set pet list **/
        List<Pet> pets = convertToPets(customerDTO.getPetIds());
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

    public List<Pet> convertToPets(List<Long> petIds) {
        if (petIds == null) {
            return null;
        }
        List<Pet> pets = new ArrayList<>();
        for (Long id : petIds) {
            Pet pet = petService.getPetById(id);
            pets.add(pet);
        }
        return pets;
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
}
