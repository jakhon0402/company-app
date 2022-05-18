package uz.pdp.companyapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private String name;

    private String phoneNumber;

    private Integer addressId;

    private Integer departmentId;
}
