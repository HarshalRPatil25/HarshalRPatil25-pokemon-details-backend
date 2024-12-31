package poke_tab.details.models;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Document(collection = "pokemon")
@Data
public class Pokemon {

    @Id
    private ObjectId id;              // Unique identifier for each Pokémon
    private String name;             // Name of the Pokémon
    private String type;             // Primary type (e.g., Fire, Water)
    private String secondaryType;    // Secondary type (optional, e.g., Flying)
    private int level;               // Level of the Pokémon
    private int baseAttack;          // Base attack stat
    private int baseDefense;         // Base defense stat
    private int baseStamina;         // Base stamina (HP)
    private String ability;          // Primary ability of the Pokémon
    private String hiddenAbility;    // Hidden ability (if any)
    private int evolutionStage;      // Stage in the evolution chain (e.g., 1 for base form, 2 for evolved form)
    private List<String> moves;      // List of moves the Pokémon can learn
    private boolean legendary;       // Whether the Pokémon is legendary or not
    private String imageUrl;  
 // URL for the Pokémon's image

    private int purchasePrice;
    
    @CreatedDate
    private Date addedDate;  

   private double avgRating;
   
    @DBRef
    private List<Rating>rating;
    
    // Getters and Setters
}
