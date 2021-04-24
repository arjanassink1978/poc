package pojos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    @JsonProperty("Error")
    private String error;
}
