package com.patel.tupdates.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
   // String destination_addresses;
 //   String origin_addresses;


    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    @JsonProperty("destination_addresses")
    private List<String> destinations;

    @JsonProperty("origin_addresses")
    private List<String> origins;

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    @JsonProperty("rows")
    private List<Rows> rows = Collections.emptyList();

    @Override
    public String toString() {
        return "Result{" +
                "destinations=" + destinations +
                ", origins=" + origins +
                ", rows=" + rows +
                '}';
    }
}
