package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTestSuite {

    @Autowired
    private TaskController taskController;

    @Test
    public void testGetTask() throws TaskNotFoundException {
        //Then
        TaskDto readTask = taskController.getTask(16L);
        long id = readTask.getId();

        Assert.assertEquals(id, 16L);

    }

}
