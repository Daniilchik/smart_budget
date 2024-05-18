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
@Table(name = "saving", schema = "public")
//@PrimaryKeyJoinColumn(name = "savingId")
public class Saving extends Operation {
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;
}
