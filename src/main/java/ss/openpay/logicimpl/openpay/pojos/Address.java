package ss.openpay.logicimpl.openpay.pojos;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "line1",
        "line2",
        "line3",
        "state",
        "city",
        "postal_code",
        "country_code"
})
public class Address {

    @JsonProperty("line1")
    private String line1;
    @JsonProperty("line2")
    private Object line2;
    @JsonProperty("line3")
    private Object line3;
    @JsonProperty("state")
    private String state;
    @JsonProperty("city")
    private String city;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The line1
     */
    @JsonProperty("line1")
    public String getLine1() {
        return line1;
    }

    /**
     *
     * @param line1
     * The line1
     */
    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     *
     * @return
     * The line2
     */
    @JsonProperty("line2")
    public Object getLine2() {
        return line2;
    }

    /**
     *
     * @param line2
     * The line2
     */
    @JsonProperty("line2")
    public void setLine2(Object line2) {
        this.line2 = line2;
    }

    /**
     *
     * @return
     * The line3
     */
    @JsonProperty("line3")
    public Object getLine3() {
        return line3;
    }

    /**
     *
     * @param line3
     * The line3
     */
    @JsonProperty("line3")
    public void setLine3(Object line3) {
        this.line3 = line3;
    }

    /**
     *
     * @return
     * The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The postalCode
     */
    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     * The postal_code
     */
    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     * The countryCode
     */
    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param countryCode
     * The country_code
     */
    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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