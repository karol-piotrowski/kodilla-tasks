package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";


    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;


    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 20000)
    public void sendInformationEmail() {

        long size = taskRepository.count();
//        simpleEmailService.send(new Mail(adminConfig.getSenderMail(), adminConfig.getAdminMail(), null,
//                SUBJECT, "Currently in database you got: " + size + " task" + (size == 1 ? "" : "s"))
//        );

        simpleEmailService.send(new Mail(adminConfig.getSenderMail(), adminConfig.getAdminMail(), null,
                SUBJECT, "Please find the status of your tasks in the database below.")
        );

    }
}
