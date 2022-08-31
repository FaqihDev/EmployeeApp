package com.luv2code.springboot.thymeleafdemo.Controller;


import com.luv2code.springboot.thymeleafdemo.Service.EmployeeService;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model model){
        //get employees from db
        List<Employee> theEmployee = employeeService.theEmployees();
        model.addAttribute("employees",theEmployee);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String shofFormForAdd(Model theModel){
        //create model Addtribute to bind form data

        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form";

    }


    @GetMapping("/showFormForUpdate")
    public String updateEmployee(@RequestParam("employeeId") int theId, Model theModel){
        //get the employee from the service
        Employee theEmployee = employeeService.getEmployee(theId);
        //set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee",theEmployee);
        // send over to our form
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        // save the employee
        employeeService.addEmployee(employee);
        //use direct link to prevent duplicate submission
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete (@RequestParam("employeeId") int theId){
        //delete the employee
        employeeService.deleteEmployee(theId);
        //redirect to /employee/list
        return "redirect:/employee/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("employeeName") String name, Model model){
        //invoke service
        List<Employee> theEmployee = employeeService.searchBy(name);
        //bind to model
        model.addAttribute("employees",theEmployee);
        //return view
        return "/employees/list-employees";
    }


}
