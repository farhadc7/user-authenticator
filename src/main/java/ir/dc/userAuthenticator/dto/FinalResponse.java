package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public  class FinalResponse {
    private SabtAhvalImageResponseWrapper imageResponse;
    private SabtAhvalResponse sabtAhvalResponse;


}