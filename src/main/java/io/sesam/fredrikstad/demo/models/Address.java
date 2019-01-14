package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Address model elwin data
 *
 * @author 100tsa
 */
public class Address extends SesamEntity {

    @JsonProperty("AddressNumber")
    private String addressNumber;

    @JsonProperty("Postcode")
    private String postcode;

    @JsonProperty("AdministrativeAreaName")
    private String administrativeAreaName;

    @JsonProperty("TownName")
    private String townName;

    @JsonProperty("StreetName")
    private String streetName;

    @JsonProperty("SecondaryAddressableObject")
    private String secondaryAddressableObject;

    @JsonProperty("PrimaryAddressableObject")
    private String primaryAddressableObject;

    @JsonProperty("QSTCode")
    private String qSTCode;

    @JsonProperty("AFTCode")
    private String aFTCode;

    @JsonProperty("GridReference")
    private String gridReference;

    @JsonProperty("LocalityName")
    private String localityName;

    @JsonProperty("GeoPosition")
    private String geoPosition;

    @JsonProperty("LotNumber")
    private String lotNumber;

    @JsonProperty("PlanNumber")
    private String planNumber;

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAdministrativeAreaName() {
        return administrativeAreaName;
    }

    public void setAdministrativeAreaName(String administrativeAreaName) {
        this.administrativeAreaName = administrativeAreaName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSecondaryAddressableObject() {
        return secondaryAddressableObject;
    }

    public void setSecondaryAddressableObject(String secondaryAddressableObject) {
        this.secondaryAddressableObject = secondaryAddressableObject;
    }

    public String getPrimaryAddressableObject() {
        return primaryAddressableObject;
    }

    public void setPrimaryAddressableObject(String primaryAddressableObject) {
        this.primaryAddressableObject = primaryAddressableObject;
    }

    public String getqSTCode() {
        return qSTCode;
    }

    public void setqSTCode(String qSTCode) {
        this.qSTCode = qSTCode;
    }

    public String getaFTCode() {
        return aFTCode;
    }

    public void setaFTCode(String aFTCode) {
        this.aFTCode = aFTCode;
    }

    public String getGridReference() {
        return gridReference;
    }

    public void setGridReference(String gridReference) {
        this.gridReference = gridReference;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(String geoPosition) {
        this.geoPosition = geoPosition;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "addressNumber=" + addressNumber + ", postcode=" + postcode + ", administrativeAreaName="
                + administrativeAreaName + ", townName=" + townName + ", streetName="
                + streetName + ", secondaryAddressableObject="
                + secondaryAddressableObject + ", primaryAddressableObject="
                + primaryAddressableObject + ", qSTCode=" + qSTCode + ", aFTCode="
                + aFTCode + ", gridReference=" + gridReference + ", localityName=" + localityName + ", geoPosition="
                + geoPosition + ", lotNumber=" + lotNumber + ", planNumber=" + planNumber + '}';
    }

    public Address() {
    }

}
