package com.sheryians.major.dto;


import com.sheryians.major.model.Category;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Category category_id;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
