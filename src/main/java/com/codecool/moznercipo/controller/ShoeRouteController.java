package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.service.ShoeDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/shoe/delete/{brand}/{shoeNumber}")
    public void deleteShoe(@PathVariable("brand") String brand,@PathVariable("shoeNumber") String shoeNumber){
        shoeDataManager.deleteShoe(brand,shoeNumber);
    };

    @GetMapping("/shoe/increase/{brand}/{shoeNumber}/{size}")
    public void increaseShoeQuantity(@PathVariable("brand") String brand,@PathVariable("shoeNumber") String shoeNumber,@PathVariable("size") String size){
        shoeDataManager.increaseQuantity(brand,shoeNumber,size);
    };

    @GetMapping("/shoe/decrease/brand}/{shoeNumber}/{size}")
    public void decreaseShoeQuantity(@PathVariable("brand") String brand,@PathVariable("shoeNumber") String shoeNumber,@PathVariable("size") String size){
        shoeDataManager.decreaseQuantity(brand,shoeNumber,size);
    };

}
