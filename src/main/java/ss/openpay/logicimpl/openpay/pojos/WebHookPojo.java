package ss.openpay.logicimpl.openpay.pojos;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type",
        "event_date",
        "transaction"
})
public class WebHookPojo {

    @JsonProperty("type")
    private String type;
    @JsonProperty("event_date")
    private String eventDate;
    @JsonProperty("transaction")
    private Transaction transaction;
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
     * The eventDate
     */
    @JsonProperty("event_date")
    public String getEventDate() {
        return eventDate;
    }

    /**
     *
     * @param eventDate
     * The event_date
     */
    @JsonProperty("event_date")
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     *
     * @return
     * The transaction
     */
    @JsonProperty("transaction")
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     *
     * @param transaction
     * The transaction
     */
    @JsonProperty("transaction")
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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