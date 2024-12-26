package poke_tab.details.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import poke_tab.details.models.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    public User findByUsername(String username);
}
