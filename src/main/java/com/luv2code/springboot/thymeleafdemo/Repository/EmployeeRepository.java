package com.luv2code.springboot.thymeleafdemo.Repository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //add a method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

    //search by name
    public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lname);

}
