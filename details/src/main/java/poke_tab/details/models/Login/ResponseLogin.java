package poke_tab.details.models.Login;

import lombok.Data;

@Data
public class ResponseLogin{
    private String username;
    private String password;
    private  String role;
}
