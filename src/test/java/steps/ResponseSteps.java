package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pojos.user.ErrorMessage;

public class ResponseSteps extends AbstractSteps {
    @Then("The responsecode should be {int}")
    public void theResponsecodeShouldBe(int code) {
          if (getResponse()==null){
              Assert.assertEquals(code,statusCode);
          }else{
              Assert.assertEquals(code, getResponse().getStatusCode());
          }
    }

    @And("The errormessage should be {string}")
    public void theErrorMessageShouldBe(String expectError) {
        if (!expectError.isEmpty()) {
            ErrorMessage errorMessage = getResponse().getBody().as(ErrorMessage.class);
            Assert.assertEquals(expectError, errorMessage.getError().trim());
        }
    }

}

