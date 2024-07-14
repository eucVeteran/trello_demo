package com.example.controller;

import com.example.model.Card;
import com.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * @author Azizbek Toshpulatov
 */
@RestController
@RequestMapping("/cards")
public class CardController {
    public CardController(CardService _cardService) {
        cardService = _cardService;
    }

    private final CardService cardService;

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.save(card);
    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable Long id) {
        return cardService.findById(id);
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.findAll();
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card cardDetails) {
        Card card = cardService.findById(id);
        if (card != null) {
            card.setName(cardDetails.getName());
            card.setDescription(cardDetails.getDescription());
            return cardService.save(card);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteById(id);
    }
}
