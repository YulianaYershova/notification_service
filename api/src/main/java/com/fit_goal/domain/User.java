package com.fit_goal.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
public class User extends BaseEntity implements Serializable {

    @NotNull
    @Email
    private String email;
    private String password;

    @NotNull
    private String verificationToken;

    public User(@NotNull @Email String email, String password) {
        this.email = email;
        this.password = password;
    }

}
