package org.parnassolutions.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account", schema = "public")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private Integer accountId;

    @Column(name="budget")
    private Long budget;

    @Column(name="income")
    private Long income;

    @Column(name="expense")
    private Long expense;

}
