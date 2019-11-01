package com.fit_goal;

import com.fit_goal.enums.Message;
import com.fit_goal.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class Notification {
    // TODO create single Enum for holding message information
    @NotBlank
    private Subject subject;
    @NotBlank
    private Message message;
}
