package com.project.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE CUSTOMER SET deleted = true where customer_id=?")
@Where(clause = "deleted=false")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    private Date dateOfBirth;

    private Date registrationDate;

    private Boolean isActive;
    private boolean deleted = Boolean.FALSE;

//    public Customer(Integer customerId, String firstName, String lastName, String email, Date dateOfBirth, Date registrationDate, Boolean isActive, boolean deleted) {
//        this.customerId = customerId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.dateOfBirth = dateOfBirth;
//        this.registrationDate = registrationDate;
//        this.isActive = isActive;
//        this.deleted = deleted;
//    }
//
//    public Customer() {
//
//    }
}
