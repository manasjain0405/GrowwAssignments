package io.github.manasjain0405.employeemanagement.consumer;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConsumer {

    @Autowired
    EmployeeService employeeService;

    @RabbitListener(queues = "${amqp-config.rabbit-mq.add-employee.queue}")
    public void consumeAddRequest(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @RabbitListener(queues="${amqp-config.rabbit-mq.modify-employee.queue}")
    public void consumeUpdateRequest(Employee employee) {
        employeeService.modifyEmployee(employee);
    }

    @KafkaListener( topics = "${kafka-config.add-employee.topic}", groupId = "${kafka-config.add-employee.group-id}")
    public void consumeAddRequestKafka(Employee employee) {
        employeeService.addEmployee(employee);
        System.out.println("Received via kafka");
    }
}
