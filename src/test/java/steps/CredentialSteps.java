package steps;

import io.cucumber.java.en.Given;
import pojos.proxyApi.Credentials;

public class CredentialSteps extends AbstractSteps {
    @Given("credentials {string} are used")
    public void useCredentials(String credentialsName) {
        setCredentials(Credentials.getCredentials(credentialsName));
    }

    @Given("get token with credential {string}")
    public void getTokenWithCredential(String credentialsName) {
        useCredentials(credentialsName);
        getToken();
    }
}
