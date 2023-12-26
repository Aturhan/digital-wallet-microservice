package com.abdullah.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;



@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String category;
    private String description;
    private Double budgetAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    @JsonBackReference
    private Wallet wallet;

}
