package entities.dto;

import entities.Employee;

public class EmployeeDTO {
    public long id;
    public String name;
    public String address;

    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }
}
