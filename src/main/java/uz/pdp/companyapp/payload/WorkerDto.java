package uz.pdp.companyapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "name bo'sh bo'lmasligi kerak !")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak !")
    private String phoneNumber;

    @NotNull(message = "addressId bo'sh bo'lmasligi kerak !")
    private Integer addressId;

    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak !")
    private Integer departmentId;
}
