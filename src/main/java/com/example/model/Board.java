package com.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * @author Azizbek Toshpulatov
 */
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // для ID, автогенерация
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")   // foreign key
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Column> columns;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
