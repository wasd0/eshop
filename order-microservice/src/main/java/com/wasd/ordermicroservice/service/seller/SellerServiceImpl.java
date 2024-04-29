package com.wasd.ordermicroservice.service.seller;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import com.wasd.ordermicroservice.exception.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.exception.SellerCreationException;
import com.wasd.ordermicroservice.persistence.seller.Seller;
import com.wasd.ordermicroservice.persistence.seller.SellerRepository;
import com.wasd.ordermicroservice.util.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    @Override
    public SellerResponse findById(Long id) throws NotFoundException {
        return SellerMapper.INSTANCE.sellerToResponse(
                sellerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Seller.class)));
    }

    @Transactional
    @Override
    public SellerResponse create(SellerRequest request) throws AlreadyExistsException {
        Integer tin = request.tin();
        if (tin == null || tin < 0) {
            throw new SellerCreationException("Invalid TIN");
        } else if (sellerRepository.existsByTin(request.tin())) {
            throw new AlreadyExistsException(Seller.class);
        }

        Seller seller = SellerMapper.INSTANCE.requestToSeller(request);
        sellerRepository.save(seller);
        return SellerMapper.INSTANCE.sellerToResponse(seller);
    }
}