package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTestSuite {
    @Autowired
    private TaskController taskController;

    @Test
    public void testGetTask() throws TaskNotFoundException {
        //Then
        TaskDto readTask = taskController.getTask((long)2);
        long id = readTask.getId();

        Assert.assertEquals(id, 2);

    }

    @Test
    public void testDbService(){
        //Given
        TaskRepository repository = mock(TaskRepository.class);
        DbService dbService = new DbService(repository);
        List<Task> resultList = new ArrayList<Task>();
        Task task1 = new Task((long)4, "MockTest", "TestMock");
        Task task2 = new Task((long)5, "Abc", "Bcd");
        Task task3 = new Task((long)6, "CVds", "ASFRhsk");

        resultList.add(task1);
        resultList.add(task2);
        resultList.add(task3);

        when(repository.findAll()).thenReturn(resultList);
        when(repository.save(task1)).thenReturn(task1);
        when(repository.findById(anyLong())).thenReturn(null);


        //When
        List<Task> resultGetTasks = dbService.getAllTasks();
        Task resultSaveTask = dbService.saveTask(task1);
        Optional<Task> resultGetTask = dbService.getTask((long)2);

        //Then
        Assert.assertEquals(3, resultGetTasks.size());
        Assert.assertSame(task1, resultSaveTask);
        Assert.assertNull(resultGetTask);

    }
}
