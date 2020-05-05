package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.service.ShoeDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ShoeRouteController {

    @Autowired
    private ShoeDataManager shoeDataManager;

    @GetMapping("/shoes")
    public List<Shoe> getAllShoes(){
        return shoeDataManager.getAllShoes();
    }

    @GetMapping("shoes/{brand}/{category}")
    public List<Shoe> getShoesByBrandAndCategory(@PathVariable("brand") String brand, @PathVariable("category") String category){
        return shoeDataManager.getShoesByBrandAndCategory(brand,category);
    }

    @GetMapping("/brands/{category}")
    public List<String> getBrandsByCategory(@PathVariable("category") String category){
        return shoeDataManager.getBrandsByCategory(category);
    }

    @GetMapping("/shoes/{brand}")
    public List<Shoe> getShoesByBrand(@PathVariable("brand") String brand){return shoeDataManager.getShoesByBrand(brand);}

    @PostMapping("/save-new-shoe")
    public Shoe saveNewShoe(@RequestBody ShoeDataFromRequest shoeDataFromRequest){
        return shoeDataManager.saveNewShoe(shoeDataFromRequest);
    };

    @GetMapping("/shoe/delete/{id}")
    public void deleteShoe(@PathVariable("id") Long id){
        shoeDataManager.deleteShoe(id);
    };

    @GetMapping("/shoe/increase/{id}/{size}")
    public void increaseShoeQuantity(@PathVariable("id") Long id,@PathVariable("size") String size){
        shoeDataManager.increaseQuantity(id,size);
    };

    @GetMapping("/shoe/decrease/{id}/{size}")
    public void decreaseShoeQuantity(@PathVariable("id") Long id,@PathVariable("size") String size){
        shoeDataManager.decreaseQuantity(id,size);
    };

}
