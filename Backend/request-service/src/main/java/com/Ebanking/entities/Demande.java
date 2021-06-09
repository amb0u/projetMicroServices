package com.Ebanking.entities;

import lombok.*;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.Date;
        import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Demande implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private Dstatus status;
    private String message;
    private long clientId;
    private String type;
    @NotNull
    private Long agencyId;

    public Demande(Date date, Dstatus status , String m , long clientId, String type, Long agencyId ) {
        this.clientId = clientId;
        this.message=m;
        this.status=status;
        this.dateDemande=date;
        this.type=type;
        this.agencyId=agencyId;
    }
}

