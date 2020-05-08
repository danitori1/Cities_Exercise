package com.example.cities_exercise.Controller;

import com.example.cities_exercise.Document.Cities;
import com.example.cities_exercise.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
        if (field != null && (sort.equals("ascent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).ascending());
        }else if(field != null && (sort.equals("descent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).descending());
        }else{
            pageable = PageRequest.of(page, size);
        }

        return citiesRepository.queryByPage(pageable);
    }
}
