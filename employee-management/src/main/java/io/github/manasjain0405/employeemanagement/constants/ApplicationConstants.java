package io.github.manasjain0405.employeemanagement.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class ApplicationConstants {

    @Value("${amqp-config.rabbit-mq.add-employee.queue}")
    public String ADD_EMPLOYEE_QUEUE;

    @Value("${amqp-config.rabbit-mq.modify-employee.queue}")
    public String MODIFY_EMPLOYEE_QUEUE;

    @Value("${amqp-config.rabbit-mq.add-employee.routing-key}")
    public String ADD_EMPLOYEE_ROUTING_KEY;

    @Value("${amqp-config.rabbit-mq.modify-employee.routing-key}")
    public String MODIFY_EMPLOYEE_ROUTING_KEY;

    @Value("${amqp-config.rabbit-mq.exchange}")
    public String EMPLOYEE_EXCHANGE;

    public ApplicationConstants() {
    }
}
