package com.steampowered.store.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.steampowered.store.data.TestData.game1PagePath;


public class GamePage {

    SelenideElement firstItemAddToCartButton =  $$("div.btn_addtocart").first();

    public GamePage openPage(){
        open(game1PagePath);
        return this;
    }


    public GamePage addFirstItemToCart(){
        firstItemAddToCartButton.click();
        return this;
    }


}
