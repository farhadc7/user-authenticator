package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoValidationResponse {
    private boolean isSimilar=false;
    private double videoSimilarityThreshold;
    private double imagesSimilarity;
    private boolean imagesVerified;
    private double videoSimilarity;
    private boolean videoVerified;
    private long timeTakenMs;

}
