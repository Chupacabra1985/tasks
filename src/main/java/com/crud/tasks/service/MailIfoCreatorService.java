package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailIfoCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String singlePlural(long count, String single, String plural){
        return count==1 ? single : plural;
    }

    public String buildInfoCardEmail() {
        long size = taskRepository.count();
        Context context = new Context();
        context.setVariable("message", "Currently in database you got: " + size + singlePlural(size, "task", "tasks"));
        context.setVariable("website_url", "https://chupacabra1985.github.io/");
        context.setVariable("button_name", "Check The Website!");
        context.setVariable("button_show", true);
        context.setVariable("user", adminConfig.getAdminName());
        context.setVariable("goodbye", "See You tomorrow!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_phone", companyConfig.getCompanyPhone());
        context.setVariable("company_mail", companyConfig.getCompanyMail());

        return templateEngine.process("mail/created-info-card-mail", context);
    }
}
