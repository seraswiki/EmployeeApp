package com.Employee.EmployeeApplication.Service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.Employee.EmployeeApplication.DTOs.EmployeeRequestDTO;
import com.Employee.EmployeeApplication.DTOs.EmployeeResponseDTO;
import com.Employee.EmployeeApplication.Model.Employee;
import com.Employee.EmployeeApplication.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    //POST
    public EmployeeResponseDTO upsertEmployee(EmployeeRequestDTO employeeRequestDTO) {

        try {
            Employee employee = employeeRepository.save(new Employee(employeeRequestDTO));
            EmployeeResponseDTO employeeResponse = new EmployeeResponseDTO();

            employeeResponse.setId(employee.getId());
            employeeResponse.setName(employee.getName());
            employeeResponse.setDepartment(employee.getDepartment());

            return employeeResponse;
        } catch (Exception e) {
            throw e;
            // System.out.println("Something went wrong here!");
        }
    }

    //GET all
    public List<EmployeeResponseDTO> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            List<EmployeeResponseDTO> employeeResponseDTO;

            employeeResponseDTO = employees.stream()
                    //.filter(employee -> employee.getId() > 2)
                    .map(employee -> new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getDepartment()))
                    .collect(Collectors.toList());

            return employeeResponseDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    //GET by Id
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee;
        EmployeeResponseDTO employeeResponseDTO;
        try {
            employee = employeeRepository.getById(id);
            employeeResponseDTO = new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getDepartment());
        } catch (Exception e) {
            return null;
        }
        return employeeResponseDTO;
    }

    //PUT by Id
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO) {

        EmployeeResponseDTO employeeResponseDTO = null;
        try {
            //update database
            //error handling check whether id is available in db
            Employee employee = employeeRepository.getById(id);
            if (employee != null) {
                employee.setName(employeeRequestDTO.getName());
                employee.setDepartment(employeeRequestDTO.getDepartment());
                employeeRepository.save(employee);

                //create response
                employeeResponseDTO = new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getDepartment());
            }
        } catch (EntityNotFoundException e) {
            throw e;
        }
        return employeeResponseDTO;
    }

    public void deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }

    //DELETE
   /* public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO) {
        try {
            Employee employee = employeeRepository.
        } catch (Exception e) {
            throw e;
            //System.out.println("Something went wrong here!");
        }
    }*/

    /*public void deleteEmployeeById(EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = employeeRepository.deleteById();
        System.out.println("Successfully deleted");
    }*/
}
