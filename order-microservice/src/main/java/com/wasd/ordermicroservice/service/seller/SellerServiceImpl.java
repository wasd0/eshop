package com.wasd.ordermicroservice.service.seller;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    @Override
    public SellerResponse findById(Long id) {
        return null;
    }

    @Override
    public SellerResponse create(SellerRequest request) {
        return null;
    }
}
