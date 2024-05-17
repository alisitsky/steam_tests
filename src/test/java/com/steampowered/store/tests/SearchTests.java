package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import com.steampowered.store.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;

import static com.steampowered.store.data.TestData.game1Title;

@Epic("Game Search")
@Feature("Search Input")
public class SearchTests extends TestBase {

    MainPage mainPage = new MainPage();
    GamePage gamePage = new GamePage();

    @Test
    @Story("Search game by name")
    @DisplayName("Select game from search suggest via click")
    public void selectFromSearchSuggestByClickTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .clickTopItemFromSuggest();
        gamePage.checkPageTitleIs(game1Title);
    }

    @Test
    @Story("Search game by name")
    @DisplayName("Select game from search suggest via keyboard")
    public void selectFromSearchSuggestWithKeyboardTest() {
        mainPage.openPage()
                .setGameTitleIntoSearchInput(game1Title)
                .searchSuggestIsVisible()
                .chooseTopItemFromSuggestWithKeyboard();
        gamePage.checkPageTitleIs(game1Title);
    }
}
