//package ir.dc.userAuthenticator.service;
//
//import com.inifinity.ecommerce.dto.user.UserCheck;
//import com.inifinity.ecommerce.entity.UserEntity;
//import com.inifinity.ecommerce.entity.UserLoginHistory;
//import com.inifinity.ecommerce.enums.NotifyActionType;
//import com.inifinity.ecommerce.enums.SuspiciousActivityType;
//import com.inifinity.ecommerce.exception.CustomCredentialException;
//import com.inifinity.ecommerce.exception.ErrorCode;
//import com.inifinity.ecommerce.exception.GeneralException;
//import com.inifinity.ecommerce.log.LOGGER;
//import com.inifinity.ecommerce.repository.redis.RedisTempRepo;
//import com.inifinity.ecommerce.service.*;
//import com.inifinity.ecommerce.service.auth.dto.PreSignInAuthRequest;
//import com.inifinity.ecommerce.service.notification.NotifyHandler;
//import com.inifinity.ecommerce.utils.InputUtil;
//import com.inifinity.ecommerce.utils.JwtUtil;
//import com.inifinity.ecommerce.utils.SessionEncryptor;
//import ir.dc.userAuthenticator.entity.UserEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class AuthUtil {
//
//    protected final UserActivityLimitService userActivityLimitService;
//    protected final InputUtil inputUtil;
//    protected final PatternValidation patternValidation;
//    protected final UserService userService;
//    protected final RedisTempRepo redisTempRepo;
//    protected final NotifyHandler notificationService;
//    protected final RecaptchaServiceCustom recaptchaServiceCustom;
//    private final AuthenticationManager authenticationManager;
//    public final UserLoginHistoryService userLoginHistoryService;
//    public final JwtUtil jwtUtil;
//    public final UserDetailsServiceImpl userDetailsService;
//    public final SessionEncryptor sessionUtil;
//
//    @Autowired
//    private Environment environment;
//
//    public AuthUtil(UserActivityLimitService userActivityLimitService,
//                    InputUtil inputUtil,
//                    PatternValidation patternValidation,
//                    UserService userService,
//                    RedisTempRepo redisTempRepo,
//                    NotifyHandler notificationService,
//                    RecaptchaServiceCustom recaptchaServiceCustom,
//                    AuthenticationManager authenticationManager, UserLoginHistoryService userLoginHistoryService, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, SessionEncryptor sessionUtil) {
//        this.userActivityLimitService = userActivityLimitService;
//        this.inputUtil = inputUtil;
//        this.patternValidation = patternValidation;
//        this.userService = userService;
//        this.redisTempRepo = redisTempRepo;
//        this.notificationService = notificationService;
//        this.recaptchaServiceCustom = recaptchaServiceCustom;
//        this.authenticationManager = authenticationManager;
//        this.userLoginHistoryService = userLoginHistoryService;
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//        this.sessionUtil = sessionUtil;
//    }
//
//    protected Optional<UserEntity> getUser(String mobileNumber){
//       return userService.findByUsername(mobileNumber);
//
//    }
////    @Transactional(propagation= Propagation.REQUIRES_NEW)
////    public UserEntity getOrCreateByMobileNumber(String mobileNumber) throws GeneralException {
////        var user = userService.findByMobileNumber(mobileNumber);
////        if (user.isPresent()) {
////            log.debug("user is present:"+user.get().getMobileNumber()+"- thread is : "+Thread.currentThread().getName());
////            return user.get();
////        } else {
////            synchronized (this){
////                if(user.isPresent()){
////                    log.debug("user is present in synchronized : "+user.get().getMobileNumber()+"- thread is : "+Thread.currentThread().getName());
////                    var u= user.get();
////                    log.debug("user is present in synchronized finished: "+user.get().getMobileNumber()+"- thread is : "+Thread.currentThread().getName());
////                    return u;
////                }
////                return userService.createCustomerByMobileNumber(mobileNumber,"notSet");
////            }
////
////        }
////    }
//
////    @Transactional
////    public UserEntity createClientByUsername(String username) throws GeneralException {
////        var user = userService.findByUsername(username);
////        if (user.isPresent()) {
////            log.debug("user is present:"+user.get().getUsername()+"- thread is : "+Thread.currentThread().getName());
////            throw new GeneralException(ErrorCode.USERNAME_ALREADY_EXISTS);
////        } else {
////            synchronized (this){
////                if(user.isPresent()){
////                    log.debug("user is present in synchronized : "+user.get().getUsername()+"- thread is : "+Thread.currentThread().getName());
////                    var u= user.get();
////                    log.debug("user is present in synchronized finished: "+user.get().getUsername()+"- thread is : "+Thread.currentThread().getName());
////                    return u;
////                }
////                return userService.createClientByUsername(username);
////            }
////
////        }
////    }
//
//
//
//    protected void validate(String mobileNumber, SuspiciousActivityType type){
//        userActivityLimitService.updateUserActivityLimit(mobileNumber,type);
//    }
//
//    protected String sanitizeMobileNumber(String mob){
//        String mobileNumber = inputUtil.trimMobileNumber(mob);
//        patternValidation.validateMobileNumber(mobileNumber);
//        return mobileNumber;
//    }
//
//
//    protected Authentication authenticate(String username, String password)   {
//
//        try{
//            Authentication authentication = authenticationManager.
//                    authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            return authentication;
//        }catch (Exception e){
//            /*todo: test it.*/
//            LOGGER.error("authenticate","exceptin in authentication",null);
//            updateUserActivityLimit(username,SuspiciousActivityType.OTP_VALIDATION);
//            throw new CustomCredentialException(ErrorCode.BAD_CREDENTIALS_ERROR,username, SuspiciousActivityType.OTP_VALIDATION);
//
//        }
//    }
//    public void updateUserActivityLimit(String mobileNumber, SuspiciousActivityType type){
//        userActivityLimitService.updateUserActivityLimit(mobileNumber, type);
//    }
//    public void checkUserLimitationStatus(String mobileNumber){
//        userActivityLimitService.throwExceptionIfUserIsBlocked(mobileNumber);
//    }
//
//
//    public String getCaptcha(String hiddenCaptcha) {
//        return recaptchaServiceCustom.getCaptchaFromCache(hiddenCaptcha);
//    }
//
//    public void evictCaptcha(String hiddenCaptcha) {
//        recaptchaServiceCustom.evictCaptcha(hiddenCaptcha);
//    }
//
//    public String generateSignInCode(String mobileNumber) {
//        return redisTempRepo.generateSignInCode(mobileNumber);
//    }
//
//    public String generateChangePasswordCode(String mobileNumber) {
//        return redisTempRepo.generateChangePasswordCode(mobileNumber);
//    }
//
//    public void notifyMessage(NotifyActionType signIn, String mobileNumber, String otp) {
//        notificationService.notifyMessage(signIn,mobileNumber,otp);
//    }
//
//    public String getSignInCode(String mobileNumber) {
//        return redisTempRepo.getSignInCode(mobileNumber);
//    }
//    public String getChangePasswordCode(String mobileNumber) {
//        return redisTempRepo.getChangePasswordCode(mobileNumber);
//    }
//
//    public void removeFromRedis(String phone) {
//        redisTempRepo.removeCode(phone);
//    }
//
//    public String encryptSession(UserLoginHistory loginHistory) {
//        return sessionUtil.encrypt(loginHistory);
//    }
//
//    public Optional<UserEntity> findByMobileNumber(String mobileNumber) {
//        return userService.findByMobileNumber(mobileNumber);
//    }
//
//    public UserDetails loadUserByUsername(String mobileNumber) {
//        return userDetailsService.loadUserByUsername(mobileNumber);
//    }
//
//    public String generateJwtToken(Authentication authentication) {
//        return jwtUtil.generateJwtToken(authentication);
//    }
//
//    public UserLoginHistory saveUserLogin(UserEntity user,boolean activation) {
//        return userLoginHistoryService.save(user,activation);
//    }
//
//    public void saveUser(UserEntity user) {
//        userService.saveUser(user);
//    }
//
//    public void validatePassword(String newPassword) {
//        patternValidation.validatePassword(newPassword);
//    }
//
//    public void validateSamePassword(String pass, String rep) {
//        patternValidation.validateSamePassword(pass,rep);
//    }
//
//    public UserEntity findById(Long userId) {
//        return userService.findById(userId);
//    }
//
//    public void removeChangePasswordCodeFromRedis(String mobileNumber) {
//        redisTempRepo.removeChangePasswordCode(mobileNumber);
//    }
//
//    public void sendBackCodeInTest(UserCheck res, String code) {
//
//        if (
//        Arrays.asList(environment.getActiveProfiles()).contains("prod-env")
//        ) {
//            res.setMessage(code);
//        }
//    }
//
//    public boolean checkCaptcha( PreSignInAuthRequest signUpRequest) {
//        if((Arrays.asList(environment.getActiveProfiles()).contains("prod-env") || Arrays.asList(environment.getActiveProfiles()).contains("test-env"))
//                && signUpRequest.getHiddenCaptcha().equals("11111")){
//            if(signUpRequest.getCaptcha().equals("11111")){
//                return true;
//            }
//        }
//        String actualCaptcha = getCaptcha(signUpRequest.getHiddenCaptcha());
//        if(!actualCaptcha.equals("not-set") && signUpRequest.getCaptcha().equals(actualCaptcha)){
//            evictCaptcha(signUpRequest.getHiddenCaptcha());
//            return true;
//        }
//        return  false;
//    }
//}
