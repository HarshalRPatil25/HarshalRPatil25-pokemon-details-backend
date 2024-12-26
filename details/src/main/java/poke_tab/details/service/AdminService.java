package poke_tab.details.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poke_tab.details.models.Pokemon;

import poke_tab.details.repository.PokemonRepository;


@Service
public class AdminService {


    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon RegisterPokemon(Pokemon newPokemon){
        Pokemon registeredPokemon=null;
        if(newPokemon!=null){
            registeredPokemon=pokemonRepository.save(newPokemon);
        }
        return registeredPokemon;
    }
    
}
