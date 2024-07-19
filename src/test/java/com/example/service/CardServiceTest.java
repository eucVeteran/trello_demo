package com.example.service;

import com.example.model.Card;
import com.example.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Card card = new Card();
        when(cardRepository.save(card)).thenReturn(card);

        Card savedCard = cardService.save(card);

        assertNotNull(savedCard);
        verify(cardRepository).save(card);
    }

    @Test
    public void testFindById_Found() {
        Card card = new Card();
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        Card foundCard = cardService.findById(1L);

        assertNotNull(foundCard);
        assertEquals(card, foundCard);
        verify(cardRepository).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(cardRepository.findById(1L)).thenReturn(Optional.empty());

        Card foundCard = cardService.findById(1L);

        assertNull(foundCard);
        verify(cardRepository).findById(1L);
    }

    @Test
    public void testFindAll() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card());
        cards.add(new Card());
        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> foundCards = cardService.findAll();

        assertNotNull(foundCards);
        assertEquals(2, foundCards.size());
        verify(cardRepository).findAll();
    }

    @Test
    public void testDeleteById() {
        doNothing().when(cardRepository).deleteById(1L);

        cardService.deleteById(1L);

        verify(cardRepository).deleteById(1L);
    }
}
