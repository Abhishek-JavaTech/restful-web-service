package com.example.SpringRestfulWebService.controllers;

import com.example.SpringRestfulWebService.model.Employee;
import com.example.SpringRestfulWebService.model.EmployeeList;
import com.example.SpringRestfulWebService.services.EmployeeService;
import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ResourceController {

    private EmployeeService employeeService;

    public ResourceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Employee employee) {
        try {
            employeeService.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            description = "add a forward slash to requestMapping to fix error of no mapping found for getAll() method"
    )
    @GetMapping("/")
    public ResponseEntity getAll() {
        EmployeeList employeeListToSend = new EmployeeList();
        try {
              var employeeList = employeeService.getAll();
              for(Employee employee : employeeList){
                  employee.add(linkTo(methodOn(ResourceController.class).getEmployeeById(employee.getId())).withRel("get employee by id"));
                  employeeListToSend.getEmployeesList().add(employee);
              }
              employeeListToSend.add(linkTo(methodOn(ResourceController.class).getAll()).withSelfRel());
            return ResponseEntity.status(HttpStatus.OK).body(employeeListToSend);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") int id) {
        try {
            var employee = employeeService.findById(id).get();
            employee.add(linkTo(methodOn(ResourceController.class, HttpMethod.GET).getEmployeeById(id)).withSelfRel());
            employee.add(linkTo(methodOn(ResourceController.class, HttpMethod.DELETE).delete(id)).withRel("delete employee by id"));
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(
            description = "this request works when request body send in JSON format following RFC6902 standard",
            summary = "partial update request",
            externalDocs = @ExternalDocumentation(
                    description = "please check below syntax as an example and perform changes per your request" +
                            "[\n" +
                            "{\n" +
                            "  \"op\": \"replace\",\n" +
                            "  \"path\": \"/address\",\n" +
                            "  \"value\": \"MH\"\n" +
                            "},\n" +
                            "\n" +
                            "{\n" +
                            "\"op\": \"add\",\n" +
                            "  \"path\": \"/hobbies/-\",\n" +
                            "  \"value\": \"gyming\"\n" +
                            "}\n" +
                            "]"

            )

    )
    @PatchMapping("{id}")
    public ResponseEntity partialUpdate(@PathVariable("id") int id, @RequestBody JsonPatch patch) {
        try {
            var emp = employeeService.get(id).get();
            employeeService.save(employeeService.applyPatch(emp, patch));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            employeeService.delete(employeeService.get(id).get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
