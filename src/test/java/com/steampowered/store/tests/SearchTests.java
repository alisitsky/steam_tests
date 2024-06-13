package com.steampowered.store.tests;

import com.steampowered.store.pages.GamePage;
import com.steampowered.store.pages.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.steampowered.store.data.TestData.game1Title;
import static io.qameta.allure.Allure.step;

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
        step("Open main page", () ->
                mainPage.openPage());

        step("Set game title into search input", () -> {
            mainPage.setGameTitleIntoSearchInput(game1Title)
                    .searchSuggestIsVisible();
        });

        step("Click 1st item from search suggest", () ->
                mainPage.clickTopItemFromSuggest());

        step("Page title contains given game title", () ->
                gamePage.checkPageTitleIs(game1Title));
    }

    @Test
    @AllureId("32700")
    @Story("Search game by name")
    @DisplayName("Select game from search suggest via keyboard")
    @Owner("alisitsky")
    public void selectFromSearchSuggestWithKeyboardTest() {
        step("Open main page", () ->
                mainPage.openPage());

        step("Set game title into search input", () -> {
            mainPage.setGameTitleIntoSearchInput(game1Title)
                    .searchSuggestIsVisible();
        });

        step("Choose 1st game from suggest with keyboard", () ->
                mainPage.chooseTopItemFromSuggestWithKeyboard());

        step("Page title contains given game title", () ->
                gamePage.checkPageTitleIs(game1Title));
    }
}
