package steps;

import helper.PropertyHelper;
import io.cucumber.java.en.When;

public class TokenSteps extends AbstractSteps {


    @When("I request a token")
    public void iRequestAToken() {
         getToken();

    }
}
