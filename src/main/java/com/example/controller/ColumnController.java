package com.example.controller;

import com.example.model.Column;
import com.example.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/columns")
public class ColumnController {
    @Autowired
    private ColumnService columnService;

    @PostMapping
    public Column createColumn(@RequestBody Column column) {
        return columnService.save(column);
    }

    @GetMapping("/{id}")
    public Column getColumn(@PathVariable Long id) {
        return columnService.findById(id);
    }

    @GetMapping
    public List<Column> getAllColumns() {
        return columnService.findAll();
    }

    @PutMapping("/{id}")
    public Column updateColumn(@PathVariable Long id, @RequestBody Column columnDetails) {
        Column column = columnService.findById(id);
        if (column != null) {
            column.setName(columnDetails.getName());
            return columnService.save(column);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Column not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable Long id) {
        columnService.deleteById(id);
    }
}
