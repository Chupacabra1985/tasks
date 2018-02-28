package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "test", "test");

        //When
        Task result = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("test", result.getContent());
        assertEquals("test", result.getTitle());
        assertEquals(new Long(1),new Long(result.getId()));
    }

    @Test
    public void mapToTaskDtoTest(){
        //Given
        Task task = new Task(1L, "test", "test");

        //When
        TaskDto result = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("test", result.getContent());
        assertEquals("test", result.getTitle());
        assertEquals(new Long(1), new Long(result.getId()));
    }

    @Test
    public void mapToTaskDtoList(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test", "test"));

        //When
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getTitle());
        assertEquals("test", result.get(0).getContent());
        assertEquals(new Long(1), new Long(result.get(0).getId()));
    }


}
