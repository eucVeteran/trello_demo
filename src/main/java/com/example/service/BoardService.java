package com.example.service;

import com.example.model.Board;
import com.example.model.User;
import com.example.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Azizbek Toshpulatov
 */
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public List<Board> findAll() {
        List<Board> boards = new ArrayList<Board>();
        boardRepository.findAll().forEach(boards::add);
        return boards;
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
