package com.patel.tupdates.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Elements {
    public TrafficDuration getTrafficDuration() {
        return trafficDuration;
    }

    public void setTrafficDuration(TrafficDuration trafficDuration) {
        this.trafficDuration = trafficDuration;
    }

    @JsonProperty("duration_in_traffic")
    private TrafficDuration trafficDuration;

    @Override
    public String toString() {
        return "Elements{" +
                "trafficDuration=" + trafficDuration +
                '}';
    }
}
