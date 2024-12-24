package poke_tab.details.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poke_tab.details.models.Pokemon;
import poke_tab.details.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add-pokemon")
    public ResponseEntity<?> registeredPokemon(@RequestBody Pokemon newPokemon){
        try{
            Pokemon registeredPokemon=adminService.RegisterPokemon(newPokemon);
            if(registeredPokemon!=null){
                return ResponseEntity.ok().body("Pokemon registered successfully");
            }
            else{
                return ResponseEntity.badRequest().body("Failed to register Pokemon");
            }
            
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error occured while registering pokemon");
        }
    }

    
}
