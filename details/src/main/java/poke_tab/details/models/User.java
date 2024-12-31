package poke_tab.details.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="users")
@Data
public class User {

    @Id
    private ObjectId id;

    private String username;

    private String password;

    private String role;

    private int points;


    @DBRef
    private List<Pokemon> pokemonCollection;

    private boolean oncesLogin=false;
    
    
   

     

    
}
