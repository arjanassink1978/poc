package pojos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String role;

    private Boolean active;

    private String userName;

    private Integer id;
}
