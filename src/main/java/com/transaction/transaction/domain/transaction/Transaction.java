package com.transaction.transaction.domain.transaction;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  BigDecimal amount;

    @JoinColumn(name="comprador_id")
    @ManyToOne
    private User comprador;

    @JoinColumn(name="vendedor_id")
    @ManyToOne
    private User vendedor;

    private LacalDateTime timestamp;
}
