package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTestSuite {

    @Autowired
    private TrelloService trelloService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        //When
        List<TrelloBoardDto> boards = trelloService.fetchTrelloBoards();

        //Then
        boolean hasKodillaX = false;
        for(TrelloBoardDto board : boards) {
            if(board.getName().equals("Kodilla X")) {
                hasKodillaX = true;
            }
        }
        assertTrue(hasKodillaX);

    }

    @Test
    public void testCreatedTrelloCard() {
        //Given
        String cardNameUniqueSuffix = "" + LocalTime.now();
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test card " + cardNameUniqueSuffix, "This is a test card", "2", "5cd7604306b6b92422caf3dc");

        //When
        CreatedTrelloCardDto newCard = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertEquals("Test card " + cardNameUniqueSuffix, newCard.getName());
    }
}