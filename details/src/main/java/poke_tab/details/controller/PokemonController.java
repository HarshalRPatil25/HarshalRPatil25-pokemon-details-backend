package poke_tab.details.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poke_tab.details.models.Pokemon;
import poke_tab.details.repository.PokemonRepository;
import poke_tab.details.service.PokemonService;

import java.util.List;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonService;

    @Autowired
    private PokemonService pokeService;

    // Search Pokémon by Name
    @GetMapping("/search/name/{name}")
    public ResponseEntity<?> searchPokemonByName(@PathVariable String name) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByName(name);
            if (pokemon != null) {
                return ResponseEntity.ok().body(pokemon);
            } else {
                return ResponseEntity.badRequest().body("Pokemon not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while searching for the Pokémon.");
        }
    }

    @GetMapping("/view-Detail/{name}")
    public ResponseEntity<?> viewPokemonDetail(@PathVariable String name) {
        try {
            Pokemon pokemon = pokemonService.getPokemonByName(name);
            if (pokemon != null) {
                return ResponseEntity.ok().body(pokemon);
            } else {
                return ResponseEntity.badRequest().body("Pokemon not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while searching for the Pokémon.");
        }
    }

    // Search Pokémon by Type
    @GetMapping("/search/type/{type}")
    public ResponseEntity<?> searchPokemonByType(@PathVariable String type) {
        try {
            List<Pokemon> pokemonList = pokemonService.getPokemonByType(type);
            if (pokemonList != null && !pokemonList.isEmpty()) {
                return ResponseEntity.ok().body(pokemonList);
            } else {
                return ResponseEntity.badRequest().body("No Pokémon found for the given type.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while searching for the Pokémon.");
        }
    }

    // Search Pokémon by Level
    @GetMapping("/search/level/{level}")
    public ResponseEntity<?> searchPokemonByLevel(@PathVariable int level) {
        try {
            List<Pokemon> pokemonList = pokemonService.findByLevel(level);
            if (pokemonList != null && !pokemonList.isEmpty()) {
                return ResponseEntity.ok().body(pokemonList);
            } else {
                return ResponseEntity.badRequest().body("No Pokémon found for the given level.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while searching for the Pokémon.");
        }
    }

    // Search Legendary Pokémon
    
    @GetMapping("/search/legendary/{legendary}")
    public ResponseEntity<?> searchLegendaryPokemon(@PathVariable boolean legendary) {
        try {
            List<Pokemon> pokemonList = pokeService.getLegendaryPokemon(legendary);
            if (pokemonList != null && !pokemonList.isEmpty()) {
                return ResponseEntity.ok().body(pokemonList);
            } else {
                return ResponseEntity.badRequest().body("No legendary Pokémon found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while searching for the Pokémon.");
        }
    }

    // Combined Search with Multiple Parameters (Optional)
    // @GetMapping("/search")
    // public ResponseEntity<?> searchPokemon(
    //         @RequestParam(required = false) String name,
    //         @RequestParam(required = false) String type,
    //         @RequestParam(required = false) Integer level,
    //         @RequestParam(required = false) Boolean legendary
    // ) {
    //     try {
    //         List<Pokemon> result = pokemonService.searchPokemon(name, type, level, legendary);
    //         if (result != null && !result.isEmpty()) {
    //             return ResponseEntity.ok().body(result);
    //         } else {
    //             return ResponseEntity.badRequest().body("No Pokémon match the search criteria.");
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.internalServerError().body("Error occurred while searching for Pokémon.");
    //     }
    // }
}
