package poke_tab.details.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import poke_tab.details.models.Pokemon;
import poke_tab.details.models.User;
import poke_tab.details.models.Login.RequestLogin;
import poke_tab.details.models.Login.ResponseLogin;
import poke_tab.details.service.PokemonService;
import poke_tab.details.service.PublicService;

@RestController
@RequestMapping("/api/public")
public class PublicController {



    @Autowired 
    private PublicService publicService;

    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser){
        try{
           User registeredUser=publicService.registerUser(newUser);
           if(registeredUser!=null){
            return ResponseEntity.ok().body("User registered successfully");
           }
           else{
               return ResponseEntity.badRequest().body("Failed to register user");
           }
            

        }
        catch(Exception e){
            return ResponseEntity.status(404).body("error");

        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLogin requestLogin){
        try{
            ResponseLogin responseLogin=publicService.login(requestLogin);
            if(responseLogin!=null){
                return ResponseEntity.ok().body(responseLogin);
            }
            else{
                return ResponseEntity.status(404).body("Invalid Credentials:");
            }


        }
        catch(Exception e){
            return ResponseEntity.status(501).body("Error");
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllPokemons(){
        try{
            List<Pokemon> getAllPokemons=pokemonService.getAllPokemons();
            if(getAllPokemons != null && !getAllPokemons.isEmpty()) {
            return ResponseEntity.ok().body(getAllPokemons);
        } else {
            return ResponseEntity.status(404).body("No Pokemons found");
        }
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("error in fetching pokemons");
        }
    }

}
