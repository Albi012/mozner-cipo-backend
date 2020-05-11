package com.codecool.moznercipo.repository;

import com.codecool.moznercipo.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoeRepository extends JpaRepository<Shoe,Long> {

    List<Shoe> getShoesByBrand(String brand);

    void deleteByBrandAndShoeNumber(String brand,String shoeNumber);

    Shoe getShoesByBrandAndShoeNumber(String brand,String shoeNumber);

    Shoe getShoeByShoeNumberAndBrand(String shoeNumber,String brand);

    List<Shoe> getShoesByOnSaleIsTrue();

    List<Shoe> getShoesByBrandAndCategory(String brand,String category);

    @Query("select distinct s.brand from Shoe s where s.category =:category ")
    List<String> getBrandsByCategory(@Param("category") String category);
}
