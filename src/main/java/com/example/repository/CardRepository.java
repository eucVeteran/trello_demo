package com.example.repository;

import com.example.model.Card;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Azizbek Toshpulatov
 */
public interface CardRepository extends CrudRepository<Card, Long> {
}
