package ir.dc.userAuthenticator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoResponseData {
    @JsonProperty("images_similarity")
    private double imagesSimilarity;
    @JsonProperty("images_verified")
    private boolean imagesVerified;
    @JsonProperty("video_similarity")
    private double videoSimilarity;
    @JsonProperty("video_verified")
    private boolean videoVerified;
    @JsonProperty("time_taken_ms")
    private long timeTakenMs;
}
