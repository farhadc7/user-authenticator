package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SabtAhvalTokenResponse {
    private String access_token;
    private long expires_in;
    private String token_type;
    private String refresh_token;
    private String scope ;


}
