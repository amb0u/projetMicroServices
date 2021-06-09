package org.Ebanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDay {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique=true)
    private String name;
    @NotNull
    @Column(unique=true)
    @Temporal(TemporalType.DATE)
    private Date date;
}
