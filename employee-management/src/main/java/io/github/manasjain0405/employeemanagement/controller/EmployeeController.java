package io.github.manasjain0405.employeemanagement.controller;

import io.github.manasjain0405.employeemanagement.constants.ApplicationConstants;
import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ApplicationConstants constants;

    @GetMapping("/{id}")
    public Employee getEmployeeInfo(@PathVariable("id") final Long employeeId) {

        Employee employee = null;
        try {
            employee = employeeService.getEmployeeDetails(employeeId);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e);
        }
        return employee;
    }

    @GetMapping("/listAll")
    public List<Employee> getAllEmployeeInfo() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody final Employee employee) {
        template.convertAndSend(constants.EMPLOYEE_EXCHANGE, constants.ADD_EMPLOYEE_ROUTING_KEY, employee);
        //employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") final Long employeeId) {
        employeeService.removeEmployee(employeeId);
    }

//    @PutMapping("/{id}")
//    public void modifyEmployee(@PathVariable("id") final Long employeeId, @RequestBody final Employee employee) {
//        employee.setId(employeeId);
//        employeeService.modifyEmployee(employee);
//    }

    @PutMapping("/")
    public void modifyEmployee(@RequestBody final Employee employee) {
        template.convertAndSend(constants.EMPLOYEE_EXCHANGE, constants.MODIFY_EMPLOYEE_ROUTING_KEY, employee);
        //employeeService.modifyEmployee(employee);
        //employeeService.cacheEmployee(modifiedEmployee.getId(), modifiedEmployee);
    }
}
