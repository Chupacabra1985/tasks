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

   @Test
   public void mapTrelloBoardsDtoNullTest(){
       List<TrelloBoard> trelloBoardListTest = null;

       if (trelloBoardListTest == null){
           trelloBoardListTest = new ArrayList<TrelloBoard>();
       }

       List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoardListTest);
       assertEquals(0, result.size());
   }

   @Test
   public void mapTrelloBoardsNullTest() {
       List<TrelloBoardDto> trelloBoardDtoTest = null;

       if (trelloBoardDtoTest == null) {
           trelloBoardDtoTest = new ArrayList<TrelloBoardDto>();
       }

       List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtoTest);
       assertEquals(0, result.size());
   }

   @Test
    public void mapToListNullTest(){
       List<TrelloListDto> trelloListDtoListTest = null;

       if(trelloListDtoListTest == null){
           trelloListDtoListTest = new ArrayList<TrelloListDto>();
       }

       List<TrelloList> result = trelloMapper.mapToList(trelloListDtoListTest);
       assertEquals(0, result.size());
   }

   @Test
   public void mapToListDtoNullTest(){
        List<TrelloList> trelloListsTest = null;

        if(trelloListsTest == null){
            trelloListsTest = new ArrayList<TrelloList>();
        }

        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloListsTest);
        assertEquals(0, result.size());
   }

   @Test
   public void mapToCardDtoNullTest(){
       TrelloCard trelloCardTest = null;

       if(trelloCardTest == null){
           trelloCardTest = new TrelloCard("null", "null", "null", "null");
       }

       TrelloCardDto result = trelloMapper.mapToCardDto(trelloCardTest);
       assertEquals("null", result.getListId());
   }

   @Test
   public void mapToCardNullTest(){
       TrelloCardDto trelloCardDtoTest = null;

       if(trelloCardDtoTest == null){
           trelloCardDtoTest = new TrelloCardDto("null", "null", "null", "null");
       }

       TrelloCard result = trelloMapper.mapToCard(trelloCardDtoTest);
       assertEquals("null", result.getListId());
   }

}
