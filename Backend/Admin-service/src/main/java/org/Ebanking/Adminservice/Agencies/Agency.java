package org.Ebanking.Adminservice.Agencies;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Agency {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String fax;
    private Date CreationDate;
    private Long AgencyChefId;
}
