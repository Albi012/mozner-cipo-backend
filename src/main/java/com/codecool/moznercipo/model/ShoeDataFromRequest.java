package com.codecool.moznercipo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ShoeDataFromRequest {
    String brand;
    String shoeNumber;
    String category;
    String price;
    String size;
    //Boolean onSale;
    String url;
}
