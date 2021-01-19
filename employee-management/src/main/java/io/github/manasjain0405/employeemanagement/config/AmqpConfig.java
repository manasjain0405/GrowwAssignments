package io.github.manasjain0405.employeemanagement.config;

import io.github.manasjain0405.employeemanagement.constants.ApplicationConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Autowired
    ApplicationConstants constants;

    @Bean
    public Queue addEmployeeQueue() {
        return new Queue(constants.ADD_EMPLOYEE_QUEUE);
    }

    @Bean
    public Queue modifyEmployeeQueue() {
        return new Queue(constants.MODIFY_EMPLOYEE_QUEUE);
    }

    @Bean
    public TopicExchange employeeExchange() {
        return new TopicExchange(constants.EMPLOYEE_EXCHANGE);
    }

    @Bean
    public Binding addEmployeeBinding(@Qualifier("addEmployeeQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(constants.ADD_EMPLOYEE_ROUTING_KEY);
    }

    @Bean
    public Binding modifyEmployeeBinding(@Qualifier("modifyEmployeeQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(constants.MODIFY_EMPLOYEE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
