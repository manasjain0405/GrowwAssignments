package io.github.manasjain0405.employeemanagement.exceptions;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
