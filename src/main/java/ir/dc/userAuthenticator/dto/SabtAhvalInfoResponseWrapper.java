package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SabtAhvalInfoResponseWrapper {
    private Result result;
    private Status status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Result {
        private Object data;
        private Status status;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Status {
        private int statusCode;
        private String message;
    }
}