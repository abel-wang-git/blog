package com.api.common.weipay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@ConfigurationProperties("wx.config")
public class WXConfig extends WXPayConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private  String appID;

    private String mchID;

    private String key;

    private byte[] certData;

    private String certPath;

    private String notifyUrl;

    private IWXPayDomain wXPayDomain;


    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        return new ByteArrayInputStream(this.certData);
    }

    @Override
    public IWXPayDomain getWXPayDomain(){
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public void setMchID(String mchID) {
        this.mchID = mchID;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        InputStream certStream = resourceLoader.getResource(certPath).getInputStream();

        int b;
        while ((b = certStream.read()) != -1) {
            outStream.write(b);
        }
        this.certData = outStream.toByteArray();
        outStream.close();
        certStream.close();
        this.certPath = certPath;

    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
