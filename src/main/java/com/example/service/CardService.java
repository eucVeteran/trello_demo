package com.example.service;

import com.example.model.Card;
import com.example.model.Column;
import com.example.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Azizbek Toshpulatov
 */
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public List<Card> findAll() {
        List<Card> cards = new ArrayList<Card>();
        cardRepository.findAll().forEach(cards::add);
        return cards;
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}