package com.codecool.moznercipo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    String name;
    String price;
    String quantitiy;

}
