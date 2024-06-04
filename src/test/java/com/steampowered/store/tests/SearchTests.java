package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import com.steampowered.store.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
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

        step("Open main page", () -> {
            mainPage.openPage();
        });

        step("Set game title into search input", () -> {
            mainPage.setGameTitleIntoSearchInput(game1Title);
        });

        step("Set game title into search input", () -> {
            mainPage.setGameTitleIntoSearchInput(game1Title);
            step("Search suggest is visible", () -> {
                mainPage.searchSuggestIsVisible();
            });
        });

        step("Click 1st game from the suggest list", () -> {
            mainPage.clickTopItemFromSuggest();
        });

        step("Page title corresponds to chosen game", () -> {
            gamePage.checkPageTitleIs(game1Title);
        });
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
