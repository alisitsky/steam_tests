package com.steampowered.store.data;

import java.util.stream.Stream;

public class TestData {

    public static String    game1Title = "Disco Elysium",
                            game2Title = "Pathologic 2",
                            game3Title = "The Talos Principle 2",
                            game1PagePath = "/app/632470/Disco_Elysium__The_Final_Cut/",
                            game2SubId = "407916",
                            game3SubId = "260435",
                            addToCartApiPath = "/cart/addtocart",
                            estimatedTotalBeforeChange;

    public static Stream<String[]> changeLanguageData() {
        return Stream.of(
                new String[]{"Français", "MAGASIN"},
                new String[]{"Deutsch ", "SHOP"},
                new String[]{"Español ", "TIENDA"});
    }
}
