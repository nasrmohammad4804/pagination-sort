package com.nasr.pagination.service.impl;

import com.nasr.pagination.domain.Employee;
import com.nasr.pagination.repository.EmployeeRepository;
import com.nasr.pagination.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public void save(Employee employee) {
        repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("this employee not exists")
        );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Employee> findPaginated(int pageNumber,int pageSize,String sortField,String sortDirection) {

        //pageNumber -1 because we always start page one two three .. but spring data jpa start from zero
        Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable= PageRequest.of(pageNumber-1,pageSize,sort);

        return repository.findAll(pageable);
    }
}
