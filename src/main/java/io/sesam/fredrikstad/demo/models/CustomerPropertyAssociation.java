package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class CustomerPropertyAssociation extends SesamEntity{
    @JsonProperty("CustomerNumber")
    private String customerNumber;
    
    @JsonProperty("PropertyNumber")
    private String propertyNumber;
    
    @JsonProperty("ATCode")
    private int atCode;
    
    @JsonProperty("UsageStartDate")
    private String usageStartDate;
    
    @JsonProperty("UsageEndDate")
    private String usageEndDate;
    
    @JsonProperty("LinkReference")
    private String linkReference;
    
    @JsonProperty("LinkSystem")
    private String linkSystem;
    
    @JsonProperty("BusinessSource")
    private String businessSource;
    
    @JsonProperty("LinkAddressReference")
    private String linkAddressReference;

    public CustomerPropertyAssociation() {
        super();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public int getAtCode() {
        return atCode;
    }

    public void setAtCode(int atCode) {
        this.atCode = atCode;
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

    public String getLinkReference() {
        return linkReference;
    }

    public void setLinkReference(String linkReference) {
        this.linkReference = linkReference;
    }

    public String getLinkSystem() {
        return linkSystem;
    }

    public void setLinkSystem(String linkSystem) {
        this.linkSystem = linkSystem;
    }

    public String getBusinessSource() {
        return businessSource;
    }

    public void setBusinessSource(String businessSource) {
        this.businessSource = businessSource;
    }

    public String getLinkAddressReference() {
        return linkAddressReference;
    }

    public void setLinkAddressReference(String linkAddressReference) {
        this.linkAddressReference = linkAddressReference;
    }
    
    
    
    
}
