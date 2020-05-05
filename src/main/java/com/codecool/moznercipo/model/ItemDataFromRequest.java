package com.codecool.moznercipo.model;

import lombok.Data;

@Data
public class ItemDataFromRequest {
    String name;
    String category;
    //boolean onSale;
    String price;
    String url;
}
