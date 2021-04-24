package pojos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String role;

    private Boolean active;

    private String username;

    private Integer id;
}
