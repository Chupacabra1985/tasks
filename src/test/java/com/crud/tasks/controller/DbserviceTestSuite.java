package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DbserviceTestSuite {

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
