package poke_tab.details.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Document(collection="rating")
@Data
public class Rating {

    @Id
    private ObjectId id;

    private ObjectId pokemonId;
    
    private String username;
 
    @NonNull
    private String message;
    @NonNull
    private int rating;

    
}
