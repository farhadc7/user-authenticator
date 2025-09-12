package ir.dc.userAuthenticator.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private int code;
    private String desc;

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
