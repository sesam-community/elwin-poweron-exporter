package io.sesam.fredrikstad.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author 100tsa
 */
public class SesamEntity {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_deleted")
    private boolean deleted;
    
    @JsonProperty("operation")
    private String operation;

    private String status;

    public SesamEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
    @Override
    public String toString() {
        return "SesamEntity{" + "id=" + id + ", deleted=" + deleted + ", status=" + status + '}';
    }

}
