package io.github.manasjain0405.employeemanagement.service.impl;

import io.github.manasjain0405.employeemanagement.exceptions.EmployeeNotFoundException;
import io.github.manasjain0405.employeemanagement.model.Employee;
import io.github.manasjain0405.employeemanagement.repository.EmployeeRepo;
import io.github.manasjain0405.employeemanagement.service.EmployeeService;
import io.github.manasjain0405.employeemanagement.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"Employee"})
public class EmployeeServiceI implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    @Cacheable(key = "#id", value = "employees")
    public Employee getEmployeeDetails(final Long id) throws EmployeeNotFoundException {
        return employeeRepo.findById(id)
                .orElseThrow(ExceptionUtils.bind(EmployeeNotFoundException::new,
                        String.format("Employee with id %d not found", id)));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public void addEmployee(final Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    @CacheEvict(allEntries = false, key = "#id", value = "employees")
    public void removeEmployee(final Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    @CachePut(key = "#employee.id", value = "employees")
    //@CacheEvict(allEntries = false, key = "#employee.id", value = "employees")
    public Employee modifyEmployee(final Employee employee) {
        return employeeRepo.save(employee);
    }
}
