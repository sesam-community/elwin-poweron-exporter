package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class MeterNumber extends SesamEntity{
    
    @JsonProperty("PropertyNumber")
    private String propertyNumber;
    
    @JsonProperty("MeterNumber")
    private String meterNumber;
    
    @JsonProperty("IsSmartMeter")
    private int isSmartMeter;

    public MeterNumber() {
        super();
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public int getIsSmartMeter() {
        return isSmartMeter;
    }

    public void setIsSmartMeter(int isSmartMeter) {
        this.isSmartMeter = isSmartMeter;
    }
    
    
    
    
    
}
