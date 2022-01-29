package com.nasr.pagination.controller;

import com.nasr.pagination.domain.Employee;
import com.nasr.pagination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //display list of employee
    @GetMapping({"/",""})
    public String viewHomePage(Model model){
       /* model.addAttribute("listEmployees",service.getAllEmployees());
        return "index";*/

        //default page number is 1
       return findPaginated(1,model);
    }
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(/*@ModelAttribute("employee")*/ Employee employee){
        service.save(employee);
        return "redirect:/";
    }
    @GetMapping("/showForForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id,Model model){
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("id") Long id){

        service.deleteById(id);
        return "forward:/";
    }
    @GetMapping("/page/{PageNo}")
    public String findPaginated(@PathVariable("PageNo") int pageNumber,Model model){
        //when page number greather than one then /page/{pageNo} api is call
        int pageSize=5;
        Page<Employee> page=service.findPaginated(pageNumber,pageSize);
        List<Employee> employees=page.getContent();
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listEmployees",employees);

        return "index";
    }
}
