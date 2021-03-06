package io.sesam.fredrikstad.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 *
 * @author Timur Samkharadze
 */
@ConfigurationProperties
@Component
public class AppConfig {

    @Value("${POWERON_SOAP_URL}")
    private String url;

    @Nullable
    public String getUrl() {
        return this.url;
    }

    @Value("${client.ssl.trust-store}")
    private Resource trustStore;

    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;

    @Value("${client.ssl.use-ssl}")
    private boolean useSSL = false;

    @Value("${client.ssl.trust-all}")
    private boolean trustAll = false;

    @Nullable
    public Resource getTrustStore() {
        return trustStore;
    }

    @Nullable
    public String getTrustStorePassword() {
        return trustStorePassword;
    }

    public boolean useSSL() {
        return useSSL;
    }

    public boolean isTrustAll() {
        return trustAll;
    }

    @Override
    public String toString() {
        return "AppConfig{" + "url=" + url + ", trustStore=" + trustStore + ", trustStorePassword=" + trustStorePassword + ", useSSL=" + useSSL + ", trustAll=" + trustAll + '}';
    }
    
    
    
    
}
