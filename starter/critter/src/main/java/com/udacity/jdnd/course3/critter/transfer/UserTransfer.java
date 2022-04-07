package com.udacity.jdnd.course3.critter.transfer;

import com.udacity.jdnd.course3.critter.controller.UserController;
import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransfer {
    private Logger logger = LoggerFactory.getLogger(UserTransfer.class);
    private final PetTransfer petTransfer;
    private final EmployeeService employeeService;

    public UserTransfer(PetTransfer petTransfer, EmployeeService employeeService) {
        this.petTransfer = petTransfer;
        this.employeeService = employeeService;
    }

    public Customer convertToCustomer(CustomerDTO customerDTO) {
        logger.info("   convertToCustomer called");
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        /** set pet list **/
        System.out.println("petids:" + customerDTO.getPetIds());
        if (customerDTO.getPetIds() != null) {
            List<Pet> pets = petTransfer.convertToPetList(customerDTO.getPetIds());
            customer.setPets(pets);
        }
        return customer;
    }

    public CustomerDTO convertToCustomerDTO(Customer customer) {
        logger.info("  convertToCustomerDTO called:");
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        /** set petIds **/
        if (customer.getPets() != null) {
            List<Long> petIds = petTransfer.convertToPetIds(customer.getPets());
            customerDTO.setPetIds(petIds);
        }

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
        if (employees == null) {
            return null;
        }

        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee : employees) {
            employeeIds.add(employee.getId());
        }

        return employeeIds;
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
