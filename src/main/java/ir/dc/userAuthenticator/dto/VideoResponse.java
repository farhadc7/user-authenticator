package ir.dc.userAuthenticator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoResponse {
    private VideoResponseData data;
    private Object meta; // Assuming Meta can be any object
    private Object error; // Assuming Error can be any object
}

