package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            Shoe existingShoe = shoeRepository.getShoeByShoeNumberAndBrand(shoeDataFromRequest.getShoeNumber(), shoeDataFromRequest.getBrand());
            if (!existingShoe.getSize().contains(shoeDataFromRequest.getSize())) {
                addShoeSizeById(existingShoe.getId(), shoeDataFromRequest.getSize());
            }
            return existingShoe;
        }
        Shoe newShoe = Shoe.builder()
                .brand(shoeDataFromRequest.getBrand())
                .shoeNumber(shoeDataFromRequest.getShoeNumber())
                .category(shoeDataFromRequest.getCategory())
                .price(shoeDataFromRequest.getPrice())
                .size(List.<String>of(shoeDataFromRequest.getSize()))
                .url(shoeDataFromRequest.getUrl())
                .onSale(shoeDataFromRequest.isOnSale())
                .build();
        return shoeRepository.save(newShoe);
    }

    public List<String> addShoeSize(String brand, String shoeNumber, String size) {
        Shoe shoe = shoeRepository.getShoeByShoeNumberAndBrand(shoeNumber, brand);
        if (!shoe.getSize().contains(size)) {
            shoe.getSize().add(size);
        }
        shoeRepository.save(shoe);
        return shoe.getSize();
    }

    public List<String> deleteShoeSize(String brand, String shoeNumber, String size) {
        Shoe shoe = shoeRepository.getShoeByShoeNumberAndBrand(shoeNumber, brand);
        shoe.getSize().remove(size);
        shoeRepository.save(shoe);
        return shoe.getSize();
    }

    @Transactional
    public void deleteShoe(String brand, String shoeNumber) {
        shoeRepository.deleteByBrandAndShoeNumber(brand, shoeNumber);
    }

    void addShoeSizeById(Long id, String size) {
        Shoe shoe = shoeRepository.getOne(id);
        shoe.getSize().add(size);
        shoeRepository.save(shoe);
    }

    public List<Shoe> getShoesByBrandAndCategory(String brand, String category) {
        return shoeRepository.getShoesByBrandAndCategory(brand, category);
    }

    public List<String> getBrandsByCategory(String category) {
        return shoeRepository.getBrandsByCategory(category);
    }

    public List<Shoe> getShoesOnSale() {
        return shoeRepository.getShoesByOnSaleIsTrue();
    }
}
