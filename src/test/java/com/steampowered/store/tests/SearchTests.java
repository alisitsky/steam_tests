package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import com.steampowered.store.pages.MainPage;
import org.junit.jupiter.api.Test;

import static com.steampowered.store.data.TestData.game1Title;

public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    GamePage gamePage = new GamePage();

    @Test
    public void selectFromSearchSuggestByClickTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .clickTopItemFromSuggest();
        gamePage.checkPageTitleIs(game1Title);
    }

    @Test
    public void selectFromSearchSuggestWithKeyboardTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .chooseTopItemFromSuggestWithKeyboard();
        gamePage.checkPageTitleIs(game1Title);
    }
}
