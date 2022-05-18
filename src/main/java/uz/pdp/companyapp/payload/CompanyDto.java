package uz.pdp.companyapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.companyapp.entity.Address;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "corpName bo'sh bo'lmasligi kerak !")
    private String corpName;

    @NotNull(message = "corpName bo'sh bo'lmasligi kerak !")
    private String directorName;

    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private Integer homeNumber;
}
