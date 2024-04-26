package com.tkoskiahde.springboot.demoapp.controller;

import com.tkoskiahde.springboot.demoapp.entity.Employee;
import com.tkoskiahde.springboot.demoapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model model) {
        // get the employees from the DB
        List<Employee> employees = employeeService.findAll();
        // Add to the Spring model
        model.addAttribute("employees", employees);
        // Return a Thymeleaf template
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        // Create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // Save the employee
        employeeService.save(employee);
        // Redirect back to employees list to prevent duplicate submissions, aka post/redirect/get pattern
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {
        // Get employee from the service
        Employee employeeFromDB = employeeService.findById(employeeId);

        // Set employee in the model to prepopulate the form
        model.addAttribute("employee", employeeFromDB);

        // Send over to our form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById(employeeId);

        return "redirect:/employees/list";
    }
}
