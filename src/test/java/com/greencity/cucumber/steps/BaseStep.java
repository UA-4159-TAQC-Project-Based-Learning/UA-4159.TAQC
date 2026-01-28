package com.greencity.cucumber.steps;

import com.greencity.utils.TestValueProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class BaseStep {

    private Hooks hooks;

    public BaseStep(Hooks hooks) {
        this.hooks = hooks;
    }



}