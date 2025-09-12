package ir.dc.userAuthenticator.exceptions;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getReasonPhraseFa());
        this.errorCode = errorCode;
    }

    public CustomException(Exception e, ErrorCode errorCode) {
        super(errorCode.getReasonPhraseFa(),e);
        this.errorCode= errorCode;
    }

    public CustomException(String message) {
        this.message= message;
    }
}
