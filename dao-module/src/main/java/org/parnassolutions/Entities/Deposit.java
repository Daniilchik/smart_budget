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
@Table(name = "deposit", schema = "public")
//@PrimaryKeyJoinColumn(name = "depositId")
public class Deposit extends Operation {
    @Column(nullable = false)
    private String source;
}
