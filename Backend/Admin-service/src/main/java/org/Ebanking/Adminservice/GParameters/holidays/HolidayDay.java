package org.Ebanking.Adminservice.GParameters.holidays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDay {
    private Long id;
    private String name;
    private String date;
}
