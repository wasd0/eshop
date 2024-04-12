package com.wasd.ordermicroservice.persistence.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private Long id;
    private String title;
    private String description;
    private Integer tin;
}
