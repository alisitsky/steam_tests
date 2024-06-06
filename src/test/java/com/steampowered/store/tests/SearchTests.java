package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import com.steampowered.store.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.steampowered.store.data.TestData.game1Title;

@Layer("ui")
@Epic("Game Search")
@Feature("Search Input")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    GamePage gamePage = new GamePage();

    @Test
    @AllureId("32647")
    @Story("Search game by name")
    @DisplayName("Select game from search suggest via click")
    @Owner("alisitsky")
    public void selectFromSearchSuggestByClickTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .clickTopItemFromSuggest();
        gamePage.checkPageTitleIs(game1Title);
    }

    @Test
    @AllureId("32700")
    @Story("Search game by name")
    @DisplayName("Select game from search suggest via keyboard")
    @Owner("alisitsky")
    public void selectFromSearchSuggestWithKeyboardTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .chooseTopItemFromSuggestWithKeyboard();
        gamePage.checkPageTitleIs(game1Title);
    }
}
