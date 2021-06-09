package org.Ebanking.agencyservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Agency {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column( unique = true)
    private String name;
    @NotNull
    @Column( unique = true)
    private String address;
    @NotNull
    @Column( unique = true)
    private String phoneNumber;
    @Column( unique = true)
    private String email;
    @NotNull
    @Column( unique = true)
    private String fax;
    @Temporal ( TemporalType.DATE )
    private Date CreationDate;
    private Long AgencyChefId;
}
