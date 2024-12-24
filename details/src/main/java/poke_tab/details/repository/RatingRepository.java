package poke_tab.details.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import poke_tab.details.models.Rating;

public interface RatingRepository extends MongoRepository<Rating,ObjectId> {
    
}
