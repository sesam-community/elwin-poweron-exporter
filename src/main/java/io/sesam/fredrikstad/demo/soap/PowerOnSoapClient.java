package io.sesam.fredrikstad.demo.soap;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import io.sesam.fredrikstad.demo.AppConfig;
import io.sesam.fredrikstad.demo.models.Address;
import io.sesam.fredrikstad.demo.models.ConnectionAgreement;
import io.sesam.fredrikstad.demo.models.Customer;
import io.sesam.fredrikstad.demo.models.CustomerClassification;
import io.sesam.fredrikstad.demo.models.CustomerPropertyAssociation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.sesam.fredrikstad.demo.models.EmailAddress;
import io.sesam.fredrikstad.demo.models.MeterNumber;
import io.sesam.fredrikstad.demo.models.NetworkPropertyLink;
import io.sesam.fredrikstad.demo.models.PhoneNumber;
import io.sesam.fredrikstad.demo.models.Property;
import io.sesam.fredrikstad.demo.models.PropertyClassification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import poweron.wsdl.AddressItemStc;
import poweron.wsdl.AddressListStc;
import poweron.wsdl.Addresses;
import poweron.wsdl.AddressesResponse;
import poweron.wsdl.AddressesResponseStc;
import poweron.wsdl.AddressesStc;
import poweron.wsdl.ConnectionAgreementItemStc;
import poweron.wsdl.ConnectionAgreementListStc;
import poweron.wsdl.ConnectionAgreements;
import poweron.wsdl.ConnectionAgreementsResponse;
import poweron.wsdl.ConnectionAgreementsResponseStc;
import poweron.wsdl.ConnectionAgreementsStc;
import poweron.wsdl.CustomerClassificationItemStc;
import poweron.wsdl.CustomerClassificationListStc;
import poweron.wsdl.CustomerClassifications;
import poweron.wsdl.CustomerClassificationsResponse;
import poweron.wsdl.CustomerClassificationsResponseStc;
import poweron.wsdl.CustomerClassificationsStc;
import poweron.wsdl.CustomerItemStc;
import poweron.wsdl.CustomerListStc;
import poweron.wsdl.CustomerPropertyAssociationItemStc;
import poweron.wsdl.CustomerPropertyAssociationListStc;
import poweron.wsdl.CustomerPropertyAssociations;
import poweron.wsdl.CustomerPropertyAssociationsResponse;
import poweron.wsdl.CustomerPropertyAssociationsResponseStc;
import poweron.wsdl.CustomerPropertyAssociationsStc;
import poweron.wsdl.Customers;
import poweron.wsdl.CustomersResponse;
import poweron.wsdl.CustomersResponseStc;
import poweron.wsdl.CustomersStc;
import poweron.wsdl.EmailAddressItemStc;
import poweron.wsdl.EmailAddressListStc;
import poweron.wsdl.EmailAddresses;
import poweron.wsdl.EmailAddressesResponse;
import poweron.wsdl.EmailAddressesResponseStc;
import poweron.wsdl.EmailAddressesStc;
import poweron.wsdl.MeterNumberItemStc;
import poweron.wsdl.MeterNumberListStc;
import poweron.wsdl.MeterNumbers;
import poweron.wsdl.MeterNumbersResponse;
import poweron.wsdl.MeterNumbersResponseStc;
import poweron.wsdl.MeterNumbersStc;
import poweron.wsdl.NetworkPropertyLinkItemStc;
import poweron.wsdl.NetworkPropertyLinkListStc;
import poweron.wsdl.NetworkPropertyLinks;
import poweron.wsdl.NetworkPropertyLinksResponse;
import poweron.wsdl.NetworkPropertyLinksResponseStc;
import poweron.wsdl.NetworkPropertyLinksStc;
import poweron.wsdl.ObjectFactory;
import poweron.wsdl.Properties;
import poweron.wsdl.PropertiesResponse;
import poweron.wsdl.PropertiesResponseStc;
import poweron.wsdl.PropertiesStc;
import poweron.wsdl.PropertyClassificationItemStc;
import poweron.wsdl.PropertyClassificationListStc;
import poweron.wsdl.PropertyClassifications;
import poweron.wsdl.PropertyClassificationsResponse;
import poweron.wsdl.PropertyClassificationsResponseStc;
import poweron.wsdl.PropertyClassificationsStc;
import poweron.wsdl.PropertyItemStc;
import poweron.wsdl.PropertyListStc;
import poweron.wsdl.TelephoneNumberItemStc;
import poweron.wsdl.TelephoneNumberListStc;
import poweron.wsdl.TelephoneNumbers;
import poweron.wsdl.TelephoneNumbersResponse;
import poweron.wsdl.TelephoneNumbersResponseStc;
import poweron.wsdl.TelephoneNumbersStc;

/**
 * Simple SoapService client for Power ON customer inbound messages service
 *
 * @author 100tsa
 */
@Component
public class PowerOnSoapClient extends WebServiceGatewaySupport {

    private static final ObjectFactory FACTORY = new ObjectFactory();
    private static final Logger LOG = LoggerFactory.getLogger(PowerOnSoapClient.class);

    @Autowired
    AppConfig config;

    /**
     * Insert/update email addresses
     *
     * @param input list of email addresses
     */
    public void processEmailAddressList(List<EmailAddress> input) {
        EmailAddresses soapMessage = FACTORY.createEmailAddresses();
        EmailAddressesStc emailAddressesPlaceholder = FACTORY.createEmailAddressesStc();
        emailAddressesPlaceholder.setOperationType("I");
        if (1 == input.size()) {
            emailAddressesPlaceholder.setRealTime(FACTORY.createEmailAddressesStcRealTime(1));
        }
        EmailAddressListStc emailList = FACTORY.createEmailAddressListStc();
        List<EmailAddressItemStc> emailAddressStc = emailList.getEmailAddressStc();

        input.stream().map((emailAddress) -> {
            EmailAddressItemStc item = FACTORY.createEmailAddressItemStc();
            item.setCustomerNumber(emailAddress.getCustomerNumber());
            item.setEmailAddress(emailAddress.getEmailAddress());
            emailAddress.setStatus("Successfully added to queue.");
            return item;
        }).forEachOrdered(emailAddressStc::add);
        emailAddressesPlaceholder.setEmailAddressList(emailList);
        soapMessage.setEmailAddressesStc(emailAddressesPlaceholder);

        WebServiceTemplate template = buildWebServiceTemplate();

        EmailAddressesResponse res = (EmailAddressesResponse) template.marshalSendAndReceive(config.getUrl(), soapMessage,
                new SoapActionCallback("Customer/EmailAddresses"));
        EmailAddressesResponseStc innerRes = res.getEmailAddressesResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }

    }

    /**
     * Insert/update addresses
     *
     * @param input
     */
    public void processAddresses(List<Address> input) {
        Addresses addresses = FACTORY.createAddresses();
        AddressesStc addressesStc = FACTORY.createAddressesStc();
        addressesStc.setOperationType("I");
        if (1 == input.size()) {
            addressesStc.setRealTime(FACTORY.createAddressesStcRealTime(1));
        }
        AddressListStc addressListStc = FACTORY.createAddressListStc();
        List<AddressItemStc> addressStc = addressListStc.getAddressStc();
        input.stream().map((address) -> {
            AddressItemStc item = FACTORY.createAddressItemStc();
            item.setAddressNumber(address.getAddressNumber());
            item.setPostcode(FACTORY.createAddressItemStcPostcode(address.getPostcode()));
            item.setAdministrativeAreaName(FACTORY.createAddressItemStcAdministrativeAreaName(address.getAdministrativeAreaName()));
            item.setTownName(FACTORY.createAddressItemStcTownName(address.getTownName()));
            item.setStreetName(FACTORY.createAddressItemStcStreetName(address.getStreetName()));
            item.setSecondaryAddressableObject(FACTORY.createAddressItemStcSecondaryAddressableObject(address.getSecondaryAddressableObject()));
            item.setPrimaryAddressableObject(FACTORY.createAddressItemStcPrimaryAddressableObject(address.getPrimaryAddressableObject()));
            item.setQSTCode(FACTORY.createAddressItemStcQSTCode(address.getqSTCode()));
            item.setAFTCode(FACTORY.createAddressItemStcAFTCode(address.getaFTCode()));
            item.setGridReference(FACTORY.createAddressItemStcGridReference(address.getGridReference()));
            item.setLocalityName(FACTORY.createAddressItemStcLocalityName(address.getLocalityName()));
            item.setGeoPosition(FACTORY.createAddressItemStcGeoPosition(address.getGeoPosition()));
            item.setLotNumber(FACTORY.createAddressItemStcLotNumber(address.getLotNumber()));
            item.setPlanNumber(FACTORY.createAddressItemStcPlanNumber(address.getPlanNumber()));
            return item;
        }).forEachOrdered((item) -> {
            addressStc.add(item);
        });
        addressesStc.setAddressList(addressListStc);
        addresses.setAddressesStc(addressesStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        AddressesResponse res = (AddressesResponse) template.marshalSendAndReceive(config.getUrl(), addresses,
                new SoapActionCallback("Customer/Addresses"));
        AddressesResponseStc innerRes = res.getAddressesResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update connection agreements
     *
     * @param input
     */
    public void processConnectionAgreements(List<ConnectionAgreement> input) {
        ConnectionAgreements connectionAgreements = FACTORY.createConnectionAgreements();
        ConnectionAgreementsStc connectionAgreementsStc = FACTORY.createConnectionAgreementsStc();

        connectionAgreementsStc.setOperationType("I");

        ConnectionAgreementListStc connectionAgreementListStc = FACTORY.createConnectionAgreementListStc();
        List<ConnectionAgreementItemStc> agreements = connectionAgreementListStc.getConnectionAgreementStc();

        input.stream().map((conAgreement) -> {
            ConnectionAgreementItemStc item = FACTORY.createConnectionAgreementItemStc();
            if (!conAgreement.getAgreementStartDate().isEmpty()) {
                try {
                    item.setAgreementStartDate(FACTORY.createConnectionAgreementItemStcAgreementStartDate(
                            this.parseDateStringToXmlGregorianCalendar(conAgreement.getAgreementStartDate(), null)));
                } catch (DatatypeConfigurationException ex) {
                    LOG.error("Couldn't parse date {} to XMLGregorianCalendar", conAgreement.getAgreementStartDate());
                    LOG.error(ex.getMessage());
                }
            }
            item.setCustomerNumber(FACTORY.createConnectionAgreementItemStcCustomerNumber(
                    conAgreement.getCustomerNumber()));
            item.setNoticeToDeenergise(FACTORY.createConnectionAgreementItemStcNoticeToDeenergise(
                    conAgreement.getNoticeToDeenergise()));
            item.setPropertyNumber(FACTORY.createConnectionAgreementItemStcPropertyNumber(
                    conAgreement.getPropertyNumber()));
            return item;
        }).forEachOrdered((item) -> {
            agreements.add(item);
        });
        connectionAgreementsStc.setConnectionAgreementList(connectionAgreementListStc);
        connectionAgreements.setConnectionAgreementsStc(connectionAgreementsStc);
        WebServiceTemplate template = buildWebServiceTemplate();
        ConnectionAgreementsResponse res
                = (ConnectionAgreementsResponse) template.marshalSendAndReceive(config.getUrl(), connectionAgreements,
                        new SoapActionCallback("Customer/ConnectionAgreements"));
        ConnectionAgreementsResponseStc innerRes = res.getConnectionAgreementsResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update customer property associations
     *
     * @param input
     */
    public void processCustomerPropertyAssociations(List<CustomerPropertyAssociation> input) {
        CustomerPropertyAssociations customerPropertyAssociations = FACTORY.createCustomerPropertyAssociations();
        CustomerPropertyAssociationsStc customerPropertyAssociationsStc = FACTORY.createCustomerPropertyAssociationsStc();

        customerPropertyAssociationsStc.setOperationType("I");
        if (1 == input.size()) {
            customerPropertyAssociationsStc.setRealTime(FACTORY.createCustomerPropertyAssociationsStcRealTime(1));
        }

        CustomerPropertyAssociationListStc customerPropertyAssociationListStc = FACTORY.createCustomerPropertyAssociationListStc();
        List<CustomerPropertyAssociationItemStc> customerPropertyAssociationStc = customerPropertyAssociationListStc.getCustomerPropertyAssociationStc();

        input.stream().map((inCustPropAssoc) -> {
            CustomerPropertyAssociationItemStc item = FACTORY.createCustomerPropertyAssociationItemStc();

            item.setCustomerNumber(inCustPropAssoc.getCustomerNumber());
            item.setPropertyNumber(inCustPropAssoc.getPropertyNumber());
            item.setATCode(FACTORY.createCustomerPropertyAssociationItemStcATCode(inCustPropAssoc.getAtCode()));

            if (null != inCustPropAssoc.getUsageStartDate() && !inCustPropAssoc.getUsageStartDate().isEmpty()) {
                try {
                    item.setUsageStartDate(FACTORY.createCustomerPropertyAssociationItemStcUsageStartDate(
                            this.parseDateStringToXmlGregorianCalendar(inCustPropAssoc.getUsageStartDate(), null)));
                } catch (DatatypeConfigurationException ex) {
                    LOG.warn("Couldn't parse date {} to XMLGregorianCalendar", inCustPropAssoc.getUsageStartDate());
                    LOG.warn(ex.getMessage());
                }
            }

            if (null != inCustPropAssoc.getUsageEndDate() && !inCustPropAssoc.getUsageEndDate().isEmpty()) {
                try {
                    item.setUsageEndDate(FACTORY.createCustomerPropertyAssociationItemStcUsageEndDate(
                            this.parseDateStringToXmlGregorianCalendar(inCustPropAssoc.getUsageEndDate(), null)));
                } catch (DatatypeConfigurationException ex) {
                    LOG.warn("Couldn't parse date {} to XMLGregorianCalendar", inCustPropAssoc.getUsageStartDate());
                    LOG.warn(ex.getMessage());
                }
            }
            item.setLinkReference(FACTORY.createCustomerPropertyAssociationItemStcLinkReference(
                    inCustPropAssoc.getLinkReference()));
            item.setLinkSystem(FACTORY.createCustomerPropertyAssociationItemStcLinkSystem(
                    inCustPropAssoc.getLinkSystem()));
            item.setBusinessSource(FACTORY.createCustomerPropertyAssociationItemStcBusinessSource(
                    inCustPropAssoc.getBusinessSource()));
            item.setLinkAddressReference(FACTORY.createCustomerPropertyAssociationItemStcLinkAddressReference(
                    inCustPropAssoc.getLinkAddressReference()));
            return item;
        }).forEachOrdered((item) -> {
            customerPropertyAssociationStc.add(item);
        });
        customerPropertyAssociationsStc.setCustomerPropertyAssociationList(customerPropertyAssociationListStc);
        customerPropertyAssociations.setCustomerPropertyAssociationsStc(customerPropertyAssociationsStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        CustomerPropertyAssociationsResponse res
                = (CustomerPropertyAssociationsResponse) template.marshalSendAndReceive(
                        config.getUrl(), customerPropertyAssociations,
                        new SoapActionCallback("Customer/CustomerPropertyAssociations"));
        CustomerPropertyAssociationsResponseStc innerRes = res.getCustomerPropertyAssociationsResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update customers
     *
     * @param input
     */
    public void processCustomers(List<Customer> input) {
        Customers customers = FACTORY.createCustomers();
        CustomersStc customersStc = FACTORY.createCustomersStc();

        customersStc.setOperationType("I");
        if (1 == input.size()) {
            customersStc.setRealTime(FACTORY.createCustomersStcRealTime(1));
        }

        CustomerListStc customerListStc = FACTORY.createCustomerListStc();
        List<CustomerItemStc> customerList = customerListStc.getCustomerStc();

        input.stream().map((customer) -> {
            CustomerItemStc item = FACTORY.createCustomerItemStc();
            item.setCustomerNumber(customer.getCustomerNumber());
            item.setForeNames(FACTORY.createCustomerItemStcForeNames(customer.getForeName()));
            item.setName(FACTORY.createCustomerItemStcName(customer.getSurName()));
            return item;
        }).forEachOrdered((item) -> {
            customerList.add(item);
        });
        customersStc.setCustomerList(customerListStc);
        customers.setCustomersStc(customersStc);
        WebServiceTemplate template = buildWebServiceTemplate();
        CustomersResponse res = (CustomersResponse) template.marshalSendAndReceive(config.getUrl(), customers,
                new SoapActionCallback("Customer/Customers"));
        CustomersResponseStc innerRes = res.getCustomersResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update customer classifications
     *
     * @param input
     */
    public void processCustomerClassifications(List<CustomerClassification> input) {
        CustomerClassifications customerClassifications = FACTORY.createCustomerClassifications();
        CustomerClassificationsStc customerClassificationsStc = FACTORY.createCustomerClassificationsStc();

        customerClassificationsStc.setOperationType("I");
        if (1 == input.size()) {
            customerClassificationsStc.setRealTime(FACTORY.createCustomerClassificationsStcRealTime(1));
        }

        CustomerClassificationListStc customerClassificationListStc = FACTORY.createCustomerClassificationListStc();
        List<CustomerClassificationItemStc> customerClassificationList = customerClassificationListStc.getCustomerClassificationStc();

        input.stream().map((cusClassifications) -> {
            CustomerClassificationItemStc item = FACTORY.createCustomerClassificationItemStc();
            item.setCustomerNumber(cusClassifications.getCustomerNumber());
            item.setCTCode(FACTORY.createCustomerClassificationItemStcCTCode(String.valueOf(cusClassifications.getCtCode())));
            return item;
        }).forEachOrdered((item) -> {
            customerClassificationList.add(item);
        });
        customerClassificationsStc.setCustomerClassificationList(customerClassificationListStc);
        customerClassifications.setCustomerClassificationsStc(customerClassificationsStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        CustomerClassificationsResponse res = (CustomerClassificationsResponse) template.marshalSendAndReceive(
                config.getUrl(), customerClassifications,
                new SoapActionCallback("Customer/CustomerClassifications"));
        CustomerClassificationsResponseStc innerRes = res.getCustomerClassificationsResponseStc();

        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update meter point numbers
     *
     * @param input
     */
    public void processMeterNumbers(List<MeterNumber> input) {
        MeterNumbers meterNumbers = FACTORY.createMeterNumbers();
        MeterNumbersStc meterNumbersStc = FACTORY.createMeterNumbersStc();

        meterNumbersStc.setOperationType("I");
        if (1 == input.size()) {
            meterNumbersStc.setRealTime(FACTORY.createMeterNumbersStcRealTime(1));
        }

        MeterNumberListStc meterNumberListStc = FACTORY.createMeterNumberListStc();
        List<MeterNumberItemStc> meterNumberStcList = meterNumberListStc.getMeterNumberStc();

        input.stream().map((meterNumber) -> {
            MeterNumberItemStc item = FACTORY.createMeterNumberItemStc();
            item.setPropertyNumber(meterNumber.getPropertyNumber());
            item.setMeterNumber(FACTORY.createMeterNumberItemStcMeterNumber(meterNumber.getMeterNumber()));
            item.setIsSmartMeter(FACTORY.createMeterNumberItemStcIsSmartMeter(meterNumber.getIsSmartMeter()));
            return item;
        }).forEachOrdered((item) -> {
            meterNumberStcList.add(item);
        });

        meterNumbersStc.setMeterNumberList(meterNumberListStc);
        meterNumbers.setMeterNumbersStc(meterNumbersStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        MeterNumbersResponse res = (MeterNumbersResponse) template.marshalSendAndReceive(
                config.getUrl(), meterNumbers,
                new SoapActionCallback("Customer/MeterNumbers"));
        MeterNumbersResponseStc innerRes = res.getMeterNumbersResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update properties
     *
     * @param input
     */
    public void processProperties(List<Property> input) {
        Properties properties = FACTORY.createProperties();
        PropertiesStc propertiesStc = FACTORY.createPropertiesStc();

        propertiesStc.setOperationType("I");
        if (1 == input.size()) {
            propertiesStc.setRealTime(FACTORY.createPropertiesStcRealTime(1));
        }

        PropertyListStc propertyListStc = FACTORY.createPropertyListStc();
        List<PropertyItemStc> propertyStcList = propertyListStc.getPropertyStc();

        input.forEach((property) -> {
            PropertyItemStc item = FACTORY.createPropertyItemStc();
            item.setAddressNumber(property.getAddressNumber());
            item.setPropertyNumber(property.getPropertyNumber());
            propertyStcList.add(item);
        });

        propertiesStc.setPropertyList(propertyListStc);
        properties.setPropertiesStc(propertiesStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        PropertiesResponse res = (PropertiesResponse) template.marshalSendAndReceive(config.getUrl(), properties,
                new SoapActionCallback("Customer/Properties"));

        PropertiesResponseStc innerRes = res.getPropertiesResponseStc();
        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update property classifications
     *
     * @param input
     */
    public void processPropertyClassifications(List<PropertyClassification> input) {
        PropertyClassifications propertyClassifications = FACTORY.createPropertyClassifications();
        PropertyClassificationsStc propertyClassificationsStc = FACTORY.createPropertyClassificationsStc();

        propertyClassificationsStc.setOperationType("I");
        if (1 == input.size()) {
            propertyClassificationsStc.setRealTime(FACTORY.createPropertyClassificationsStcRealTime(1));
        }

        PropertyClassificationListStc propertyClassificationListStc = FACTORY.createPropertyClassificationListStc();
        List<PropertyClassificationItemStc> propertyClassificationStcList = propertyClassificationListStc.getPropertyClassificationStc();

        input.stream().map((propertyClassification) -> {
            PropertyClassificationItemStc item = FACTORY.createPropertyClassificationItemStc();
            item.setPropertyNumber(propertyClassification.getPropertyNumber());
            item.setPTCode(FACTORY.createPropertyClassificationItemStcPTCode(String.valueOf(propertyClassification.getPtCode())));
            return item;
        }).forEachOrdered((item) -> {
            propertyClassificationStcList.add(item);
        });

        propertyClassificationsStc.setPropertyClassificationList(propertyClassificationListStc);
        propertyClassifications.setPropertyClassificationsStc(propertyClassificationsStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        PropertyClassificationsResponse res = (PropertyClassificationsResponse) template.marshalSendAndReceive(config.getUrl(), propertyClassifications,
                new SoapActionCallback("Customer/PropertyClassifications"));
        PropertyClassificationsResponseStc innerRes = res.getPropertyClassificationsResponseStc();

        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * Insert/update phone numbers
     *
     * @param input
     */
    public void processPhoneNumbers(List<PhoneNumber> input) {
        TelephoneNumbers telephoneNumbers = FACTORY.createTelephoneNumbers();
        TelephoneNumbersStc telephoneNumbersStc = FACTORY.createTelephoneNumbersStc();

        telephoneNumbersStc.setOperationType("I");
        if (1 == input.size()) {
            telephoneNumbersStc.setRealTime(FACTORY.createTelephoneNumbersStcRealTime(1));
        }

        TelephoneNumberListStc telephoneNumberListStc = FACTORY.createTelephoneNumberListStc();
        List<TelephoneNumberItemStc> telephoneNumberStcList = telephoneNumberListStc.getTelephoneNumberStc();

        input.stream().map((phoneNumber) -> {
            TelephoneNumberItemStc item = FACTORY.createTelephoneNumberItemStc();
            item.setPropertyNumber(phoneNumber.getPropertyNumber());
            item.setTelephoneNumber(phoneNumber.getPhoneNumber());
            if (null != phoneNumber.getUsageStartDate() && !phoneNumber.getUsageStartDate().isEmpty()) {
                try {
                    item.setUsageStartDate(FACTORY.createTelephoneNumberItemStcUsageStartDate(parseDateStringToXmlGregorianCalendar(phoneNumber.getUsageStartDate(), null)));
                } catch (DatatypeConfigurationException ex) {
                    LOG.warn("Couldn't parse date {} to XMLGregorianCalendar", phoneNumber.getUsageStartDate());
                    LOG.warn(ex.getMessage());
                }
            }
            if (null != phoneNumber.getUsageEndDate() && !phoneNumber.getUsageEndDate().isEmpty()) {
                try {
                    item.setUsageEndDate(FACTORY.createTelephoneNumberItemStcUsageEndDate(parseDateStringToXmlGregorianCalendar(phoneNumber.getUsageEndDate(), null)));
                } catch (DatatypeConfigurationException ex) {
                    LOG.warn("Couldn't parse date {} to XMLGregorianCalendar", phoneNumber.getUsageEndDate());
                    LOG.warn(ex.getMessage());
                }
            }
            item.setType(FACTORY.createTelephoneNumberItemStcType(phoneNumber.getType()));
            return item;
        }).forEachOrdered((item) -> {
            telephoneNumberStcList.add(item);
        });

        telephoneNumbersStc.setTelephoneNumberList(telephoneNumberListStc);
        telephoneNumbers.setTelephoneNumbersStc(telephoneNumbersStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        TelephoneNumbersResponse res = (TelephoneNumbersResponse) template.marshalSendAndReceive(config.getUrl(), telephoneNumbers,
                new SoapActionCallback("Customer/TelephoneNumbers"));

        TelephoneNumbersResponseStc innerRes = res.getTelephoneNumbersResponseStc();
        LOG.info("status: {}, message: {} - {}", innerRes.getStatus(), innerRes.getTransactionErrors(), innerRes.getErrorMsg());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors() + " " + innerRes.getErrorMsg());
        }

    }

    /**
     *
     * @param input
     */
    public void processNetworkPropertyLinks(List<NetworkPropertyLink> input) {
        NetworkPropertyLinks networkPropertyLinks = FACTORY.createNetworkPropertyLinks();
        NetworkPropertyLinksStc networkPropertyLinksStc = FACTORY.createNetworkPropertyLinksStc();

        networkPropertyLinksStc.setOperationType("I");
        if (1 == input.size()) {
            networkPropertyLinksStc.setRealTime(FACTORY.createNetworkPropertyLinksStcRealTime(1));
        }

        NetworkPropertyLinkListStc networkPropertyLinkListStc = FACTORY.createNetworkPropertyLinkListStc();
        List<NetworkPropertyLinkItemStc> networkPropertyLinkStcList = networkPropertyLinkListStc.getNetworkPropertyLinkStc();

        input.stream().map((networkPropertyLink) -> {
            NetworkPropertyLinkItemStc item = FACTORY.createNetworkPropertyLinkItemStc();
            item.setPropertyNumber(networkPropertyLink.getPropertyNumber());
            item.setFeederNumber(FACTORY.createNetworkPropertyLinkItemStcFeederNumber(String.valueOf(networkPropertyLink.getFeederNumber())));
            item.setFeedQualityID(FACTORY.createNetworkPropertyLinkItemStcFeedQualityID(networkPropertyLink.getFeederQualityID()));
            item.setPhase(FACTORY.createNetworkPropertyLinkItemStcPhase(String.valueOf(networkPropertyLink.getPhase())));
            item.setNetworkStatus(FACTORY.createNetworkPropertyLinkItemStcNetworkStatus(networkPropertyLink.getNetworkStatus()));
            return item;
        }).forEachOrdered((item) -> {
            networkPropertyLinkStcList.add(item);
        });

        networkPropertyLinksStc.setNetworkPropertyLinkList(networkPropertyLinkListStc);
        networkPropertyLinks.setNetworkPropertyLinksStc(networkPropertyLinksStc);

        WebServiceTemplate template = buildWebServiceTemplate();
        NetworkPropertyLinksResponse res = (NetworkPropertyLinksResponse) template.marshalSendAndReceive(config.getUrl(), networkPropertyLinks,
                new SoapActionCallback("Customer/NetworkPropertyLinks"));
        NetworkPropertyLinksResponseStc innerRes = res.getNetworkPropertyLinksResponseStc();

        LOG.info("status: {}, message: {}", innerRes.getStatus(), innerRes.getTransactionErrors());
        if (isNotOk(innerRes.getStatus())) {
            throw new OperationException(innerRes.getTransactionErrors());
        }
    }

    /**
     * utility function to build and return web service template
     *
     * @return
     */
    private WebServiceTemplate buildWebServiceTemplate() {
        NamespacePrefixMapper mapper = new NamespacePrefixMapper() {
            @Override
            public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
                return "cus";
            }
        };
        Map<String, Object> props = new HashMap();
        props.put("jaxb.formatted.output", Boolean.TRUE);
        props.put("com.sun.xml.bind.namespacePrefixMapper", mapper);

        Jaxb2Marshaller m = new Jaxb2Marshaller();
        m.setMarshallerProperties(props);
        m.setContextPath("poweron.wsdl");
        WebServiceTemplate template = getWebServiceTemplate();
        template.setMarshaller(m);
        template.setUnmarshaller(m);
        return template;
    }

    /**
     * utility funciton to convert date string into XMLGregorianCalender instance
     *
     * @param date date (time) string
     * @param format not used yet
     * @return instance of XMLGregorianCalendar for given date
     * @throws DatatypeConfigurationException
     */
    private XMLGregorianCalendar parseDateStringToXmlGregorianCalendar(String date, String format) throws DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
    }

    /**
     * function to check power on response status code
     *
     * @param i PowerOn status code as int
     * @return boolean true if status not equal to success code (which is 0)
     */
    private boolean isNotOk(int i) {
        return i != 0;
    }
}
