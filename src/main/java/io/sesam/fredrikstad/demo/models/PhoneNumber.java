package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class PhoneNumber extends SesamEntity{
    @JsonProperty("PropertyNumber")
    private String propertyNumber;
    
    @JsonProperty("TelephoneNumber")
    private String phoneNumber;
    
    @JsonProperty("UsageStartDate")
    private String usageStartDate;
    
    @JsonProperty("UsageEndDate")
    private String usageEndDate;
    
    @JsonProperty("Type")
    private int type;

    public PhoneNumber() {
        super();
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsageStartDate() {
        return usageStartDate;
    }

    public void setUsageStartDate(String usageStartDate) {
        this.usageStartDate = usageStartDate;
    }

    public String getUsageEndDate() {
        return usageEndDate;
    }

    public void setUsageEndDate(String usageEndDate) {
        this.usageEndDate = usageEndDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
    
    
    
}
