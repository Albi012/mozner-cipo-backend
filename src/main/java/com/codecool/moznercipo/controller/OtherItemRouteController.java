package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.ItemDataFromRequest;
import com.codecool.moznercipo.model.OtherItem;
import com.codecool.moznercipo.service.OtherItemDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OtherItemRouteController {

    @Autowired
    OtherItemDataManager otherItemDataManager;

    @GetMapping("/items")
    public List<OtherItem> getAllOtherItem(){
        return otherItemDataManager.getAllOtherItem();
    }

    @GetMapping("/items/{category}")
    public List<OtherItem> getItemByCategory(@PathVariable("category") String category){
        return otherItemDataManager.getOtherItemByCategory(category);
    };

    @GetMapping("/items/delete/{name}")
    public void deleteItem(@PathVariable("name")String name){
        otherItemDataManager.deleteItemByName(name);
    }

    @PostMapping("/save-new-item")
    public OtherItem saveNewOtherItem(@RequestBody ItemDataFromRequest itemDataFromRequest){
        return otherItemDataManager.saveNewOtherItem(itemDataFromRequest);
    }

}
