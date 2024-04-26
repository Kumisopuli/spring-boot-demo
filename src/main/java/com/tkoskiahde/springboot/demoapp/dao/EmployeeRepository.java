package com.tkoskiahde.springboot.demoapp.dao;

import com.tkoskiahde.springboot.demoapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Find all employees, sorted by last name. Spring data JPA parses the method name to generate the query.
    public List<Employee> findAllByOrderByLastNameAsc();

}
