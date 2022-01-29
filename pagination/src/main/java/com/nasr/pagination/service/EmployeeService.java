package com.nasr.pagination.service;

import com.nasr.pagination.domain.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
public interface EmployeeService {

    List<Employee> getAllEmployees();

    void save(Employee employee);

    Employee getEmployeeById(Long id);

    void deleteById(Long id);

    Page<Employee> findPaginated(int pageNumber,int pageSize,String sortField,String sortDirection);
//    void update(Employee employee);
}
