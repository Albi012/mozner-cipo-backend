package com.codecool.moznercipo.service;

import com.codecool.moznercipo.model.Shoe;
import com.codecool.moznercipo.model.ShoeDataFromRequest;
import com.codecool.moznercipo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeDataManager {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public List<Shoe> getShoesByBrand(String brand){return shoeRepository.getShoesByBrand(brand);}

    private boolean checkIfShoeExist(ShoeDataFromRequest shoeDataFromRequest){
        Shoe shoe = shoeRepository.getShoeByBrandAndNameAndSize(
                shoeDataFromRequest.getBrand(),
                shoeDataFromRequest.getName(),
                shoeDataFromRequest.getSize());
        return shoe != null;
    }

    public Shoe saveNewShoe(ShoeDataFromRequest shoeDataFromRequest) {
        if(checkIfShoeExist(shoeDataFromRequest)){
            Shoe existingShoe = shoeRepository.getShoeByBrandAndNameAndSize(
                    shoeDataFromRequest.getBrand(),
                    shoeDataFromRequest.getName(),
                    shoeDataFromRequest.getSize());
            increaseQuantity(existingShoe.getId());
            return existingShoe;
        }
        Shoe newShoe = Shoe.builder()
                .brand(shoeDataFromRequest.getBrand())
                .name(shoeDataFromRequest.getName())
                .price(shoeDataFromRequest.getPrice())
                .size(shoeDataFromRequest.getSize())
                .url(shoeDataFromRequest.getUrl())
                .quantitiy(1)
                .build();
        return shoeRepository.save(newShoe);
    }

    public void increaseQuantity(Long id){
        Shoe shoe = shoeRepository.getOne(id);
        shoe.setQuantitiy(shoe.getQuantitiy()+1);
        shoeRepository.save(shoe);
    }

    public void decreaseQuantity(Long id){
        Shoe shoe = shoeRepository.getOne(id);
        shoe.setQuantitiy(shoe.getQuantitiy()-1);
        shoeRepository.save(shoe);
    }

}
