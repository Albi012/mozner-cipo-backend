package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.service.ShoeDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    private ShoeDataManager shoeDataManager;

    @GetMapping("/shoes")
    public List<Shoe> getAllShoes(){
        return shoeDataManager.getAllShoes();
    }

    @GetMapping("/shoes/{brand}")
    public List<Shoe> getShoesByBrand(@PathVariable("brand") String brand){return shoeDataManager.getShoesByBrand(brand);}

}
