package com.fit_goal.util;

import com.fit_goal.util.enums.Message;
import com.fit_goal.util.enums.Subject;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Notification {

    private Subject subject;
    private Message message;

    public Notification(Subject subject, Message message) {
        this.subject = subject;
        this.message = message;
    }
}
