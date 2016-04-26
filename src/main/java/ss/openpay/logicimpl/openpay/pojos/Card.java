package ss.openpay.logicimpl.openpay.pojos;

/**
 * Created by carlos on 1/04/16.
 */

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type",
        "brand",
        "address",
        "card_number",
        "holder_name",
        "expiration_month",
        "expiration_year",
        "allows_charges",
        "allows_payouts",
        "creation_date",
        "bank_name",
        "bank_code"
})
public class Card {

    @JsonProperty("type")
    private String type;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("holder_name")
    private String holderName;
    @JsonProperty("expiration_month")
    private String expirationMonth;
    @JsonProperty("expiration_year")
    private String expirationYear;
    @JsonProperty("allows_charges")
    private Boolean allowsCharges;
    @JsonProperty("allows_payouts")
    private Boolean allowsPayouts;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("bank_name")
    private String bankName;
    @JsonProperty("bank_code")
    private String bankCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The brand
     */
    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @param brand
     * The brand
     */
    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return
     * The address
     */
    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The cardNumber
     */
    @JsonProperty("card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     *
     * @param cardNumber
     * The card_number
     */
    @JsonProperty("card_number")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     *
     * @return
     * The holderName
     */
    @JsonProperty("holder_name")
    public String getHolderName() {
        return holderName;
    }

    /**
     *
     * @param holderName
     * The holder_name
     */
    @JsonProperty("holder_name")
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    /**
     *
     * @return
     * The expirationMonth
     */
    @JsonProperty("expiration_month")
    public String getExpirationMonth() {
        return expirationMonth;
    }

    /**
     *
     * @param expirationMonth
     * The expiration_month
     */
    @JsonProperty("expiration_month")
    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    /**
     *
     * @return
     * The expirationYear
     */
    @JsonProperty("expiration_year")
    public String getExpirationYear() {
        return expirationYear;
    }

    /**
     *
     * @param expirationYear
     * The expiration_year
     */
    @JsonProperty("expiration_year")
    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    /**
     *
     * @return
     * The allowsCharges
     */
    @JsonProperty("allows_charges")
    public Boolean getAllowsCharges() {
        return allowsCharges;
    }

    /**
     *
     * @param allowsCharges
     * The allows_charges
     */
    @JsonProperty("allows_charges")
    public void setAllowsCharges(Boolean allowsCharges) {
        this.allowsCharges = allowsCharges;
    }

    /**
     *
     * @return
     * The allowsPayouts
     */
    @JsonProperty("allows_payouts")
    public Boolean getAllowsPayouts() {
        return allowsPayouts;
    }

    /**
     *
     * @param allowsPayouts
     * The allows_payouts
     */
    @JsonProperty("allows_payouts")
    public void setAllowsPayouts(Boolean allowsPayouts) {
        this.allowsPayouts = allowsPayouts;
    }

    /**
     *
     * @return
     * The creationDate
     */
    @JsonProperty("creation_date")
    public String getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     * The creation_date
     */
    @JsonProperty("creation_date")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return
     * The bankName
     */
    @JsonProperty("bank_name")
    public String getBankName() {
        return bankName;
    }

    /**
     *
     * @param bankName
     * The bank_name
     */
    @JsonProperty("bank_name")
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     *
     * @return
     * The bankCode
     */
    @JsonProperty("bank_code")
    public String getBankCode() {
        return bankCode;
    }

    /**
     *
     * @param bankCode
     * The bank_code
     */
    @JsonProperty("bank_code")
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
