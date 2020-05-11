package com.codecool.moznercipo.repository;

import com.codecool.moznercipo.model.OtherItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OtherItemRepository extends JpaRepository<OtherItem,Long> {

    void deleteByName(String name);

}
