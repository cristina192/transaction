package com.transaction.transaction.domain.transaction;


import com.transaction.transaction.domain.users.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @JoinColumn(name="comprador_Id")
    @ManyToOne
    private User comprador;

    @JoinColumn(name="vendedor_Id")
    @ManyToOne
    private User vendedor;

    private LocalDateTime timestamp;
}
