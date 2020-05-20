package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.ItemDataFromRequest;
import com.codecool.moznercipo.model.OtherItem;
import com.codecool.moznercipo.repository.OtherItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OtherItemDataManager {

    @Autowired
    OtherItemRepository otherItemRepository;

    public List<OtherItem> getAllOtherItem() {
        return otherItemRepository.findAll();
    }

    public OtherItem saveNewOtherItem(ItemDataFromRequest itemDataFromRequest) {
        OtherItem item = OtherItem.builder()
                .name(itemDataFromRequest.getName())
                .desc(itemDataFromRequest.getDesc())
                .price(itemDataFromRequest.getPrice())
                .url(itemDataFromRequest.getUrl())
                .build();
        return otherItemRepository.saveAndFlush(item);
    }
    @Transactional
    public void deleteItemByName(String name) {
        otherItemRepository.deleteByName(name);
    }
}
