package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;


public class EmailSchedulerTest {

    @Test
    public void shouldGetCountAndSendMail(){
        SimpleEmailService mockSimpleEmailService = Mockito.mock(SimpleEmailService.class);
        TaskRepository mockTaskRepository = Mockito.mock(TaskRepository.class);
        AdminConfig mockAdminConfig = Mockito.mock(AdminConfig.class);

        EmailScheduler emailScheduler = new EmailScheduler(mockSimpleEmailService, mockTaskRepository, mockAdminConfig);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        Mockito.verify(mockTaskRepository, Mockito.times(1)).count();
        Mockito.verify(mockSimpleEmailService, Mockito.times(1)).send(ArgumentMatchers.any());

    }

}