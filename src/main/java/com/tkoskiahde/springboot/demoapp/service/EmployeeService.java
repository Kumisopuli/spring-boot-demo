package com.tkoskiahde.springboot.demoapp.service;

import com.tkoskiahde.springboot.demoapp.dao.EmployeeRepository;
import com.tkoskiahde.springboot.demoapp.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    public Employee findById(int id) {

        // Logging has been auto-configured for methods to use with @Slf4j annotation
        log.info("Attempting to fetch employee with id: {}", id);

        // "Optional" class was introduced in Java 8
        Optional<Employee> result = employeeRepository.findById(id);

        Employee fetchedEmployee = null;
        if (result.isPresent()) {
            fetchedEmployee = result.get();
        }
        else {
            log.error("No employee found with id: {}", id);
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return fetchedEmployee;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}






