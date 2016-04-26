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
        "amount",
        "authorization",
        "method",
        "operation_type",
        "transaction_type",
        "card",
        "status",
        "id",
        "creation_date",
        "description",
        "error_message",
        "order_id"
})
public class Transaction {

    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("authorization")
    private String authorization;
    @JsonProperty("method")
    private String method;
    @JsonProperty("operation_type")
    private String operationType;
    @JsonProperty("transaction_type")
    private String transactionType;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("status")
    private String status;
    @JsonProperty("id")
    private String id;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("error_message")
    private Object errorMessage;
    @JsonProperty("order_id")
    private String orderId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The amount
     */
    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     * The amount
     */
    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The authorization
     */
    @JsonProperty("authorization")
    public String getAuthorization() {
        return authorization;
    }

    /**
     *
     * @param authorization
     * The authorization
     */
    @JsonProperty("authorization")
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    /**
     *
     * @return
     * The method
     */
    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     * The method
     */
    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @return
     * The operationType
     */
    @JsonProperty("operation_type")
    public String getOperationType() {
        return operationType;
    }

    /**
     *
     * @param operationType
     * The operation_type
     */
    @JsonProperty("operation_type")
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     *
     * @return
     * The transactionType
     */
    @JsonProperty("transaction_type")
    public String getTransactionType() {
        return transactionType;
    }

    /**
     *
     * @param transactionType
     * The transaction_type
     */
    @JsonProperty("transaction_type")
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     *
     * @return
     * The card
     */
    @JsonProperty("card")
    public Card getCard() {
        return card;
    }

    /**
     *
     * @param card
     * The card
     */
    @JsonProperty("card")
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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
     * The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The errorMessage
     */
    @JsonProperty("error_message")
    public Object getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @param errorMessage
     * The error_message
     */
    @JsonProperty("error_message")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     *
     * @return
     * The orderId
     */
    @JsonProperty("order_id")
    public String getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId
     * The order_id
     */
    @JsonProperty("order_id")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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