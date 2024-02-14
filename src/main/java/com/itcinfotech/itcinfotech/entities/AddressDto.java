package com.itcinfotech.itcinfotech.entities;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class AddressDto {
    @Id
    private Long id;
    private String address;
    @OneToOne
    private EmployeeDto employeeDto;

}
