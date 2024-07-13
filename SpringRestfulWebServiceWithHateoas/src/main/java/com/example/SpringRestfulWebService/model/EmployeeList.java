package com.example.SpringRestfulWebService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeList extends RepresentationModel<EmployeeList> {
    private List<Employee> employeesList = new ArrayList<>();
}
