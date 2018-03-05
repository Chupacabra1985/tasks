package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @SpyBean
    private TaskMapper taskMapper;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto((long) 1, "test", "test"));
        taskDtos.add(new TaskDto((long) 2, "test2", "test2"));

        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].content", is("test")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("test2")))
                .andExpect(jsonPath("$[1].content", is("test2")));
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "test", "test");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test")));

        verify(dbService, times(1)).getTask(1L);
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //Given
        Task task = new Task(1L, "test", "test");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));
        doNothing().when(dbService).deleteTask(task.getId());

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(task.getId());

    }

    @Test
    public void updateTaskTest() throws Exception{
        //Given
        Task task1 = new Task(2L, "test_update", "test_update");

         when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task1);
         Gson gson = new Gson();
         String jsonContent = gson.toJson(task1);

        //when & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.title", is("test_update")))
                .andExpect(jsonPath("$.content", is("test_update")));

    }

    @Test
    public void createTask() throws Exception {
        //Given
        Task task1 = new Task(1L, "test_update", "test_update");

        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task1);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}








