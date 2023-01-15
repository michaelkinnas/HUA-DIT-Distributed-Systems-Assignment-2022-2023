package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.request;


import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getEmail() {
        return  email;
    }

    public void setEmail() {
        this.email = email;
    }
}
