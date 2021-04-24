package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.PropertyHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pojos.user.AllUsersList;
import pojos.user.PostSingleUser;
import pojos.user.User;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class Usersteps extends AbstractSteps {
    private String baseurlUserApi = PropertyHelper.getProperties("BASE_URL_USER_API");
    private String baseUrlProxyApi = PropertyHelper.getProperties("BASE_URL_PROXY_API");

    @Given("The user {string} doesnt exist")
    public void theUserDoesntExist(String username) throws Exception {
        setResponse(getRequest("/Users"));
        AllUsersList allUsersList = getResponse().getBody().as(AllUsersList.class);
        User user = null;
        try{
            user =  allUsersList.getAllUsers().stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
        }catch (NoSuchElementException exception){
            //The user doenst exist
        }
        if (user !=null){
            //TODO Uncomment when delete request is implemented!
//            setResponse(deleteRequest("/Users/"+allUsers.getId()+"",false));
//            Assert.assertTrue("User not deleted",getResponse().getStatusCode()==200);
            throw new Exception("The user allready exists.Did you start with a empty db?");
        }
    }

    @When("I create and post a user payload with")
    public void iCreateAndPostAUserPayloadWith(DataTable dataTable) {
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        List<PostSingleUser> users = values.stream().map(i -> objectMapper.convertValue(i, PostSingleUser.class)).collect(Collectors.toList());
        for (PostSingleUser user: users){
        setResponse(postRequest(user,"/Users"));

        }
    }

    @When("I create a get request to get user by username")
    public void iCreateAGetRequestToGetUserByUsername(DataTable dataTable) {
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        User user = values.stream().map(i -> objectMapper.convertValue(i, User.class)).collect(Collectors.toList()).get(0);
        setResponse(getRequest("/users/"+user.getUsername()));
    }

    @And("The userResponse contains if responsecode is 200")
    public void theUserResponseContainsIfResponsecodeIs(DataTable dataTable) {
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        User expectedUser = values.stream().map(i -> objectMapper.convertValue(i, User.class)).collect(Collectors.toList()).get(0);
        if (getResponse().getStatusCode()==200){
            User user = getResponse().getBody().as(User.class);
            assertThat("expected user is not the same as user from response",user, samePropertyValuesAs(expectedUser));
        }else {
            System.out.println("Step: The userResponse contains if responsecode is 200 skipped, because of responsecode = "+getResponse().getStatusCode()+"");
        }

    }

}
