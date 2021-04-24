package pojos.proxyApi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String access_token;

    private int expires_in;
}
