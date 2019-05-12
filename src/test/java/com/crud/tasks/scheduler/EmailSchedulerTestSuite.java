package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSchedulerTestSuite {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void sendInformationEmail() {
        // Given
//        Mail mail = new Mail("sender@test.com", "test@test.com", "test2@test.com", "Test", "Test Message");
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(mail.getMailFrom());
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setCc(mail.getToCc());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());

        when(taskRepository.count()).thenReturn(2l);

        // When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).send(anyObject());
    }
}