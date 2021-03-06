package com.codecool.moznercipo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shoe {

    @Id
    @GeneratedValue
    Long id;
    String brand;
    String category;
    String shoeNumber;
    String price;
    boolean onSale;
    @ElementCollection
    List<String> size;
    String url;


}
