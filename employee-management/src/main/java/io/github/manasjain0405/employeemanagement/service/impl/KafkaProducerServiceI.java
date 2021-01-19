package io.github.manasjain0405.employeemanagement.service.impl;

import io.github.manasjain0405.employeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServiceI {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @Value("${kafka-config.add-employee.topic}")
    public String TOPIC;

    public void sendToAddQueue(Employee employee) {
        this.kafkaTemplate.send(TOPIC, employee);
        System.out.println("Sent via kafka");
    }
}
