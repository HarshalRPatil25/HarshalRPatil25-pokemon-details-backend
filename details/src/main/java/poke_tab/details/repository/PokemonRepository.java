package poke_tab.details.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import poke_tab.details.models.Pokemon;

public interface PokemonRepository extends MongoRepository<Pokemon,ObjectId> {
    public Pokemon getPokemonByName(String name);
    public List<Pokemon> getPokemonByType(String type);
    public List<Pokemon> findByLevel(int level);
  

}
