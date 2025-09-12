package ir.dc.userAuthenticator.token;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtTokenService {

    private static ObjectMapper mapper= new ObjectMapper();
    public static Long getUserId(String token) throws CustomException {
        extractToken(token);
        return null;
    }
    public static Map<String,Object> extractToken(String token) throws CustomException {
        try{
            token = token.replace("Bearer ","");
            String[] chunks= token.split("\\.");
            String header= new String(Base64.getDecoder().decode(chunks[0]));
            String payload= new String(Base64.getDecoder().decode(chunks[1]));

            var a=new String(Base64.getDecoder().decode(chunks[1]));
           var map = mapper.readValue(a, new TypeReference<HashMap<String,Object>>() {});
            return map;

        }catch (Exception e){
            throw new CustomException(e,ErrorCode.VALIDATION_CODE_ERROR);
        }
    }

    public static Optional<String> getClaim(String token,String claimName) throws CustomException {
        try{

           var map =extractToken(token);
            return Optional.ofNullable((String) map.getOrDefault(claimName,null));


        }catch (Exception e){
            throw new CustomException(e, ErrorCode.VALIDATION_CODE_ERROR);
        }
    }
}
