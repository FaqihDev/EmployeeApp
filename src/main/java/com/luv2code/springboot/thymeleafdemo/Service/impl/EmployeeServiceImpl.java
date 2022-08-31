package com.luv2code.springboot.thymeleafdemo.Service.impl;

import com.luv2code.springboot.thymeleafdemo.Repository.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.Service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> theEmployees() {
     return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int theId) {
      Optional<Employee> result = employeeRepository.findById(theId);
      Employee employee = null;

      if (result.isPresent()){
          employee = result.get();
      } else {
          throw new RuntimeException("Did not find employee id -" + theId);
      }

      return employee;
    }



    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
         employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public List<Employee> searchBy(String name) {

        //Buat Objek list kosong
        List<Employee> result = null;
        //Buat kondisi Jika keyword yang dimasukan user ini ini kosong atau berisi apa saja cari
        if (name != null && name.trim().length() > 0) {
            result = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name);
        } else {
            //selain itu kembalikan semua list
            result = employeeRepository.findAll();
        }
        return result;
    }
}
