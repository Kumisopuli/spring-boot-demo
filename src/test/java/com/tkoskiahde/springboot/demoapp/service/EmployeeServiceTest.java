package com.tkoskiahde.springboot.demoapp.service;

import com.tkoskiahde.springboot.demoapp.dao.EmployeeRepository;
import com.tkoskiahde.springboot.demoapp.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    /* Mock EmployeeRepository (= class that interacts with DB) so we can test service-level stuff without setting
    * up an actual DB for tests.*/
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @BeforeEach
    public void setup() {
        Employee testEmployee = new Employee(1, "Test", "Person", "test.person@example.com");
        when(employeeRepository.findAllByOrderByLastNameAsc()).thenReturn(List.of(testEmployee));
    }

    @Test
    public void testFindAll() {
        List<Employee> employees = employeeService.findAll();
        assertEquals(1, employees.size());
        assertEquals("Person", employees.get(0).getLastName());
    }
}
