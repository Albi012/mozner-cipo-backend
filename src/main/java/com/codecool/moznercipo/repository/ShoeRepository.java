package com.codecool.moznercipo.repository;

import com.codecool.moznercipo.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoeRepository extends JpaRepository<Shoe,Long> {

    List<Shoe> getShoesByBrand(String brand);

    Shoe getShoeById(Long id);

    Shoe getShoeByShoeNumberAndBrand(String shoeNumber,String brand);

}
