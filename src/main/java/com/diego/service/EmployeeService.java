package com.diego.service;

import com.diego.models.Employee;
import com.diego.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private IEmployeeRepository employeeRepository;


    @Override
    public void saveEmployee(Employee employee) {

        if (employee.getId() != null) {
            Employee employeeExisting = this.employeeRepository.findById(employee.getId()).orElse(null);
            if (employeeExisting != null) {
                if (employee.getFirstName() != null) {
                    employeeExisting.setFirstName(employee.getFirstName());
                }
                if (employee.getLastName() != null) {
                    employeeExisting.setLastName(employee.getLastName());
                }
                if (employee.getSalary() != null) {
                    employeeExisting.setSalary(employee.getSalary());
                }
                employeeRepository.save(employeeExisting);
            }
        } else {
            this.employeeRepository.save(employee);
        }


    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> employeeList() { return this.employeeRepository.findAll();}

}


