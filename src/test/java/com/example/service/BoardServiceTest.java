package com.example.service;

import com.example.model.Board;
import com.example.repository.BoardRepository;
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

public class BoardServiceTest {

    // Mocking a board repository
    @Mock
    private BoardRepository boardRepository;

    // Injecting a mock repository into board service
    @InjectMocks
    private BoardService boardService;

    // Initialize mocks before each test
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Board board = new Board();
        when(boardRepository.save(board)).thenReturn(board);

        Board savedBoard = boardService.save(board);

        assertNotNull(savedBoard);
        verify(boardRepository).save(board);
    }

    @Test
    public void testFindById_Found() {
        Board board = new Board();
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        Board foundBoard = boardService.findById(1L);

        assertNotNull(foundBoard);
        assertEquals(board, foundBoard);
        verify(boardRepository).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(boardRepository.findById(1L)).thenReturn(Optional.empty());

        Board foundBoard = boardService.findById(1L);

        assertNull(foundBoard);
        verify(boardRepository).findById(1L);
    }

    @Test
    public void testFindAll() {
        List<Board> boards = new ArrayList<>();
        boards.add(new Board());
        boards.add(new Board());
        when(boardRepository.findAll()).thenReturn(boards);

        List<Board> foundBoards = boardService.findAll();

        assertNotNull(foundBoards);
        assertEquals(2, foundBoards.size());
        verify(boardRepository).findAll();
    }

    @Test
    public void testDeleteById() {
        doNothing().when(boardRepository).deleteById(1L);

        boardService.deleteById(1L);

        verify(boardRepository).deleteById(1L);
    }
}
