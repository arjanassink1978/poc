package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.PropertyHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSenderOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.checkerframework.checker.units.qual.A;
import org.json.simple.JSONObject;
import pojos.proxyApi.Credentials;
import pojos.proxyApi.TokenResponse;

import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * The abstract step class with methods that are used in every step class.
 */

public class AbstractSteps {
    private static volatile AbstractSteps mInstance;
    private static Response response;
    private static Credentials credentials;
    protected String token = null;
    public static int statusCode=0;
    private static String BaseURI = null;
    protected ObjectMapper objectMapper = new ObjectMapper();
    private static boolean useAuthentication;

    public static AbstractSteps getInstance(){
        if (mInstance == null) {
            synchronized (AbstractSteps.class) {
                if (mInstance == null) {
                    mInstance = new AbstractSteps();
                }
            }
        }
        return mInstance;
    }
    /**
     * Set the base URI for the api.
     *
     * @return a string with the base uri
     */
    public static void setBaseURI(String baseURI) {
        BaseURI = baseURI;
    }

    /**
     * Get the base URI for the api.
     *
     * @return a string with the base uri
     */
    public String getBaseURI() {
        return BaseURI;
    }
    /**
     * Set authentication for request.
     */
    public void setUseAuthentication(boolean useAuthentication) {
        this.useAuthentication = useAuthentication;
    }


    /**
     * Get the response the api.
     *
     * @return response of the api
     */
    public static Response getResponse() {
        return response;
    }

    /**
     * Set the response the api.
     *
     * @return response of the api
     */
    public void setResponse(Response response) {
        this.response = response;
    }


    /**
     * Get token of proxy api
     * For now i use Apache HTTP to get the token
     * @return token
     * *TODO discuss why a 308 is returned because restassured cant handle redirecting with 308
     */
    @SneakyThrows
    public String getToken() {
        TokenResponse tokenResponse = null;
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(getBaseURI()+"/tokens/");
        JSONObject json = new JSONObject();
        json.put("username", credentials.getUsername());
        json.put("id", credentials.getId());
        json.put("password", credentials.getPassword());
        StringEntity se = new StringEntity(json.toString());
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String output = EntityUtils.toString(httpEntity);
        statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper();
            tokenResponse = mapper.readValue(output, TokenResponse.class);
            token = tokenResponse.getAccess_token();
            return token;
        }
    }

    /**
     * A generic post request to post something at the api.
     * @param url          - The url of the call to the api.
     * @return the response of the api.
     */
    public Response postRequest(Object object, String url) {
        RestAssured.baseURI = getBaseURI();
        RequestSpecification request = given();
        request.body(object);
        request.header("Content-Type", "application/json");
        if (useAuthentication) {
            request.auth().oauth2(getToken());
        }
        return request.post(url);
    }

    /**
     * A generic get request to get something form the api.
     * @param url          - The url of the call to the api.

     * @return the response of the api.
     */
    public Response getRequest(String url) {
        RestAssured.baseURI = getBaseURI();
        RequestSpecification request = given();
        request.header("Accept", "application/json");
        request.header("Content-Type", "application/json");
        if (useAuthentication) {
            request.auth().oauth2(getToken());
        }
        return request.get(url);

    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

}
