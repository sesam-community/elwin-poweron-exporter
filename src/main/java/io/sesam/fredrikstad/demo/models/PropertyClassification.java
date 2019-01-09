package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class PropertyClassification extends SesamEntity {

    @JsonProperty("PropertyNumber")
    private String propertyNumber;

    @JsonProperty("PTCode")
    private int ptCode;

    public PropertyClassification() {
        super();
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public int getPtCode() {
        return ptCode;
    }

    public void setPtCode(int ptCode) {
        this.ptCode = ptCode;
    }

}
