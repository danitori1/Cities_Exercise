package com.example.cities_exercise.Repository;
import com.example.cities_exercise.Document.Cities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface CitiesRepository extends CrudRepository<Cities, Integer> {

    @Query(value = "SELECT * FROM cities",
            nativeQuery = true)
        public Page<Cities> queryByPage(PageRequest pageable);
}
