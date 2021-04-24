package pojos.proxyApi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {
    private String username;
    private String password;
    private int id;

    /**
     * An enum of the different credentials options.
     */
    public enum CredentialsEnum {
        ADMIN_CREDENTIALS("testadmin", "testadmin", "admin",1),
        TESTUSER_CREDENTIALS("testuser", "testuser", "user",2),
        INVALID_CREDENTIALS("invalidusername","invaliduser","invalidpass",1000),
        VALID_USERNAME_WRONG_PASSWORD("wrongpassword","testuser","invalidpass",2),
        VALID_USER_WRONG_ID("wrongid","testuser","testuser",1);

        private String credentialsName;
        private String username;
        private String password;
        private int id;

        CredentialsEnum(String credentialsName, String username, String password,int id) {
            this.credentialsName = credentialsName;
            this.username = username;
            this.password = password;
            this.id =id;
        }


        public String getCredentialsName() {
            return credentialsName;
        }
    }

    /**
     * Get the credentials by name for tests.
     * @param credentialName - The name of the credentials to get.
     * @return a Credentials object.
     */
    public static Credentials getCredentials(String credentialName){
        for(CredentialsEnum credentialsEnum: CredentialsEnum.values()){
            if(credentialName.equals(credentialsEnum.getCredentialsName())){
                return new Credentials(credentialsEnum.username, credentialsEnum.password,credentialsEnum.id);
            }
        }
        return null;
    }
}
