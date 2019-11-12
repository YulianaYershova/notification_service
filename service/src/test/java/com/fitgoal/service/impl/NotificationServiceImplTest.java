package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.service.impl.utils.TestHelper;
import com.fitgoal.service.mail.impl.MailSenderImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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

    private NotificationService notificationService;

    @Before
    public void setNotificationService() {
        notificationService = new NotificationServiceImpl(auditDao, mailSenderImpl);
    }

    @Test
    public void registerTest() {
        UserVerification userVerification = TestHelper.buildUserVerification();
        notificationService.register(userVerification);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void registerSuccessTest() {
        Recipient recipient = TestHelper.buildRecipient();
        notificationService.registerSuccess(recipient);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void resetPasswordTest() {
        UserVerification userVerification = TestHelper.buildUserVerification();
        notificationService.resetPassword(userVerification);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }

    @Test
    public void resetPasswordSuccessTest() {
        Recipient recipient = TestHelper.buildRecipient();
        notificationService.resetPasswordSuccess(recipient);
        verify(mailSenderImpl).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        verify(auditDao).create(ArgumentMatchers.any(AuditDto.class));
    }
}