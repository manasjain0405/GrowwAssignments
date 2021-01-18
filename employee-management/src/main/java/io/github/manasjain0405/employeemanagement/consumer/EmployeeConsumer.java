package io.github.manasjain0405.employeemanagement.consumer;

import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConsumer {

    @Autowired
    EmployeeService employeeService;

    @RabbitListener(queues = "employee_queue")
    public void consumeMessageFromQueue(Employee employee) {
        employeeService.addEmployee(employee);
    }
}
