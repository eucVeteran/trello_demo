package com.example.service;

import java.util.Optional;

import com.example.model.Board;
import com.example.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class BoardServiceTest {

    @Test
    void save() {
    }

    @Test
    void findById() {
        var board = new Board();

        var mBoardRepository = mock(BoardRepository.class);
        Mockito.when(mBoardRepository.findById(1L)).thenReturn(Optional.of(board));

        var subject = new BoardService(mBoardRepository);

        var result = subject.findById(1L);

        assertEquals(board, result);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}
