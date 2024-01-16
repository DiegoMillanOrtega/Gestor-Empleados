package com.diego.controller;

import com.diego.models.Employee;
import com.diego.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
@CrossOrigin(value = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping("/getEmployee")
    public List<Employee> getEmployee() {
        List<Employee> employees = this.employeeService.employeeList();
        logger.info("Employees obtained");
        employees.forEach(employee -> logger.info(employee.toString()));
        return employees;
    }
}
