package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShoeDataManager {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public List<Shoe> getShoesByBrand(String brand) {
        return shoeRepository.getShoesByBrand(brand);
    }

    private boolean checkIfShoeExist(ShoeDataFromRequest shoeDataFromRequest) {
        Shoe shoe = shoeRepository.getShoeByShoeNumberAndBrand(
                shoeDataFromRequest.getShoeNumber(), shoeDataFromRequest.getBrand());
        return shoe != null;
    }


    public Shoe saveNewShoe(ShoeDataFromRequest shoeDataFromRequest) {
        if (checkIfShoeExist(shoeDataFromRequest)) {
            Shoe existingShoe = shoeRepository.getShoeByShoeNumberAndBrand(
                    shoeDataFromRequest.getShoeNumber(), shoeDataFromRequest.getBrand());
            if (existingShoe.getSize().containsKey(shoeDataFromRequest.getSize())) {
                increaseQuantity(existingShoe.getId(), shoeDataFromRequest.getSize());
            }
            return existingShoe;
        }
        Shoe newShoe = Shoe.builder()
                .brand(shoeDataFromRequest.getBrand())
                .category(shoeDataFromRequest.getCategory())
                .price(shoeDataFromRequest.getPrice())
                .size(Map.of(shoeDataFromRequest.getSize(), 1))
                .url(shoeDataFromRequest.getUrl())
                .onSale(shoeDataFromRequest.getOnSale())
                .build();
        return shoeRepository.save(newShoe);
    }


    public void increaseQuantity(Long id, String size) {
        Shoe shoe = shoeRepository.getOne(id);
        shoe.getSize().put(size, shoe.getSize().get(size) + 1);
        shoeRepository.save(shoe);
    }

    public void decreaseQuantity(Long id, String size) {
        Shoe shoe = shoeRepository.getOne(id);
        shoe.getSize().put(size, shoe.getSize().get(size) - 1);
        shoeRepository.save(shoe);
    }

    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }
}
