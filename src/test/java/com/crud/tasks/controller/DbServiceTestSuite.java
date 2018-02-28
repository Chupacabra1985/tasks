package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DbServiceTestSuite {

    @Test
    public void testGetAllTask() {
        //Given
        TaskRepository repository = mock(TaskRepository.class);
        DbService dbService = new DbService(repository);
        List<Task> resultList = new ArrayList<Task>();
        Task task1 = new Task((long) 4, "MockTest", "TestMock");
        Task task2 = new Task((long) 5, "Abc", "Bcd");
        Task task3 = new Task((long) 6, "CVds", "ASFRhsk");

        resultList.add(task1);
        resultList.add(task2);
        resultList.add(task3);

        when(repository.findAll()).thenReturn(resultList);

        //When
        List<Task> resultGetTasks = dbService.getAllTasks();

        //Then
        Assert.assertEquals(3, resultGetTasks.size());
    }

    @Test
    public void testSaveTask(){
        //Given
        TaskRepository repository = mock(TaskRepository.class);
        DbService dbService = new DbService(repository);
        Task task1 = new Task((long) 4, "MockTest", "TestMock");

        when(repository.save(task1)).thenReturn(task1);

        //When
        Task resultSaveTask = dbService.saveTask(task1);

        //Then
        Assert.assertSame(task1, resultSaveTask);
    }

    @Test
    public void testGetTask(){
        //Given
        TaskRepository repository = mock(TaskRepository.class);
        DbService dbService = new DbService(repository);

        when(repository.findById(anyLong())).thenReturn(null);

        //When
        Optional<Task> resultGetTask = dbService.getTask((long) 2);

        //Then
        Assert.assertNull(resultGetTask);
    }

    @Test
    public void testDeleteTask(){
        //Given
        TaskRepository repository = mock(TaskRepository.class);
        DbService dbService = new DbService(repository);

        //When
        dbService.deleteTask(anyLong());

        //Then
        Mockito.verify(repository, Mockito.times(1)).deleteById(anyLong());
    }

}
