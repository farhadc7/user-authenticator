package ir.dc.userAuthenticator.entity;

import ir.dc.userAuthenticator.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueCode;
    private String mobileNumber;
    private String nationalCode;
    private String birthdate;

    @ManyToOne
    private Movie movie;
    private String imagePath;


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

    public CustomerEntity(String mobileNumber,String uniqueCode, String nationalCode, String birthdate, Movie movie) {
        this.uniqueCode=uniqueCode;
        this.mobileNumber = mobileNumber;
        this.nationalCode = nationalCode;
        this.birthdate = birthdate;
        this.movie = movie;


    }

    public void sabtAhvalSetter(CustomerDto r){
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
