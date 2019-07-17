package com.api.common.weipay;

import org.apache.http.client.HttpClient;

/**
 * 常量
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    static final String DOMAIN_API = "api.mch.weixin.qq.com";
    public static final String DOMAIN_API2 = "api2.mch.weixin.qq.com";
    public static final String DOMAIN_APIHK = "apihk.mch.weixin.qq.com";
    public static final String DOMAIN_APIUS = "apius.mch.weixin.qq.com";


    static final String FAIL     = "FAIL";
    static final String SUCCESS  = "SUCCESS";
    static final String HMACSHA256 = "HMAC-SHA256";
    static final String MD5 = "MD5";

    static final String FIELD_SIGN = "sign";
    static final String FIELD_SIGN_TYPE = "sign_type";

    static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    static final String USER_AGENT = WXPAYSDK_VERSION +
            " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") +
            ") Java/" + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();

    static final String MICROPAY_URL_SUFFIX     = "/pay/micropay";

    static final String UNIFIEDORDER_URL_SUFFIX = "/pay/unifiedorder";
    static final String ORDERQUERY_URL_SUFFIX   = "/pay/orderquery";
    static final String REVERSE_URL_SUFFIX      = "/secapi/pay/reverse";
    static final String CLOSEORDER_URL_SUFFIX   = "/pay/closeorder";
    static final String REFUND_URL_SUFFIX       = "/secapi/pay/refund";
    static final String REFUNDQUERY_URL_SUFFIX  = "/pay/refundquery";
    static final String DOWNLOADBILL_URL_SUFFIX = "/pay/downloadbill";
    static final String REPORT_URL_SUFFIX       = "/payitil/report";
    static final String SHORTURL_URL_SUFFIX     = "/tools/shorturl";
    static final String AUTHCODETOOPENID_URL_SUFFIX = "/tools/authcodetoopenid";
    static final String ENTERPRISE_PAY = "/mmpaymkttransfers/promotion/transfers";


    // sandbox
    static final String SANDBOX_MICROPAY_URL_SUFFIX     = "/sandboxnew/pay/micropay";
    static final String SANDBOX_UNIFIEDORDER_URL_SUFFIX = "/sandboxnew/pay/unifiedorder";
    static final String SANDBOX_ORDERQUERY_URL_SUFFIX   = "/sandboxnew/pay/orderquery";
    static final String SANDBOX_REVERSE_URL_SUFFIX      = "/sandboxnew/secapi/pay/reverse";
    static final String SANDBOX_CLOSEORDER_URL_SUFFIX   = "/sandboxnew/pay/closeorder";
    static final String SANDBOX_REFUND_URL_SUFFIX       = "/sandboxnew/secapi/pay/refund";
    static final String SANDBOX_REFUNDQUERY_URL_SUFFIX  = "/sandboxnew/pay/refundquery";
    static final String SANDBOX_DOWNLOADBILL_URL_SUFFIX = "/sandboxnew/pay/downloadbill";
    static final String SANDBOX_REPORT_URL_SUFFIX       = "/sandboxnew/payitil/report";
    static final String SANDBOX_SHORTURL_URL_SUFFIX     = "/sandboxnew/tools/shorturl";
    static final String SANDBOX_AUTHCODETOOPENID_URL_SUFFIX = "/sandboxnew/tools/authcodetoopenid";
    static final String SANDBOX_ENTERPRISE_PAY = "/sandboxnew/mmpaymkttransfers/promotion/transfers";

}

