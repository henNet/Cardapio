package com.example.Cardapio.controller;

import com.example.Cardapio.food.Food;
import com.example.Cardapio.food.FoodRepository;
import com.example.Cardapio.food.FoodRequestDTO;
import com.example.Cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* Classe utilizada para receber as requisicoes HTTP (endpoints) */
@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @CrossOrigin()
    @PostMapping
    public String saveFood(@RequestBody FoodRequestDTO data){
        Food dataFood = new Food(data);
        repository.save(dataFood);
        return "OK";
    }

    @CrossOrigin()
    @GetMapping
    public List<FoodResponseDTO> getAll()
    {
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodList;
    }

    @CrossOrigin()
    @PutMapping(value = "/{id}")
    public FoodResponseDTO changeFood(@PathVariable Long id, @RequestBody FoodRequestDTO data)
    {
        Optional<Food> dataFood = repository.findById(id);

        if(dataFood.isPresent()) {
            dataFood.get().setTitle(data.title());
            dataFood.get().setPrice(data.price());
            dataFood.get().setImage(data.image());

            repository.save(dataFood.get());

            return new FoodResponseDTO(dataFood.get());
        }

        return null;
    }
}
