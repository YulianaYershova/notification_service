package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.service.util.MailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {

    @Mock
    private AuditDao auditDao;
    @Mock
    private MailSender mailSender;

    private NotificationService notificationService;

    @Before
    public void setNotificationService() {
        notificationService = new NotificationServiceImpl(auditDao, mailSender);
    }

    @Test
    public void registerTest() {
        UserVerification userVerification = buildUserVerification();
        notificationService.register(userVerification);
        verify(mailSender, times(1)).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao, times(1)).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void registerSuccessTest() {
        Recipient recipient = buildRecipient();
        notificationService.registerSuccess(recipient);
        verify(mailSender, times(1)).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao, times(1)).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void resetPasswordTest() {
        UserVerification userVerification = buildUserVerification();
        notificationService.resetPassword(userVerification);
        verify(mailSender, times(1)).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao, times(1)).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void resetPasswordSuccessTest() {
        Recipient recipient = buildRecipient();
        notificationService.resetPasswordSuccess(recipient);
        verify(mailSender, times(1)).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao, times(1)).create(ArgumentMatchers.any(AuditDto.class));
    }

    private UserVerification buildUserVerification() {
        return UserVerification
                .builder()
                .email("test@test.com")
                .verificationLink("verification_link")
                .build();
    }

    private Recipient buildRecipient() {
        return Recipient
                .builder()
                .email("test@test.com")
                .build();
    }

}