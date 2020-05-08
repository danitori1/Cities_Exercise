package com.example.cities_exercise.Controller;

import com.example.cities_exercise.Document.City;
import com.example.cities_exercise.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(value = {})
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping(path="")
    public String welcomePage(){
        return ("HELLO");
    };

    @GetMapping(path="/")
    public Iterable<City> findAll(){
        return cityRepository.findAll();
    };

    @GetMapping(path="/queryByPage")
    public Page<City> queryByPage(@RequestParam int page, int size, String field, String sort) {
        PageRequest pageable;
        if (field != null && (sort.equals("ascent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).ascending());
        }else if(field != null && (sort.equals("descent"))){
            pageable = PageRequest.of(page, size, Sort.by(field).descending());
        }else{
            pageable = PageRequest.of(page, size);
        }

        return cityRepository.queryByPage(pageable);
    }
}
