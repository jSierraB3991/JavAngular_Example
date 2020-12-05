package com.douglas.Douglas.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "authorization")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authorization extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false, length = 500)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    @NotBlank
    private String password;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    @NotBlank
    private String lastName;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "cell_phone", length = 15)
    private String cellPhone;

    @ManyToOne(targetEntity = Role.class)
    private Role role;
}
