package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

   @Test
   public void mapToBoardsTest() {
       //Given
       List<TrelloListDto> trelloListsTest = new ArrayList<>();
       trelloListsTest.add(new TrelloListDto("1", "test_list", false));

       List<TrelloBoardDto> trelloBoardsTest = new ArrayList<>();
       trelloBoardsTest.add(new TrelloBoardDto("1", "test_board", trelloListsTest));

       //When
       List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardsTest);

       //Then
       assertEquals(1, result.size());
       assertEquals("test_board", result.get(0).getName());
       assertEquals("1", result.get(0).getId());
       assertEquals(1, result.get(0).getLists().size());
   }

   @Test
   public void mapToBoardsDtoTest(){
       //Given
       List<TrelloList> trelloListsTest = new ArrayList<>();
       trelloListsTest.add(new TrelloList("1", "test_list", false));

       List<TrelloBoard> trelloBoardsTest = new ArrayList<>();
       trelloBoardsTest.add(new TrelloBoard("1", "test_board", trelloListsTest));

       //When
       List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoardsTest);

       //Then
       assertEquals(1, result.size());
       assertEquals("test_board", result.get(0).getName());
       assertEquals("1", result.get(0).getId());
       assertEquals(1, result.get(0).getLists().size());
   }

   @Test
   public void mapToListTest(){
       //Given
       List<TrelloListDto> trelloListsTest = new ArrayList<>();
       trelloListsTest.add(new TrelloListDto("1", "new_list", false));

       //When
       List<TrelloList> result = trelloMapper.mapToList(trelloListsTest);

       //Then
       assertEquals(1, result.size());
       assertEquals("new_list", result.get(0).getName());
       assertEquals("1", result.get(0).getId());
       assertFalse(result.get(0).isClosed());
   }

   @Test
   public void mapToListDtoTest(){
       //Given
       List<TrelloList> trelloListsTest = new ArrayList<>();
       trelloListsTest.add(new TrelloList("1", "new_list", false));

       //When
       List<TrelloListDto> result = trelloMapper.mapToListDto(trelloListsTest);

       //Then
       assertEquals(1, result.size());
       assertEquals("new_list", result.get(0).getName());
       assertEquals("1", result.get(0).getId());
       assertFalse(result.get(0).isClosed());
   }

   @Test
   public void mapToCardDtoTest(){
       //Given
       TrelloCard trelloCardTest = new TrelloCard("new_name", "new_description", "new_pos", "1");

       //When
       TrelloCardDto result = trelloMapper.mapToCardDto(trelloCardTest);

       //Then
       assertEquals("new_name", result.getName());
       assertEquals("new_description", result.getDescription());
       assertEquals("new_pos", result.getPos());
       assertEquals("1", result.getListId());
   }

   @Test
   public void mapToCardTest(){
       //Given
       TrelloCardDto trelloCardTest = new TrelloCardDto("new_name", "new_description", "new_pos", "1");

       //When
       TrelloCard result = trelloMapper.mapToCard(trelloCardTest);

       //Then
       assertEquals("new_name", result.getName());
       assertEquals("new_description", result.getDescription());
       assertEquals("new_pos", result.getPos());
       assertEquals("1", result.getListId());
   }

   @Test(expected = NullPointerException.class)
   public void mapTrelloBoardsDtoNullTest(){
          List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(null);
   }

   @Test(expected = NullPointerException.class)
   public void mapTrelloBoardsNullTest(){
       List<TrelloBoard> result = trelloMapper.mapToBoards(null);
   }

   @Test(expected = NullPointerException.class)
    public void mapToListNullTest(){
       List<TrelloList> result = trelloMapper.mapToList(null);
   }

   @Test(expected = NullPointerException.class)
   public void mapToListDtoNullTest(){
        List<TrelloListDto> result = trelloMapper.mapToListDto(null);
   }

   @Test(expected = NullPointerException.class)
   public void mapToCardDtoNullTest(){
       TrelloCardDto result = trelloMapper.mapToCardDto(null);
   }

   @Test(expected = NullPointerException.class)
   public void mapToCardNullTest(){
       TrelloCard result = trelloMapper.mapToCard(null);
   }

}
