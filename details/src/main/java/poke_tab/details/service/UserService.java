package poke_tab.details.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poke_tab.details.models.Pokemon;
import poke_tab.details.models.Rating;
import poke_tab.details.repository.PokemonRepository;
import poke_tab.details.repository.RatingRepository;
@Service
public class UserService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private  RatingRepository ratingRepository;

    public Rating addRating(String username, String name, Rating rating) {
        // Fetch the Pokémon by name
        Pokemon pokemon = pokemonRepository.getPokemonByName(name);
         boolean result=alreadyRated(username, name);
         if(result==true){
            return null;
         }
        if (pokemon != null) {
            try {
                // Save the rating to MongoDB
                rating.setPokemonId(pokemon.getId()); // Associate Pokémon ID with rating
                ratingRepository.save(rating);

                // Update Pokémon's rating list
                List<Rating> ratingList = pokemon.getRating();
                if (ratingList == null) {
                    ratingList = new ArrayList<>();
                }
                ratingList.add(rating);
                pokemon.setRating(ratingList);

                // Calculate and update the average rating
                double avgRating = ratingList.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
                pokemon.setAvgRating(avgRating);

                // Save updated Pokémon back to the repository
                pokemonRepository.save(pokemon);

                return rating; // Return the newly added rating
            } catch (Exception e) {
                e.printStackTrace(); // Log for debugging
                return null;
            }
        }
        return null; // Pokémon not found
    }

    public boolean alreadyRated(String username, String name) {
        // Fetch the Pokémon by name
        Pokemon pokemon = pokemonRepository.getPokemonByName(name);

        if (pokemon != null) {
            // Check if the user has already rated this Pokémon
            List<Rating> ratingList = pokemon.getRating();
            if (ratingList != null) {
                return ratingList.stream().anyMatch(rating -> rating.getUsername().equals(username));
            }
        }
        return false; // Pokémon not found or user has not rated
    }
}
