package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class NetworkPropertyLink extends SesamEntity {

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

    @JsonProperty("ComponentAlias")
    private String componentAlias;

    @JsonProperty("SubQualityID")
    private char subQualityID;

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

    public String getComponentAlias() {
        return componentAlias;
    }

    public void setComponentAlias(String componentAlias) {
        this.componentAlias = componentAlias;
    }

    public char getSubQualityID() {
        return subQualityID;
    }

    public void setSubQualityID(char subQualityID) {
        this.subQualityID = subQualityID;
    }

}
