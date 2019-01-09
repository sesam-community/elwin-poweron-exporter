package io.sesam.fredrikstad.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
}
