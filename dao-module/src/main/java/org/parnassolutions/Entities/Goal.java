package org.parnassolutions.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "goal", schema = "public")
@SequenceGenerator(name = "goal_id_seq", sequenceName = "goal_id_seq", allocationSize = 1)
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_id_seq")
    @Column(name = "goal_id")
    private Long goalId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double balance;

    @Column(name = "reach_date", nullable = true)
    private Date reachDate;
}
