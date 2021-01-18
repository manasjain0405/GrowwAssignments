package io.github.manasjain0405.employeemanagement.controller;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/{employeeId}")
    public Employee getEmployeeInfo(@PathVariable("employeeId") final Long employeeId) {

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
        template.convertAndSend("employee_exchange", "employee_routing_key", employee);
        //employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") final Long employeeId) {
        employeeService.removeEmployee(employeeId);
    }

    @PutMapping("/{employeeId}")
    public void modifyEmployee(@PathVariable("employeeId") final Long employeeId, @RequestBody final Employee employee) {
        employeeService.modifyEmployee(employeeId, employee);
    }

    @PutMapping("/")
    public void modifyEmployee(@RequestBody final Employee employee) {
        employeeService.addEmployee(employee);
    }
}
