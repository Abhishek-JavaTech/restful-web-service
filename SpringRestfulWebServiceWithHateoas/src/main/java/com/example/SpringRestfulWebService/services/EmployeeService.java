package com.example.SpringRestfulWebService.services;

import com.example.SpringRestfulWebService.model.Employee;
import com.example.SpringRestfulWebService.repos.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> get(int id){
        return employeeRepository.findById(id);
    }

    public void update(Employee employee){
        employeeRepository.save(employee);
    }

    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }

    public Employee applyPatch(Employee emp, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        JsonNode jsonNode = patch.apply(objectMapper.convertValue(emp, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, Employee.class);
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }
}
