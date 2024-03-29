package com.douglas.Douglas.infrastructure.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRest extends BaseRest {

    @NotNull(message = "exception.save.user.email.required")
    @NotEmpty(message = "exception.save.user.email.required")
    @Email(message = "exception.save.user.email.not.format",
            regexp = "^[a-z|A-Z|0-9].*@[a-z|A-Z].{0,6}mail.com$")
    private String email;

    @NotNull(message = "exception.save.user.password.required")
    @NotEmpty(message = "exception.save.user.password.required")
    @Size(max = 15, message = "exception.save.user.password.max.length")
    private String password;

    @NotNull(message = "exception.save.user.first.name.required")
    @NotEmpty(message = "exception.save.user.first.name.required")
    @Size(max = 50, message = "exception.save.user.first.name.max.length")
    private String firstName;

    @NotNull(message = "exception.save.user.last.name.required")
    @NotEmpty(message = "exception.save.user.last.name.required")
    @Size(max = 100, message = "exception.save.user.last.name.max.length")
    private String lastName;

    @Size(max = 200, message = "exception.save.user.address.max.length")
    private String address;

    @Size(max = 11, message = "exception.save.user.cellphone.max.length")
    private String cellPhone;

    private boolean status;

    private Set<RoleRest> roles = new HashSet<>();
}
