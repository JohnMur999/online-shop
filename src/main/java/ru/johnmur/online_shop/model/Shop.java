package ru.johnmur.online_shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "\"Shop\"")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
