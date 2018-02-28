package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrelloServiceTest {

    @Test
    public void fetchTrelloBoardsTest(){
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        AdminConfig adminConfigMock = mock(AdminConfig.class);
        TrelloClient trelloClientMock = mock(TrelloClient.class);
        SimpleEmailService simpleEmailServiceMock = mock(SimpleEmailService.class);
        when(trelloClientMock.getTrelloBoards()).thenReturn(trelloBoards);

        TrelloService trelloService = new TrelloService(adminConfigMock, trelloClientMock, simpleEmailServiceMock);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        Mockito.verify(trelloClientMock, Mockito.times(1)).getTrelloBoards();
        assertEquals(1, result.size());
    }

    @Test
    public void createdTrelloCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");

        AdminConfig adminConfigMock = mock(AdminConfig.class);
        TrelloClient trelloClientMock = mock(TrelloClient.class);
        SimpleEmailService simpleEmailServiceMock = mock(SimpleEmailService.class);

        TrelloService trelloService = new TrelloService(adminConfigMock, trelloClientMock, simpleEmailServiceMock);

        //When
        CreatedTrelloCardDto result = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        Mockito.verify(trelloClientMock, Mockito.times(1)).createNewCard(ArgumentMatchers.any());
    }

}