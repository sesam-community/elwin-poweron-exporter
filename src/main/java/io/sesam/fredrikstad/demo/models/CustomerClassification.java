package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class CustomerClassification  extends SesamEntity{
    
    @JsonProperty("CustomerNumber")
    private String customerNumber;
    
    @JsonProperty("CTCode")
    private int ctCode;

    public CustomerClassification() {
        super();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCtCode() {
        return ctCode;
    }

    public void setCtCode(int ctCode) {
        this.ctCode = ctCode;
    }
    
    
    
    
}
