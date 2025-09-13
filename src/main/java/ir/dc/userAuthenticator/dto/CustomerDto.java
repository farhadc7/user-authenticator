package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String nationalCode;
    private String mobileNumber;
    private String birthDate;
    private String uniqueCode;
    private Long movieId;
    private String imagePath;
    private LocalDateTime issuedDate;
    private boolean conditionAccepted=false;


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

    public void sabtAhvalSetter(SabtAhvalResponse r){
        this.firstName=r.getFirstName();
        this.lastName=r.getLastName();
        this.liveStatus=r.isLiveStatus();
        this.gender=r.getGender();
        this.identificationNo=r.getIdentificationNo();
        this.fatherName= r.getFatherName();
        this.shenasnameSerial=r.getShenasnameSerial();
        this.shenasnameSeri=r.getShenasnameSeri();
        this.officeName= r.getOfficeName();
        this.officeCode=r.getOfficeCode();
    }

}
