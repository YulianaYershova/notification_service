package com.fitgoal.service.impl;

import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.service.config.UserServiceConfiguration;
import com.fitgoal.service.impl.utils.TestHelper;
import com.fitgoal.service.mail.impl.MailSenderImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {

    @Mock
    private AuditDao auditDao;
    @Mock
    private MailSenderImpl mailSenderImpl;
    @Mock
    private UserServiceConfiguration userServiceConfiguration;
    @InjectMocks
    private NotificationServiceImpl notificationServiceImpl;

    @Test
    public void registerTest() {
        UserVerification userVerification = TestHelper.buildUserVerification();
        notificationServiceImpl.register(userVerification);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void confirmRegistrationTest() {
        Recipient recipient = TestHelper.buildRecipient();
        notificationServiceImpl.confirmRegistration(recipient);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void resetPasswordTest() {
        UserVerification userVerification = TestHelper.buildUserVerification();
        notificationServiceImpl.resetPassword(userVerification);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void confirmPasswordResetTest() {
        Recipient recipient = TestHelper.buildRecipient();
        notificationServiceImpl.confirmPasswordReset(recipient);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }
}
