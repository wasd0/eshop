package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.seller.SellerRequest;
import com.wasd.productmicroservice.data.seller.SellerResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.service.seller.SellerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sellers")
public class SellerController {
    private final SellerService sellerService;

    @GetMapping("{id}")
    public SellerResponse findById(@Min(0L) @PathVariable Long id) throws NotFoundException {
        return sellerService.findById(id);
    }

    @PostMapping
    public SellerResponse create(@Valid @RequestBody SellerRequest request) throws AlreadyExistsException {
        return sellerService.create(request);
    }
}
