package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public  class SabtAhvalResponse {
    private String nationalId;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private boolean liveStatus;
    private String identificationNo;
    private String fatherName;
    private String shenasnameSerial;
    private String shenasnameSeri;
    private String officeName;
    private String officeCode;
}