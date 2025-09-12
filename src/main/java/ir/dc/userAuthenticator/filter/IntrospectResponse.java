package ir.dc.userAuthenticator.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntrospectResponse {
        @JsonProperty("active")
        private boolean active;

        @JsonProperty("sub")
        private String sub;

        @JsonProperty("aud")
        private String[] aud;

        @JsonProperty("nbf")
        private long nbf;

        @JsonProperty("scope")
        private String scope;

        @JsonProperty("iss")
        private String iss;

        @JsonProperty("exp")
        private long exp;

        @JsonProperty("iat")
        private long iat;

        @JsonProperty("jti")
        private String jti;

        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("username")
        private String username;

        @JsonProperty("token_type")
        private String tokenType;
        private String clientName;

}
