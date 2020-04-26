package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.repository.ShoeRepository;
import com.codecool.moznercipo.service.ShoeDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    @PostMapping("/save-new-shoe")
    public Shoe saveNewShoe(@RequestBody ShoeDataFromRequest shoeDataFromRequest){
        return shoeDataManager.saveNewShoe(shoeDataFromRequest);
    };


}
