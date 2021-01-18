package io.github.manasjain0405.employeemanagement.repository;

import io.github.manasjain0405.employeemanagement.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(final Long id);

    void deleteById(final Long id);
}
