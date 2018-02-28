package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto){
        if(trelloBoardDto == null){return new ArrayList<>();}
        return trelloBoardDto.stream()
                .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(), mapToList(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards){
        if(trelloBoards == null){ return new ArrayList<>();}
        return trelloBoards.stream()
                .map(trelloBoard ->
                         new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToListDto(trelloBoard.getLists())))
                .collect(toList());
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto){
        if(trelloListDto == null){ return new ArrayList<>();}
        return trelloListDto.stream()
                .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloLists){
        if(trelloLists == null){ return new ArrayList<>();}
        return trelloLists.stream()
                .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(), trelloList.isClosed()))
                .collect(toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard){
        if(trelloCard == null){ return null;}
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto){
        if(trelloCardDto == null){ return null;}
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
