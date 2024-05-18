package org.parnassolutions.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "expense", schema = "public")
//@PrimaryKeyJoinColumn(name = "expenceId")
public class Expense extends Operation {
    @Column(nullable = false)
    private String category;
}
