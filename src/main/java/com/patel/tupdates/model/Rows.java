package com.patel.tupdates.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rows {

        public List<Elements> getElements() {
                return elements;
        }

        public void setElements(List<Elements> elements) {
                this.elements = elements;
        }

        @JsonProperty("elements")
        private List<Elements> elements = Collections.emptyList();

        @Override
        public String toString() {
                return "Rows{" +
                        "elements=" + elements +
                        '}';
        }
}
