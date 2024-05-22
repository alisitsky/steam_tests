package com.steampowered.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetNewsForAppResponseBodyModel {

    @JsonProperty("appnews")
    private AppNews appNews;

    @Data
    public static class AppNews {

        @JsonProperty("appid")
        private int appId;
        @JsonProperty("newsitems")
        private List<NewsItems> newsItems;

        @Data
        public static class NewsItems {

            @JsonProperty("gid")
            private String gId;
            private String title;
            private String url;
            @JsonProperty("is_external_url")
            private boolean isExternalUrl;
            private String author;
            private String contents;
            @JsonProperty("feedlabel")
            private String feedLabel;
            private int date;
            @JsonProperty("feedname")
            private String feedName;
            @JsonProperty("feed_type")
            private int feedType;
            @JsonProperty("appid")
            private int appId;
            private List<String> tags;
        }

        private int count;
    }
}
