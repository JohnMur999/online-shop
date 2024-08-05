package ru.johnmur.online_shop.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String gender;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String role;

    @PrePersist
    protected void onCreate() {
        if (this.role == null) {
            this.role = "customer";
        }
    }
}
