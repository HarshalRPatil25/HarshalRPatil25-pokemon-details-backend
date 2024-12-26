package poke_tab.details.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poke_tab.details.models.Pokemon;
import poke_tab.details.repository.PokemonRepository;

@Service
public class PokemonService {

    @Autowired 
    private PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemons(){
        return pokemonRepository.findAll();
    }

    public 
    Pokemon getPokemonById(ObjectId id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void deletePokemon(ObjectId id) {
        pokemonRepository.deleteById(id);
    }

    public List<Pokemon>getLegendaryPokemon(boolean legendary){
       List<Pokemon> listOfPokemons=pokemonRepository.findAll();
       List<Pokemon> legendaryPokemons=null;
       for(Pokemon pokemon:listOfPokemons){
             if(pokemon.isLegendary()==legendary){
                 legendaryPokemons.add(pokemon);
             } 
             return legendaryPokemons; 
         
       }
       return null;

    }

    
    
}
