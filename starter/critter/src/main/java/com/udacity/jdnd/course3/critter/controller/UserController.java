package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.transfer.UserTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /** field inject **/
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final UserTransfer userTransfer;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(CustomerService customerService, EmployeeService employeeService, UserTransfer userTransfer) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.userTransfer = userTransfer;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = userTransfer.convertToCustomer(customerDTO);
        /**
         * id match 0(Long) - 1(JPA):
         * when customer been stored in database, the id will be automatically set to 1,
         * but initially been sat to 0 by Long
         * **/
        Customer JPACustomer = customerService.save(customer);
        CustomerDTO newCustomerDTO = userTransfer.convertToCustomerDTO(JPACustomer);

        return newCustomerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = customerService.getAllCustomers();

        for (Customer customer : customerList) {
            customerDTOList.add(userTransfer.convertToCustomerDTO(customer));
        }

        return customerDTOList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        return userTransfer.convertToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = userTransfer.convertToEmployee(employeeDTO);
        Employee JPAEmployee = employeeService.save(employee);

        return userTransfer.convertToEmployeeDTO(JPAEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return userTransfer.convertToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employeeList = employeeService.getByAvailableForService(employeeDTO);
        return userTransfer.convertToEmployeeDTOList(employeeList);
    }

}
