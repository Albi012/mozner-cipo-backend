package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.ItemDataFromRequest;
import com.codecool.moznercipo.model.OtherItem;
import com.codecool.moznercipo.repository.OtherItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherItemDataManager {

    @Autowired
    OtherItemRepository otherItemRepository;

    public List<OtherItem> getOtherItemByCategory(String category) {
        return otherItemRepository.getOtherItemsByCategory(category);
    }

    public List<OtherItem> getAllOtherItem() {
        return otherItemRepository.findAll();
    }

    // public List<OtherItem> getOnSaleOtherItems(){
    //   return otherItemRepository.getOtherItemsByOnSaleIsTrue();
    // }

    public OtherItem saveNewOtherItem(ItemDataFromRequest itemDataFromRequest) {
        OtherItem item = OtherItem.builder()
                .name(itemDataFromRequest.getName())
                .category(itemDataFromRequest.getCategory())
                //.onSale(itemDataFromRequest.isOnSale())
                .price(itemDataFromRequest.getPrice())
                .url(itemDataFromRequest.getUrl())
                .build();
        return otherItemRepository.saveAndFlush(item);
    }
}
