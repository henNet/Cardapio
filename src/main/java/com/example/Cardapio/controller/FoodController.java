package com.example.Cardapio.controller;

import com.example.Cardapio.food.Food;
import com.example.Cardapio.food.FoodRepository;
import com.example.Cardapio.food.FoodRequestDTO;
import com.example.Cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Classe utilizada para receber as requisicoes HTTP (endpoints) */
@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @PostMapping
    public String saveFood(@RequestBody FoodRequestDTO data){
        Food dataFood = new Food(data);
        repository.save(dataFood);
        return "OK";
    }

    @GetMapping
    public List<FoodResponseDTO> getAll()
    {
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return foodList;
    }
}
