package ir.dc.userAuthenticator.log;



import ir.dc.userAuthenticator.dto.UserDetailsImpl;
import ir.dc.userAuthenticator.util.ContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LOGGER {
    public static void error(String serviceName,String s,Exception e)   {
        UserDetailsImpl user= null;
        try{
             user = ContextUtil.getUser();
        }catch (Exception er){
            log.warn("LOGGER:there is no token available to get user from it.(auth services and callback services are samples)" );
        }
        String name=user==null?"--":user.getMobileNumber();
        log.error(serviceName+" :"+name+" :"+s,e);
    }
    public static void info(String serviceName,String s)   {
        UserDetailsImpl user= null;
        try{
             user =ContextUtil.getUser();
        }catch (Exception er){
            log.warn("LOGGER:there is no token available to get user from it.(auth services and callback services are samples)" );

        }
        String name="--";
        if(user!=null && user.getMobileNumber().length()>7){
            name = user.getMobileNumber().substring(7);
        }
        log.info(serviceName+" :"+name+" :"+ s);
    }
    public static void debug(String serviceName,String s)   {
        UserDetailsImpl user= null;
        try{
             user =ContextUtil.getUser();
        }catch (Exception er){
            log.warn("LOGGER:there is no token available to get user from it.(auth services and callback services are samples)" );
        }
        String name=user==null?"--":user.getMobileNumber();
        log.info(serviceName+" :"+name+" :"+s);
    }
}
