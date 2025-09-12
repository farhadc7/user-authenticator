package ir.dc.userAuthenticator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;


public enum ErrorCode {
    NOT_FOUND(404, HttpStatus.Series.CLIENT_ERROR, "Not Found","موردی یافت نشد!"),
    WALLET_NOT_FOUND(40401,HttpStatus.Series.CLIENT_ERROR,"wallet not found","کیف پولی یافت نشد."+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    USER_NOT_FOUND(40402,HttpStatus.Series.CLIENT_ERROR,"user not found","کابر یافت نشد. "+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    BANK_NOT_FOUND(40403,HttpStatus.Series.CLIENT_ERROR,"bank not found","بانک یافت نشد. "+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    BANK_ACCOUNT_NOT_FOUND(40404,HttpStatus.Series.CLIENT_ERROR,"bank account not found","شماره حساب یافت نشد. "+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    RATE_NOT_FOUND(40406,HttpStatus.Series.CLIENT_ERROR,"rate not found, define rate then try again","نرخ درخواستی یافت نشد. "+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    TICKET_NOT_FOUND(40407,HttpStatus.Series.CLIENT_ERROR,"ticket not found","تیکتی یافت نشد"),
    TICKET_DETAIL_NOT_FOUND(40408,HttpStatus.Series.CLIENT_ERROR,"ticket question not found","برای این تیکت پرسشی یافت نشد." ),
    NOT_ENOUGH_BALANCE(40011,HttpStatus.Series.CLIENT_ERROR,"not enough balance","موجودی حساب کافی نیست."),
    BAD_REQUESTED_AMOUNT(40012,HttpStatus.Series.CLIENT_ERROR,"invalid requested amount","مقدار درخواستی نامعتبر است."),
    MOBILE_NUMBER_EXISTS(40013,HttpStatus.Series.CLIENT_ERROR,"mobile number exists","شماره موبایل وارد شده تکراری می باشد!"),
    BAD_CREDENTIALS_ERROR(40014,HttpStatus.Series.CLIENT_ERROR,"username or password is wrong","نام کاربری وارد شده یا رمز عبور اشتباه است."),
    COUPLED_CONVERSION_NOT_FOUND(40015,HttpStatus.Series.CLIENT_ERROR,"invalid requested coupled converions","توکن درخواستی اشتباه است."+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    TRANSACTION_IS_NOT_VALID(40016,HttpStatus.Series.CLIENT_ERROR,"transaction is not valid","تراکنش نامعتبر است. "+"عملیات جدیدی را شروع کنید!"),
    INPUT_VALIDATION_TEXT_ERROR(40017,HttpStatus.Series.CLIENT_ERROR,"input text is not valid","متن ورودی نامعنبر است."),
    INPUT_VALIDATION_ERROR(40018,HttpStatus.Series.CLIENT_ERROR,"input value is not valid","مقدار ورودی نامعتبر است."),
    MOBILE_VALIDATION_ERROR(40019,HttpStatus.Series.CLIENT_ERROR,"mobile value is not valid.sample:(09124567890)","شماره موبایل نامعتبر است.نمونه ورودی صحیح: 09120123456"),
    PASSWORD_VALIDATION_ERROR(40020,HttpStatus.Series.CLIENT_ERROR,
            "password value is not valid.8 to 20 characters(should include lowercase,uppercase english letters,number and special characters including:@$!%*#?&)",
            "رمز ورودی نامعتبر است.( طول رمز باید بین ۸ تا ۲۰ کاراکتر و شامل فقط حروف انگلیسی ، اعداد و کارکترهای ویژه (@$!%*#?&) باشد."),
    REPEATED_PASSWORD_VALIDATION_ERROR(40021,HttpStatus.Series.CLIENT_ERROR,"repeated password value is not valid","عدم تطابق تکرار رمز "),
    NAME_VALIDATION_ERROR(40022,HttpStatus.Series.CLIENT_ERROR,"name value is not valid(farsi characters.(4 to 15 characters","نام و نام خانوادگی باید بین ۴ تا ۱۵ کارکتر و شامل فقط حروف فارسی باشد."),
    REFERRAL_VALIDATION_ERROR(40023,HttpStatus.Series.CLIENT_ERROR,"referral code value is not valid(english characters and numbers.(5 to 10 characters","رفرال کد باید بین  ۵ تا ۱۰ کاراکتر و شامل فقط حروف و اعداد انگلیسی باشد."),
    SHEBA_VALIDATION_ERROR(40024,HttpStatus.Series.CLIENT_ERROR,"sheba pattern is not valid.sample: IR580540105180021273113007","شماره شبای وارد شده اشتباه است. نمونه صحیح (IR580540105180021273113007)"),
    PAN_VALIDATION_ERROR(40025,HttpStatus.Series.CLIENT_ERROR,"pan pattern is not valid.sample: 6221061078314101","شماره کارت ورودی اشتباه است. نمونه صحیح (6221061078314101)"),
    ACCOUNT_NUMBER_VALIDATION_ERROR(40026,HttpStatus.Series.CLIENT_ERROR,"account number is not valid ,","شماه حساب وارد شده اشتباه است."),
    NATIONAL_CODE_VALIDATION_ERROR(40027,HttpStatus.Series.CLIENT_ERROR,"national code is not valid ,","کد ملی وارد شده اشتباه است."),
    BIRTHDATE_VALIDATION_ERROR(40028,HttpStatus.Series.CLIENT_ERROR,"birthdate is not valid ,","تاریخ تولد وارد شده اشتباه است."),
    GENDER_VALIDATION_ERROR(40029,HttpStatus.Series.CLIENT_ERROR,"gender is not valid","جنسیت وارد شده اشتباه است."),
    EMAIL_VALIDATION_ERROR(40031,HttpStatus.Series.CLIENT_ERROR,"email code is not valid ","ایمیل وازد شده نامعتبر است.نمونه معتبر : aabb@gmail.com"),
    BANK_ACCOUNT_EXISTS(40030,HttpStatus.Series.CLIENT_ERROR,"bank account already exists","حساب بانکی وارد شده تکراری است."),
    GOLD_AMOUNT_VALIDATION_ERROR(40032,HttpStatus.Series.CLIENT_ERROR,"gold value is not valid","میزان طلای درخواستی نامعبر است."),
    REQUESTED_TOKEN_NOT_FOUND_ERROR(40033,HttpStatus.Series.CLIENT_ERROR,"requested token is not valid","توکن در خواستی نامعبر است." +"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    WITHDRAW_TOKEN_AMOUNT_VALIDATION_ERROR(40034,HttpStatus.Series.CLIENT_ERROR,"requested token for withdraw is not valid","مقدار درخواستی جهت برداشت نامعتبر است."),
    DEPOSIT_CASH_AMOUNT_VALIDATION_ERROR(40035,HttpStatus.Series.CLIENT_ERROR,"requested cash for deposit is not valid","مبلغ درخواستی جهت شارژ کیف پول نامعتبر است."),

    IMAGE_SIZE_VALIDATION(40036,HttpStatus.Series.CLIENT_ERROR,"image size is not valid","سایز عکس ارسالی بیشتر از حدمجاز است."),
    IMAGE_TYPE_VALIDATION(40041,HttpStatus.Series.CLIENT_ERROR,"image type is not valid","فرمت عکس ارسالی صحیح نمی باشد."),
    DOCUMENT_SIZE_VALIDATION(4042,HttpStatus.Series.CLIENT_ERROR,"document size is not valid","سایز مدرک ارسالی بیشتر از حدمجاز است."),
    DOCUMENT_TYPE_VALIDATION(40043,HttpStatus.Series.CLIENT_ERROR,"document type is not valid","فرمت مدرک ارسالی صحیح نمی باشد."),
    NATIONAL_CODE_EXISTS_ERROR(40044,HttpStatus.Series.CLIENT_ERROR,"national code is already exists ,","کد ملی وارد شده تکراریست."),
    OLD_PASSWORD_IS_NOT_CORRECT(40037,HttpStatus.Series.CLIENT_ERROR,"old password is not correct","رمز عبور قدیمی اشتباه است."),
    NATIONAL_CARD_NOT_UPLOADED(40038,HttpStatus.Series.CLIENT_ERROR,"national card not uploaded", "تصاویر کارت ملی آپلود نشده است."),
    BUY_RATE_SHOULD_BE_BIGGER_THAN_SELL(40039,HttpStatus.Series.CLIENT_ERROR,"buy rate should bigger than sell","نرخ خرید باید بزرگتر از نرخ فروش باشد."),
    TICKET_STATUS_NOT_CORRECT(40040,HttpStatus.Series.CLIENT_ERROR,"ticket status is not correct","وضعیت تیکت باید منتظر پاسج کاربر باشد." ),
    WITHDRAW_NOT_FOUND_ERROR(40045,HttpStatus.Series.CLIENT_ERROR,"WHIDRAW REQUEST NOT FOUND","درخواست پرداختی برای شناسه ارسالی یافت نشد."),
    VERIFICATION_CODE_NOT_SENT(40046,HttpStatus.Series.CLIENT_ERROR,"verification code is empty","کد تایید بانک ارسال نشده است."),
    FAILED_DESCRIPTION_NOT_SENT(40047,HttpStatus.Series.CLIENT_ERROR,"failed description is empty","دلیل عدم پرداخت وجه ارسال نشده است."),
    COUPON_NOT_FOUND(40048,HttpStatus.Series.CLIENT_ERROR,"coupon is not valid","کد تخفیف معتبر نمی باشد."),
    COUPON_NOT_ACTIVATED(40049,HttpStatus.Series.CLIENT_ERROR,"coupon is not active","کد تخفیف فعال نمی باشد."),
    COUPON_USED_BEFORE(40050,HttpStatus.Series.CLIENT_ERROR,"coupon used before","کد تخفیف استفاده شده است."),
    CAPTCHA_VALIDATION_ERROR(40051,HttpStatus.Series.CLIENT_ERROR,"captcha is not correct","مقدار کپچا اشتباه وارد شده است."),
    OTP_VALIDATION_ERROR(40052,HttpStatus.Series.CLIENT_ERROR,"otp is not correct","رمز یکبار مصرف پیامکی وارد شده اشتباه است."),
    USER_IS_NO_SECURED_WITH_PASS(40053,HttpStatus.Series.CLIENT_ERROR, "user is not secured by pass","شما رمز خود را فعال نکرده اید."),
    AUTH_SERVER_BAD_REQUEST(40053,HttpStatus.Series.CLIENT_ERROR,"Auth server response is no 200.","پاسخ مناسبی از authserver دریافت نشد."),
    USERNAME_VALIDATION_ERROR(40054,HttpStatus.Series.CLIENT_ERROR,"user value is not valid(english lowercase characters.(3 to 15 characters)","نام کاربری باید بین 3 تا ۱۵ کارکتر و شامل فقط حروف و اعداد انگلیسی باشد."),
    TP_CLIENT_NAME_NOT_FOUND_IN_TOKEN(40055,HttpStatus.Series.CLIENT_ERROR,"tp client token does not contains client name","درخواست ارسالی حاوی نام کلاینت نمی باشد. "),
    TP_CLIENT_NAME_IS_NOT_VALID(40056,HttpStatus.Series.CLIENT_ERROR,"client not found","نام کلاینت در دیتابیس یافت نشد."),
    USERNAME_ALREADY_EXISTS(40057,HttpStatus.Series.CLIENT_ERROR,"requested username already exists.","یوزرنیم وارد شده تکراریس."),
    FACTOR_NUMBER_EXISTS(40058,HttpStatus.Series.CLIENT_ERROR,"factor number already exists.","شماه فاکتور وارد شده تکراریس."),


    UNAUTHORIZED(401,HttpStatus.Series.CLIENT_ERROR,"unauthorized","کاربر نامعتبر است."),
    VALIDATION_CODE_ERROR(40101,HttpStatus.Series.CLIENT_ERROR,"validation code does not match","کد وارد شده صحیح نمی باشد."),
    USER_DEACTIVATED_ERROR(40102,HttpStatus.Series.CLIENT_ERROR,"user deactivated","کابر غیرفعال شده است."+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    USER_NOT_VERIFIED_ERROR(40103,HttpStatus.Series.CLIENT_ERROR,"user not verified","اطلاعات شما هنوز تایید نشده است. جهت پیگیری با پشتیبانی تماس حاصل فرمایید."),
    USER_NOT_FILLED_INFORMATION(40104,HttpStatus.Series.CLIENT_ERROR,"user not filled information","جهت برداشت از حساب باید مشخصارت فردی و مدارک شناسایی خود را کامل کنید."),
    USER_BLOCKED_TOO_MANY_LOGIN_REQUEST_TEN(40105,HttpStatus.Series.CLIENT_ERROR,"user blocked because of too many wrong login requests for ten minutes"," حساب کاربری شمابه دلیل اشتباه وارد کردن کد یا رمز به مدت ۱۰ دقیقه غیر فعال  شده است."),
    USER_BLOCKED_TOO_MANY_LOGIN_REQUEST_FIVE_H(40106,HttpStatus.Series.CLIENT_ERROR,"user blocked because of too many wrong login requests for 5 hours"," حساب کاربری شمابه دلیل اشتباه وارد کردن کد یا رمز به مدت ۵ ساعت غیر فعال است."),
    USER_BLOCKED_TOO_MANY_LOGIN_REQUEST_PERM(40107,HttpStatus.Series.CLIENT_ERROR,"user blocked because of too many wrong login requests for permanently"," حساب کاربری شمابه دلیل اشتباه وارد کردن کد یا رمز غیر فعال می شود. جهت بررسی مشکل با پشتیبانی سایت تماس حاصل فرمایید."),
    USER_NOT_ALLOWED_THIS_OPERATION(40108,HttpStatus.Series.CLIENT_ERROR,"this user is not allowed to perform this operation","شما مجاز به انجام این عملیات نیستید. لطفا وضعیت کاربری خود در پروفایل را بررسی نمایید و یا با پشتیبانی تماس حاصل فرمایید."),
    WAIT_FOR_SENDER_VERIFY(40108,HttpStatus.Series.CLIENT_ERROR,"transfer sender should verify the receiver","ابتدا باید احراز هویت توسط ارسال کننده صورت گیرد."),
    DUE_DATE_NOT_REACHED(40109,HttpStatus.Series.CLIENT_ERROR,"due date has not reached yet.","تاریخ پرداخت هنوز نرسیده است."),

    OPERATION_IS_NOT_ACTIVE(40301,HttpStatus.Series.CLIENT_ERROR,"operation is not active", "عملیات فعال نمی باشد."),
    INTERNAL_SERVER_ERROR(500,HttpStatus.Series.SERVER_ERROR,"internal server error","خطای سیستم. لطفا مجددا تلاش کنید و یا "+"برای اطلاعات بیشتر با پشتیبانی تماس حاصل نمایید."),
    TOO_MANY_REQUESTS_TRY_AGAIN(50001,HttpStatus.Series.SERVER_ERROR,"too many requests , please try again", "لطفا مجددا تلاش کنید."),
    NOTIFICATION_SERVER_ERROR(50011,HttpStatus.Series.SERVER_ERROR,"notification was not successful.try again","اطلاع رسانی انجام نشد."),
    PAYMENT_SERVER_ERROR(50012,HttpStatus.Series.SERVER_ERROR,"payment gateway did not responded.  try again","پاسخی از سمت درگاه پرداخت یافت نشد. لطفا مجدد تلاش کنید. "),
    DATABASE_SERVER_ERROR(50013,HttpStatus.Series.SERVER_ERROR,"database failed to perform. try again","خطای داخلی سیستم."),
    ZARINPAL_AUTHORITY_NOT_FOUND_ERROR(50014,HttpStatus.Series.SERVER_ERROR,"zarinpal authority code not found in callback","کد درگاه پرداخت نامعتبر است."),
    AUTH_SERVER_NOT_AVAILABLE(50301,HttpStatus.Series.SERVER_ERROR,"Auth server is not available.","پاسخی از authserver دریافت نشد."),

    SABTAHVALERROR(50401, HttpStatus.Series.SERVER_ERROR,"sabt ahval server is not available." , "ثبت احوال پاسخگو نیست."),
    SABTAHVAL_TOKEN_ERROR(50402, HttpStatus.Series.SERVER_ERROR,"sabt ahval server to get token is not available." , "ثبت احوال پاسخگو نیست."),

    ZARINPAL_VALIDATION_ERROR(-9,HttpStatus.Series.SERVER_ERROR,"Validation error","خطای اعتبار سنجی "),
    ZARINPAL_TERMINAL_IS_NOT_VALID(-10,HttpStatus.Series.SERVER_ERROR,"Terminal is not valid, please check merchant_id or ip address.","ای پی یا مرچنت كد پذیرنده صحیح نیست."),
    ZARINPAL_TERMINAL_IS_NOT_ACTIVE(-11,HttpStatus.Series.SERVER_ERROR,"Terminal is not active, please contact our support team.","مرچنت کد فعال نیست، پذیرنده مشکل خود را به امور مشتریان زرین‌پال ارجاع دهد."),
    ZARINPAL_TO_MANY_ATTEMPTS(-12,HttpStatus.Series.SERVER_ERROR,"To many attempts, please try again later.","تلاش بیش از دفعات مجاز در یک بازه زمانی کوتاه به امور مشتریان زرین پال اطلاع دهید"),
    ZARINPAL_TERMINAL_USER_IS_SUSPEND(-15,HttpStatus.Series.SERVER_ERROR,"Terminal user is suspend : (please contact our support team).","درگاه پرداخت به حالت تعلیق در آمده است، پذیرنده مشکل خود را به امور مشتریان زرین‌پال ارجاع دهد."),
    ZARINPAL_TERMINAL_USER_LEVEL_IS_NOT_VALID_SILVER(-16,HttpStatus.Series.SERVER_ERROR,"Terminal user level is not valid : ( please contact our support team).","سطح تایید پذیرنده پایین تر از سطح نقره ای است."),
    ZARINPAL_TERMINAL_USER_LEVEL_IS_NOT_VALID_BLUE(-17,HttpStatus.Series.SERVER_ERROR,"Terminal user level is not valid : ( please contact our support team).","محدودیت پذیرنده در سطح آبی"),
    ZARINPAL_TERMINAL_DO_NOT_ALLOW_TO_ACCEPT_FLOATING_WAGES(-30,HttpStatus.Series.SERVER_ERROR,"Terminal do not allow to accept floating wages.","پذیرنده اجازه دسترسی به سرویس تسویه اشتراکی شناور را ندارد."),
    ZARINPAL_TERMINAL_DO_NOT_ALLOW_TO_ACCEPT_WAGES(-31,HttpStatus.Series.SERVER_ERROR,"Terminal do not allow to accept wages, please add default bank account in panel.","حساب بانکی تسویه را به پنل اضافه کنید. مقادیر وارد شده برای تسهیم درست نیست. پذیرنده جهت استفاده از خدمات سرویس تسویه اشتراکی شناور، باید حساب بانکی معتبری به پنل کاربری خود اضافه نماید."),
    ZARINPAL_WAGES_IS_NOT_VALID(-32,HttpStatus.Series.SERVER_ERROR,"Wages is not valid, Total wages(floating) has been overload max amount.","مبلغ وارد شده از مبلغ کل تراکنش بیشتر است."),
    ZARINPAL_WAGES_FLOATING_IS_NOT_VALID(-33,HttpStatus.Series.SERVER_ERROR,"Wages floating is not valid.","درصدهای وارد شده صحیح نیست."),
    ZARINPAL_WAGES_IS_NOT_VALID_TOTAL_WAGES_HAS_BEEN_OVERLOAD_MAX_AMOUNT(-34,HttpStatus.Series.SERVER_ERROR,"Wages is not valid, Total wages(fixed) has been overload max amount.","مبلغ وارد شده از مبلغ کل تراکنش بیشتر است."),
    ZARINPAL_WAGES_IS_NOT_VALID_TOTAL_WAGES_HAS_BEEN_REACHED_THE_LIMIT_IN_MAX_PARTS(-35,HttpStatus.Series.SERVER_ERROR,"Wages is not valid, Total wages(floating) has been reached the limit in max parts.","تعداد افراد دریافت کننده تسهیم بیش از حد مجاز است."),
    ZARINPAL_THE_MINIMUM_AMOUNT_FOR_WAGES(-36,HttpStatus.Series.SERVER_ERROR,"The minimum amount for wages(floating) should be 10,000 Rials","حداقل مبلغ جهت تسهیم باید ۱۰۰۰۰ ریال باشد"),
    ZARINPAL_ONE_OR_MORE_IBAN_ENTERED_FOR_WAGES(-37,HttpStatus.Series.SERVER_ERROR,"One or more iban entered for wages(floating) from the bank side are inactive.","یک یا چند شماره شبای وارد شده برای تسهیم از سمت بانک غیر فعال است."),
    ZARINPAL_WAGES_NEED_TO_SET_IBAN_IN_SHAPARAK(-38,HttpStatus.Series.SERVER_ERROR,"Wages need to set Iban in shaparak.","خطا٬عدم تعریف صحیح شبا٬لطفا دقایقی دیگر تلاش کنید."),
    ZARINPAL_WAGES_HAVE_A_ERROR(-39,HttpStatus.Series.SERVER_ERROR,"Wages have a error!","خطایی رخ داده است به امور مشتریان زرین پال اطلاع دهید"),
    ZARINPAL_INVALID_EXTRA_PARAMS_EXPIRE_IN_IS_NOT_VALID(-40,HttpStatus.Series.SERVER_ERROR,"Invalid extra params, expire_in is not valid.",""),
    ZARINPAL_MAXIMUM_AMOUNT(-41,HttpStatus.Series.SERVER_ERROR,"Maximum amount is 100,000,000 tomans.","حداکثر مبلغ پرداختی ۱۰۰ میلیون تومان است"),
    ZARINPAL_SESSION_IS_NOT_VALID_AMOUNTS_VALUES_IS_NOT_THE_SAME(-50,HttpStatus.Series.SERVER_ERROR,"Session is not valid, amounts values is not the same.","مبلغ پرداخت شده با مقدار مبلغ ارسالی در متد وریفای متفاوت است."),
    ZARINPAL_SESSION_IS_NOT_VALID_SESSION_IS_NOT_ACTIVE_PAID_TRY(-51,HttpStatus.Series.SERVER_ERROR,"Session is not valid, session is not active paid try.","پرداخت ناموفق"),
    ZARINPAL_OOPS_PLEASE_CONTACT_OUR_SUPPORT_TEAM(-52,HttpStatus.Series.SERVER_ERROR,"Oops!!, please contact our support team","خطای غیر منتظره‌ای رخ داده است. پذیرنده مشکل خود را به امور مشتریان زرین‌پال ارجاع دهد."),
    ZARINPAL_SESSION_IS_NOT_THIS_MERCHANT_ID_SESSION(-53,HttpStatus.Series.SERVER_ERROR,"Session is not this merchant_id session","پرداخت متعلق به این مرچنت کد نیست."),
    ZARINPAL_INVALID_AUTHORITY(-54,HttpStatus.Series.SERVER_ERROR,"Invalid authority.","اتوریتی نامعتبر است."),
    ZARINPAL_MANUAL_PAYMENT_REQUEST_NOT_FOUND(-55,HttpStatus.Series.SERVER_ERROR,"manual payment request not found.","تراکنش مورد نظر یافت نشد");





















    private final int value;
    private  HttpStatus.Series series;
    private  String reasonPhrase;
    private String reasonPhraseFa;

    private ErrorCode(int value, HttpStatus.Series series, String reasonPhrase, String reasonPhraseFa) {
        this.value = value;
        this.series = series;
        this.reasonPhrase = reasonPhrase;
        this.reasonPhraseFa= reasonPhraseFa;
    }

    public static ErrorCode valueOf(int statusCode) {
        ErrorCode status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static ErrorCode resolve(int statusCode) {
        ErrorCode[] var1 = ErrorCode.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ErrorCode status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        return null;
    }

    ErrorCode(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public String getReasonPhrase(){
        return reasonPhrase;
    }

    public String getReasonPhraseFa() {
        return reasonPhraseFa;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "value=" + value +
                ", series=" + series +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                ", reasonPhraseFa='" + reasonPhraseFa + '\'' +
                '}';
    }
}
