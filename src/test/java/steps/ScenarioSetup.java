package steps;

import helper.PropertyHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioSetup {

    @Before("@ProxyApi")
    public void setBaseUrlProxyApiWithAuthentication() {

        AbstractSteps.getInstance().setBaseURI(PropertyHelper.getProperties("BASE_URL_PROXY_API"));
        AbstractSteps.getInstance().setUseAuthentication(true);
    }

    @Before("@UserApi")
    public void setBaseUrlUserApi() {

        AbstractSteps.getInstance().setBaseURI(PropertyHelper.getProperties("BASE_URL_USER_API"));
        AbstractSteps.getInstance().setUseAuthentication(false);
    }

}
