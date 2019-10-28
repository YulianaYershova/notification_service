package com.fit_goal.credentials;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used for credentials.
 */
@Getter
@Setter
@NoArgsConstructor
public class Credentials {

    /** The user name.*/
    private String username;

    /** The password.*/
    private char[] password;

}
