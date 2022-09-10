package f102249.find_it_bff.payload.response;

public class UserInfoResponse {
    private String username;
    private String email;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    public UserInfoResponse(String username, String email, String firstName, String lastName, String dateOfBirth) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
