package com.codecool.moznercipo.controller;

import com.codecool.moznercipo.model.ItemDataFromRequest;
import com.codecool.moznercipo.model.OtherItem;
import com.codecool.moznercipo.service.OtherItemDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OtherItemRouteController {

    @Autowired
    OtherItemDataManager otherItemDataManager;

    @GetMapping("/other-items")
    public List<OtherItem> getAllOtherItem(){
        return otherItemDataManager.getAllOtherItem();
    }

    @PostMapping("/save-new-other-item")
    public OtherItem saveNewOtherItem(@RequestBody ItemDataFromRequest itemDataFromRequest){
        return otherItemDataManager.saveNewOtherItem(itemDataFromRequest);
    }

}
