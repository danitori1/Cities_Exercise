package com.example.cities_exercise.Controller;

import com.example.cities_exercise.Document.Cities;
import com.example.cities_exercise.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(value = {})
public class CitiesController {
    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping(path="")
    public String welcomePage(){
        return ("HELLO");
    };

    @GetMapping(path="/")
    public Iterable<Cities> findAll(){
        return citiesRepository.findAll();
    };

    @GetMapping(path="/queryByPage")
    public Page<Cities> queryByPage(@RequestParam int page, int size, String field, String sort) {
        PageRequest pageable;

        //Check that all the params are correct in the request
        if (field != null && (sort.equals("ascent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).ascending());
        }else if(field != null && (sort.equals("descent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).descending());
        }else{
            pageable = PageRequest.of(page, size);
        }

        return citiesRepository.queryByPage(pageable);
    }

    @GetMapping(path="/biggestSequence")
    public List<Cities> biggestSequence(@RequestParam int N){
        List<Cities> allCities = citiesRepository.biggestSequence();

        int biggestIndex = 0;   // Index of greater sequence
        int biggestSum = 0;     // Value of greater sequence sum

        for (int i=0; i<allCities.size()-N; i++){
            int idSum = 0;  // Sum of next N ids
            for (int j=0; j<N; j++){
                int cityId = allCities.get(i + j).getId();
                idSum += cityId;
            }
            if (biggestSum < idSum){
                biggestSum = idSum;
                biggestIndex = i;
            }
        }

        // Get sublist of greater index plus N values
        return allCities.subList(biggestIndex, biggestIndex + N);
    }
}
