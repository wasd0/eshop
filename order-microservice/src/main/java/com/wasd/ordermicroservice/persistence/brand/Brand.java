package com.wasd.ordermicroservice.persistence.brand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private Integer id;
    private String title;
    private String description;
}
