package com.luv2code.springboot.thymeleafdemo.Service;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> theEmployees();

    public List<Employee> searchBy(String name);

    public Employee getEmployee (int theId);

    public void addEmployee(Employee employee);

    public void updateEmployee(Employee employee);

    public void deleteEmployee(int theId);




}
