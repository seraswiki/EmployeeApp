package com.Employee.EmployeeApplication.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO extends EmployeeRequestDTO {
    private Long id;

    public EmployeeResponseDTO(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
}
