package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoResponseData {
    private double imagesSimilarity;
    private boolean imagesVerified;
    private double videoSimilarity;
    private boolean videoVerified;
    private long timeTakenMs;
}
