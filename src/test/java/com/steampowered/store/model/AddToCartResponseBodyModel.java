package com.steampowered.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddToCartResponseBodyModel {
    private boolean success;
    private Contents contents;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contents {
        @JsonProperty("lineitems")
        private List<LineItem> lineItems;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LineItem {
        @JsonProperty("package_item")
        private PackageItem packageItem;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PackageItem {
        @JsonProperty("packageid")
        private int packageId;
    }
}