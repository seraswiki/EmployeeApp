package com.Employee.EmployeeApplication.Controller;

import com.Employee.EmployeeApplication.DTOs.EmployeeRequestDTO;
import com.Employee.EmployeeApplication.DTOs.EmployeeResponseDTO;
import com.Employee.EmployeeApplication.Model.Employee;
import com.Employee.EmployeeApplication.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //Create employee
    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponseDTO> upsertEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        ResponseEntity<EmployeeResponseDTO> response = new ResponseEntity<>(employeeService.upsertEmployee(employeeRequestDTO),
                HttpStatus.CREATED);
        return response;
    }

    //print Hello World! in postman
    @GetMapping("/helloWorld")
    public ResponseEntity<String> helloWorld() {
        ResponseEntity<String> response = new ResponseEntity<>("Hello World!", HttpStatus.OK);
        return response;
    }

    //Read all employees list
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employeeResponseDTOs = employeeService.getAllEmployees();
        ResponseEntity<List<EmployeeResponseDTO>> response = new ResponseEntity<>(employeeResponseDTOs, HttpStatus.OK);

        return response;
    }

    //Read an employee by Id
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.getEmployeeById(id);
        if(employeeResponseDTO != null) {
            ResponseEntity<EmployeeResponseDTO> response = new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
            return  response;
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update an employee by Id
    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@PathVariable Long id, @RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployeeById(id, employeeRequestDTO);

        if(employeeResponseDTO != null) {
            ResponseEntity<EmployeeResponseDTO> response = new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
            return response;
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        return response;
    }
}
