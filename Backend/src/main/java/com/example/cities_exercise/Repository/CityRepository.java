package com.example.cities_exercise.Repository;
import com.example.cities_exercise.Document.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query(value = "SELECT * FROM city",
            nativeQuery = true)
        public Page<City> queryByPage(PageRequest pageable);
}
