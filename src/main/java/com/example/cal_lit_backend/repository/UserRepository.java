package com.example.cal_lit_backend.repository;

import com.example.cal_lit_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
     public boolean existsByEmail(
             String email
     );
    boolean existsByMobileNumber(String mobileNumber);

    Optional<User> findByEmail(String email);
}
