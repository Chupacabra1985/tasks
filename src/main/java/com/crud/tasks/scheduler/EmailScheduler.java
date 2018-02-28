package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class EmailScheduler{

    private static final String SUBJECT = "Tasks: Once a day email";


    private SimpleEmailService simpleEmailService;


    private TaskRepository taskRepository;


    private AdminConfig adminConfig;

    @Autowired
    public EmailScheduler(SimpleEmailService simpleEmailService, TaskRepository taskRepository, AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }

    public String singlePlural(long count, String single, String plural){
        return count==1 ? single : plural;
    }

    @Scheduled(cron="0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), "", SUBJECT, "Currently in database you got: "
        + size + singlePlural(size, "task", "tasks")));
    }

}
