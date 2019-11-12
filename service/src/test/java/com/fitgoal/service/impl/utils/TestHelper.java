package com.fitgoal.service.impl.utils;

import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestHelper {
    public static UserVerification buildUserVerification() {
        return UserVerification
                .builder()
                .email("test@test.com")
                .verificationLink("verification_link")
                .build();
    }

    public static Recipient buildRecipient() {
        return Recipient
                .builder()
                .email("test@test.com")
                .build();
    }
}
