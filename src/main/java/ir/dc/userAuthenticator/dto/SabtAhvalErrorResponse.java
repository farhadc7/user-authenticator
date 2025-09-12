package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SabtAhvalErrorResponse {
    private String code;
    private String msg;
    private List<Violation> violations;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Violation{
        private String fieldName;
        private String message;
    }

}
