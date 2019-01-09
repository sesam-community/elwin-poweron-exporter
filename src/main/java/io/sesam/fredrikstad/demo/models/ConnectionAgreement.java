package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class ConnectionAgreement extends SesamEntity {

    @JsonProperty("CustomerNumber")
    private String customerNumber;

    @JsonProperty("PropertyNumber")
    private String propertyNumber;

    @JsonProperty("AgreementStartDate")
    private String agreementStartDate;

    @JsonProperty("NoticeToDeenergise")
    private int noticeToDeenergise;

    @JsonProperty("Status")
    private String agreementStatus;

    public ConnectionAgreement() {
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

    public String getAgreementStartDate() {
        return agreementStartDate;
    }

    public void setAgreementStartDate(String agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public int getNoticeToDeenergise() {
        return noticeToDeenergise;
    }

    public void setNoticeToDeenergise(int noticeToDeenergise) {
        this.noticeToDeenergise = noticeToDeenergise;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

}
