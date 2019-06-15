package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        long size = taskRepository.count();

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");


        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Currently in database you have got: " + size + " task" + (size == 1 ? "" : "s"));
        context.setVariable("company_details", companyConfig.getCompanyName() + "\n" + companyConfig.getCompanyGoal() + "\n" + companyConfig.getCompanyEmail() + "\n" + companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyTasksEmail(String message) {
        long size = taskRepository.count();

        List<String> tasks = taskRepository.findAll().stream().map(task -> task.getTitle() + " : " + task.getContent()).collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("tasks_in_db", size == 0 ? false : true);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Currently in database you have got: " + size + " task" + (size == 1 ? "" : "s"));
        context.setVariable("company_details", companyConfig.getCompanyName() + "\n" + companyConfig.getCompanyGoal() + "\n" + companyConfig.getCompanyEmail() + "\n" + companyConfig.getCompanyPhone());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks", tasks);

        return templateEngine.process("mail/daily-tasks-mail", context);
    }
}
