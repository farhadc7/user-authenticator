package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoRequestDto {
    private String nationalCode;
    private String mobileNumber;
    private String birthDate;
    private Long movieId;
}
