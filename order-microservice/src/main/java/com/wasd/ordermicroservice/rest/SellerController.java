package com.wasd.ordermicroservice.rest;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import com.wasd.ordermicroservice.exception.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.service.seller.SellerService;
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
