package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
                increaseQuantityById(existingShoe.getId(), shoeDataFromRequest.getSize());
            } else {
                addSize(existingShoe.getId(), shoeDataFromRequest.getSize());
            }
            return existingShoe;
        }
        Shoe newShoe = Shoe.builder()
                .brand(shoeDataFromRequest.getBrand())
                .shoeNumber(shoeDataFromRequest.getShoeNumber())
                .category(shoeDataFromRequest.getCategory())
                .price(shoeDataFromRequest.getPrice())
                .size(Map.of(shoeDataFromRequest.getSize(), 1))
                .url(shoeDataFromRequest.getUrl())
                //.onSale(shoeDataFromRequest.getOnSale())
                .build();
        return shoeRepository.save(newShoe);
    }

    void addSize(Long id, String size) {
        Shoe shoe = shoeRepository.getOne(id);
        shoe.getSize().put(size, 1);
        shoeRepository.save(shoe);
    }

    public void increaseQuantity(String brand,String shoeNumber, String size) {
        Shoe shoe = shoeRepository.getShoeByShoeNumberAndBrand(brand,shoeNumber);
        if(shoe.getSize().containsKey(size)) {
            shoe.getSize().put(size, shoe.getSize().get(size) + 1);
        }
        else {
            shoe.getSize().put(size,1);
        }
        shoeRepository.save(shoe);
    }

    public void decreaseQuantity(String brand,String shoeNumber, String size) {
        Shoe shoe = shoeRepository.getShoeByShoeNumberAndBrand(brand,shoeNumber);
        shoe.getSize().put(size, shoe.getSize().get(size) - 1);
        if(shoe.getSize().get(size) == 0){
            shoe.getSize().remove(size);
        }
        shoeRepository.save(shoe);
    }

    @Transactional
    public void deleteShoe(String brand,String shoeNumber) {
        shoeRepository.deleteByBrandAndShoeNumber(brand,shoeNumber);
    }

    void increaseQuantityById(Long id,String size){
        Shoe shoe = shoeRepository.getOne(id);
        shoe.getSize().put(size, shoe.getSize().get(size) - 1);
        shoeRepository.save(shoe);
    }

    public List<Shoe> getShoesByBrandAndCategory(String brand, String category) {
        return shoeRepository.getShoesByBrandAndCategory(brand, category);
    }

    public List<String> getBrandsByCategory(String category) {
        return shoeRepository.getBrandsByCategory(category);
    }
}
