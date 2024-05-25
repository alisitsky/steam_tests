package com.steampowered.api.helpers;

import com.steampowered.api.model.GetNewsForAppResponseBodyModel;

import java.util.List;

public class GetNewsForAppHelpers {

    public static List<GetNewsForAppResponseBodyModel.AppNews.NewsItems> getNewsList(GetNewsForAppResponseBodyModel bm) {
        return bm.getAppNews().getNewsItems();
    }

}
