package com.greencity.api.testRunner;

import com.greencity.api.clients.OwnSecurity;
import com.greencity.api.models.ownsecurity.SignInResponse;
import com.greencity.utils.TestValueProvider;
import org.testng.annotations.BeforeClass;

public class ApiTestRunnerWithUser extends ApiTestRunner {

    protected SignInResponse signInResponse;

    @BeforeClass
    public void setUpClass() {
        if (testValueProvider == null) {
            testValueProvider = new TestValueProvider();
        }

        OwnSecurity ownSecurity = new OwnSecurity(testValueProvider.getBaseAPIUserUrl());
        signInResponse = ownSecurity.signIn(
                testValueProvider.getUserEmail(),
                testValueProvider.getUserPassword()
                ).as(SignInResponse.class);
    }

}
