package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class EmailAddress extends SesamEntity{

    @JsonProperty(value = "EmailAddress")
    private String emailAddress;

    @JsonProperty(value = "CustomerNumber")
    private String customerNumber;

    public EmailAddress() {
        super();
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public String toString() {
        return "EmailAddress{" + "emailAddress=" + emailAddress + ", customerNumber=" + customerNumber + '}';
    }
    
    

}
