package com.example.mymapview;

import java.util.List;

public class AddressResponse {

    private Result result;

    public static class Result {
        private Verdict verdict;
        private Address address;
        private Geocode geocode;
        private Metadata metadata;
        private UspsData uspsData;

        // Getters and Setters
        public Verdict getVerdict() {
            return verdict;
        }

        public void setVerdict(Verdict verdict) {
            this.verdict = verdict;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Geocode getGeocode() {
            return geocode;
        }

        public void setGeocode(Geocode geocode) {
            this.geocode = geocode;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public void setMetadata(Metadata metadata) {
            this.metadata = metadata;
        }

        public UspsData getUspsData() {
            return uspsData;
        }

        public void setUspsData(UspsData uspsData) {
            this.uspsData = uspsData;
        }
    }

    public static class Verdict {
        private String inputGranularity;
        private String validationGranularity;
        private String geocodeGranularity;
        private boolean addressComplete;
        private boolean hasInferredComponents;

        // Getters and Setters
        public String getInputGranularity() {
            return inputGranularity;
        }

        public void setInputGranularity(String inputGranularity) {
            this.inputGranularity = inputGranularity;
        }

        public String getValidationGranularity() {
            return validationGranularity;
        }

        public void setValidationGranularity(String validationGranularity) {
            this.validationGranularity = validationGranularity;
        }

        public String getGeocodeGranularity() {
            return geocodeGranularity;
        }

        public void setGeocodeGranularity(String geocodeGranularity) {
            this.geocodeGranularity = geocodeGranularity;
        }

        public boolean isAddressComplete() {
            return addressComplete;
        }

        public void setAddressComplete(boolean addressComplete) {
            this.addressComplete = addressComplete;
        }

        public boolean isHasInferredComponents() {
            return hasInferredComponents;
        }

        public void setHasInferredComponents(boolean hasInferredComponents) {
            this.hasInferredComponents = hasInferredComponents;
        }
    }

    public static class Address {
        private String formattedAddress;
        private PostalAddress postalAddress;
        private List<AddressComponent> addressComponents;

        // Getters and Setters
        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public PostalAddress getPostalAddress() {
            return postalAddress;
        }

        public void setPostalAddress(PostalAddress postalAddress) {
            this.postalAddress = postalAddress;
        }

        public List<AddressComponent> getAddressComponents() {
            return addressComponents;
        }

        public void setAddressComponents(List<AddressComponent> addressComponents) {
            this.addressComponents = addressComponents;
        }
    }

    public static class PostalAddress {
        private String regionCode;
        private String languageCode;
        private String postalCode;
        private String administrativeArea;
        private String locality;
        private List<String> addressLines;

        // Getters and Setters
        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getAdministrativeArea() {
            return administrativeArea;
        }

        public void setAdministrativeArea(String administrativeArea) {
            this.administrativeArea = administrativeArea;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public List<String> getAddressLines() {
            return addressLines;
        }

        public void setAddressLines(List<String> addressLines) {
            this.addressLines = addressLines;
        }
    }

    public static class AddressComponent {
        private ComponentName componentName;
        private String componentType;
        private String confirmationLevel;
        private boolean inferred;

        // Getters and Setters
        public ComponentName getComponentName() {
            return componentName;
        }

        public void setComponentName(ComponentName componentName) {
            this.componentName = componentName;
        }

        public String getComponentType() {
            return componentType;
        }

        public void setComponentType(String componentType) {
            this.componentType = componentType;
        }

        public String getConfirmationLevel() {
            return confirmationLevel;
        }

        public void setConfirmationLevel(String confirmationLevel) {
            this.confirmationLevel = confirmationLevel;
        }

        public boolean isInferred() {
            return inferred;
        }

        public void setInferred(boolean inferred) {
            this.inferred = inferred;
        }
    }

    public static class ComponentName {
        private String text;
        private String languageCode;

        // Getters and Setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }
    }

    public static class Geocode {
        private Location location;
        private PlusCode plusCode;
        private Bounds bounds;
        private String placeId;
        private List<String> placeTypes;

        // Getters and Setters
        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public PlusCode getPlusCode() {
            return plusCode;
        }

        public void setPlusCode(PlusCode plusCode) {
            this.plusCode = plusCode;
        }

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public List<String> getPlaceTypes() {
            return placeTypes;
        }

        public void setPlaceTypes(List<String> placeTypes) {
            this.placeTypes = placeTypes;
        }
    }

    public static class Location {
        private double latitude;
        private double longitude;

        // Getters and Setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class PlusCode {
        private String globalCode;

        // Getters and Setters
        public String getGlobalCode() {
            return globalCode;
        }

        public void setGlobalCode(String globalCode) {
            this.globalCode = globalCode;
        }
    }

    public static class Bounds {
        private Low high;
        private Low low;

        // Getters and Setters
        public Low getHigh() {
            return high;
        }

        public void setHigh(Low high) {
            this.high = high;
        }

        public Low getLow() {
            return low;
        }

        public void setLow(Low low) {
            this.low = low;
        }
    }

    public static class Low {
        private double latitude;
        private double longitude;

        // Getters and Setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

    public static class Metadata {
        private boolean business;
        private boolean residential;

        // Getters and Setters
        public boolean isBusiness() {
            return business;
        }

        public void setBusiness(boolean business) {
            this.business = business;
        }

        public boolean isResidential() {
            return residential;
        }

        public void setResidential(boolean residential) {
            this.residential = residential;
        }
    }

    public static class UspsData {
        private StandardizedAddress standardizedAddress;
        private String deliveryPointCode;
        private String deliveryPointCheckDigit;
        private String dpvConfirmation;
        private String dpvFootnote;
        private String dpvCmra;
        private String dpvVacant;
        private String dpvNoStat;
        private String carrierRouteIndicator;
        private String postOfficeCity;
        private String postOfficeState;
        private String fipsCountyCode;
        private String county;
        private String elotNumber;
        private String elotFlag;
        private String addressRecordType;
        private int dpvNoStatReasonCode;
        private String dpvDrop;
        private String dpvThrowback;
        private String dpvNonDeliveryDays;
        private String dpvNoSecureLocation;
        private String dpvPbsa;
        private String dpvDoorNotAccessible;
        private String dpvEnhancedDeliveryCode;

        // Getters and Setters
        public StandardizedAddress getStandardizedAddress() {
            return standardizedAddress;
        }

        public void setStandardizedAddress(StandardizedAddress standardizedAddress) {
            this.standardizedAddress = standardizedAddress;
        }

        public String getDeliveryPointCode() {
            return deliveryPointCode;
        }

        public void setDeliveryPointCode(String deliveryPointCode) {
            this.deliveryPointCode = deliveryPointCode;
        }

        public String getDeliveryPointCheckDigit() {
            return deliveryPointCheckDigit;
        }

        public void setDeliveryPointCheckDigit(String deliveryPointCheckDigit) {
            this.deliveryPointCheckDigit = deliveryPointCheckDigit;
        }

        public String getDpvConfirmation() {
            return dpvConfirmation;
        }

        public void setDpvConfirmation(String dpvConfirmation) {
            this.dpvConfirmation = dpvConfirmation;
        }

        public String getDpvFootnote() {
            return dpvFootnote;
        }

        public void setDpvFootnote(String dpvFootnote) {
            this.dpvFootnote = dpvFootnote;
        }

        public String getDpvCmra() {
            return dpvCmra;
        }

        public void setDpvCmra(String dpvCmra) {
            this.dpvCmra = dpvCmra;
        }

        public String getDpvVacant() {
            return dpvVacant;
        }

        public void setDpvVacant(String dpvVacant) {
            this.dpvVacant = dpvVacant;
        }

        public String getDpvNoStat() {
            return dpvNoStat;
        }

        public void setDpvNoStat(String dpvNoStat) {
            this.dpvNoStat = dpvNoStat;
        }

        public String getCarrierRouteIndicator() {
            return carrierRouteIndicator;
        }

        public void setCarrierRouteIndicator(String carrierRouteIndicator) {
            this.carrierRouteIndicator = carrierRouteIndicator;
        }

        public String getPostOfficeCity() {
            return postOfficeCity;
        }

        public void setPostOfficeCity(String postOfficeCity) {
            this.postOfficeCity = postOfficeCity;
        }

        public String getPostOfficeState() {
            return postOfficeState;
        }

        public void setPostOfficeState(String postOfficeState) {
            this.postOfficeState = postOfficeState;
        }

        public String getFipsCountyCode() {
            return fipsCountyCode;
        }

        public void setFipsCountyCode(String fipsCountyCode) {
            this.fipsCountyCode = fipsCountyCode;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getElotNumber() {
            return elotNumber;
        }

        public void setElotNumber(String elotNumber) {
            this.elotNumber = elotNumber;
        }

        public String getElotFlag() {
            return elotFlag;
        }

        public void setElotFlag(String elotFlag) {
            this.elotFlag = elotFlag;
        }

        public String getAddressRecordType() {
            return addressRecordType;
        }

        public void setAddressRecordType(String addressRecordType) {
            this.addressRecordType = addressRecordType;
        }

        public int getDpvNoStatReasonCode() {
            return dpvNoStatReasonCode;
        }

        public void setDpvNoStatReasonCode(int dpvNoStatReasonCode) {
            this.dpvNoStatReasonCode = dpvNoStatReasonCode;
        }

        public String getDpvDrop() {
            return dpvDrop;
        }

        public void setDpvDrop(String dpvDrop) {
            this.dpvDrop = dpvDrop;
        }

        public String getDpvThrowback() {
            return dpvThrowback;
        }

        public void setDpvThrowback(String dpvThrowback) {
            this.dpvThrowback = dpvThrowback;
        }

        public String getDpvNonDeliveryDays() {
            return dpvNonDeliveryDays;
        }

        public void setDpvNonDeliveryDays(String dpvNonDeliveryDays) {
            this.dpvNonDeliveryDays = dpvNonDeliveryDays;
        }

        public String getDpvNoSecureLocation() {
            return dpvNoSecureLocation;
        }

        public void setDpvNoSecureLocation(String dpvNoSecureLocation) {
            this.dpvNoSecureLocation = dpvNoSecureLocation;
        }

        public String getDpvPbsa() {
            return dpvPbsa;
        }

        public void setDpvPbsa(String dpvPbsa) {
            this.dpvPbsa = dpvPbsa;
        }

        public String getDpvDoorNotAccessible() {
            return dpvDoorNotAccessible;
        }

        public void setDpvDoorNotAccessible(String dpvDoorNotAccessible) {
            this.dpvDoorNotAccessible = dpvDoorNotAccessible;
        }

        public String getDpvEnhancedDeliveryCode() {
            return dpvEnhancedDeliveryCode;
        }

        public void setDpvEnhancedDeliveryCode(String dpvEnhancedDeliveryCode) {
            this.dpvEnhancedDeliveryCode = dpvEnhancedDeliveryCode;
        }
    }

    public static class StandardizedAddress {
        private String firstAddressLine;
        private String cityStateZipAddressLine;
        private String city;
        private String state;
        private String zipCode;
        private String zipCodeExtension;

        // Getters and Setters
        public String getFirstAddressLine() {
            return firstAddressLine;
        }

        public void setFirstAddressLine(String firstAddressLine) {
            this.firstAddressLine = firstAddressLine;
        }

        public String getCityStateZipAddressLine() {
            return cityStateZipAddressLine;
        }

        public void setCityStateZipAddressLine(String cityStateZipAddressLine) {
            this.cityStateZipAddressLine = cityStateZipAddressLine;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getZipCodeExtension() {
            return zipCodeExtension;
        }

        public void setZipCodeExtension(String zipCodeExtension) {
            this.zipCodeExtension = zipCodeExtension;
        }
    }

    // Getters and Setters for AddressResponse class
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

