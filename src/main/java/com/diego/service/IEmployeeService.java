package com.diego.service;

import com.diego.models.Employee;

import java.util.List;

public interface IEmployeeService {
    public void saveEmployee(Employee employee);
    public void deleteEmployeeById(Long id);
    public List<Employee> employeeList( );
}
