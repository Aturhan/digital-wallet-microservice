package com.abdullah.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "wallet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Wallet {
        @Id
        private Long id;
        private Double amount;
        private String userId;
        private String fullName;
        private String emailAddress;
        private String location;

        @OneToMany(fetch = FetchType.LAZY ,mappedBy = "wallet",cascade = CascadeType.ALL)
        List<Expense> expenses;

        @OneToMany(fetch = FetchType.LAZY,mappedBy = "wallet",cascade = CascadeType.ALL)
        List<Budget> budgets;

        @CreationTimestamp
        private LocalDateTime createDate;

}
