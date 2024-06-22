package com.example.repository;

import com.example.model.Board;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Azizbek Toshpulatov
 */
public interface BoardRepository extends CrudRepository<Board, Long> {
}
