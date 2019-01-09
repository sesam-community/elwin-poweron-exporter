package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class NetworkPropertyLink extends SesamEntity{
    @JsonProperty("PropertyNumber")
    private String propertyNumber;
    
    @JsonProperty("FeederNumber")
    private int feederNumber;
    
    @JsonProperty("FeedQualityID")
    private String feederQualityID;
    
    @JsonProperty("Phase")
    private int phase;
    
    @JsonProperty("NetworkStatus")
    private int networkStatus;

    public NetworkPropertyLink() {
        super();
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public int getFeederNumber() {
        return feederNumber;
    }

    public void setFeederNumber(int feederNumber) {
        this.feederNumber = feederNumber;
    }

    public String getFeederQualityID() {
        return feederQualityID;
    }

    public void setFeederQualityID(String feederQualityID) {
        this.feederQualityID = feederQualityID;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(int networkStatus) {
        this.networkStatus = networkStatus;
    }
    
    
    
    
}
