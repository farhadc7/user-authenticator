package ir.dc.userAuthenticator.util;

import ir.dc.userAuthenticator.dto.UserDetailsImpl;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import org.springframework.security.core.context.SecurityContextHolder;


public class ContextUtil {
    public static UserDetailsImpl getUser() throws CustomException {
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try{
            if(principal==null || !(principal instanceof UserDetailsImpl)){
                throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
            }
            return (UserDetailsImpl)principal;

        }catch (Exception e){
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);
        }
    }
    public static void checkUserValidation(Long userId) throws CustomException {
        if(!userId.equals(getUser().getId())){
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);

        }
    }

    public static void checkByUsername(String username) throws CustomException {
        if(!username.equals(getUser().getMobileNumber())){
            throw new CustomException(ErrorCode.BAD_CREDENTIALS_ERROR);

        }
    }

    public static void checkUserValidationByMobileNumber(String mobileNumber) {
        if(!getUser().getMobileNumber().equals(mobileNumber)){
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
