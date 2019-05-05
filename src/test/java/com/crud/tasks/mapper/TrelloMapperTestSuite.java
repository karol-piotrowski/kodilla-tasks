package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        List<TrelloListDto> workTrelloListDtos = new ArrayList<>();
        List<TrelloListDto> privateTrelloListDtos = new ArrayList<>();
        workTrelloListDtos.add(new TrelloListDto("1", "Backlog list", true));
        workTrelloListDtos.add(new TrelloListDto("2", "Done list", false));
        privateTrelloListDtos.add(new TrelloListDto("1", "To do list", false));
        privateTrelloListDtos.add(new TrelloListDto("2", "Done list", false));
        trelloBoardDtos.add(new TrelloBoardDto("1", "Work projects", workTrelloListDtos));
        trelloBoardDtos.add(new TrelloBoardDto("2", "Private projects", workTrelloListDtos));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("Work projects", trelloBoards.get(0).getName());
        assertEquals(2, trelloBoards.get(0).getLists().size());
        assertEquals("2", trelloBoards.get(1).getId());
        assertEquals("Private projects", trelloBoards.get(1).getName());
        assertEquals(2, trelloBoards.get(1).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> workTrelloLists = new ArrayList<>();
        List<TrelloList> privateTrelloLists = new ArrayList<>();
        workTrelloLists.add(new TrelloList("1", "Backlog list", true));
        workTrelloLists.add(new TrelloList("2", "Done list", false));
        privateTrelloLists.add(new TrelloList("1", "To do list", false));
        privateTrelloLists.add(new TrelloList("2", "Done list", false));
        trelloBoards.add(new TrelloBoard("1", "Work projects", workTrelloLists));
        trelloBoards.add(new TrelloBoard("2", "Private projects", workTrelloLists));
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals("1", trelloBoardDtos.get(0).getId());
        assertEquals("Work projects", trelloBoardDtos.get(0).getName());
        assertEquals(2, trelloBoardDtos.get(0).getLists().size());
        assertEquals("2", trelloBoardDtos.get(1).getId());
        assertEquals("Private projects", trelloBoardDtos.get(1).getName());
        assertEquals(2, trelloBoardDtos.get(1).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "Backlog list", true));
        trelloListDtos.add(new TrelloListDto("2", "Done list", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("Backlog list", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
        assertEquals("2", trelloLists.get(1).getId());
        assertEquals("Done list", trelloLists.get(1).getName());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Backlog list", true));
        trelloLists.add(new TrelloList("2", "Done list", false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals("1", trelloListDtos.get(0).getId());
        assertEquals("Backlog list", trelloListDtos.get(0).getName());
        assertTrue(trelloListDtos.get(0).isClosed());
        assertEquals("2", trelloListDtos.get(1).getId());
        assertEquals("Done list", trelloListDtos.get(1).getName());
        assertFalse(trelloListDtos.get(1).isClosed());
        assertEquals(2, trelloListDtos.size());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test card 1", "This card was created to test the TrelloMapper class", "3", "Test List");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Test card 1", trelloCardDto.getName());
        assertEquals("This card was created to test the TrelloMapper class", trelloCardDto.getDescription());
        assertEquals("3", trelloCardDto.getPos());
        assertEquals("Test List", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test card 1", "This card was created to test the TrelloMapper class", "3", "Test List");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Test card 1", trelloCard.getName());
        assertEquals("This card was created to test the TrelloMapper class", trelloCard.getDescription());
        assertEquals("3", trelloCard.getPos());
        assertEquals("Test List", trelloCard.getListId());
    }
}