package com.Employee.EmployeeApplication.DTOs;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {

    @NotNull
    public String name;
    @NotNull
    public String department;
}
