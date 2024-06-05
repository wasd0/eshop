package com.wasd.productmicroservice.service.brand;

import com.wasd.eshopcommon.exception.NotFoundException;
import com.wasd.productmicroservice.data.brand.BrandRequest;
import com.wasd.productmicroservice.data.brand.BrandResponse;
import com.wasd.productmicroservice.persistence.brand.Brand;
import com.wasd.productmicroservice.persistence.brand.BrandRepository;
import com.wasd.productmicroservice.util.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    @Override
    public BrandResponse create(BrandRequest request) {
        Brand brand = BrandMapper.INSTANCE.requestToBrand(request);
        brandRepository.save(brand);
        return BrandMapper.INSTANCE.brandToResponse(brand);
    }

    @Override
    public BrandResponse findById(Integer id) {
        return BrandMapper.INSTANCE.brandToResponse(
                brandRepository.findById(id).orElseThrow(()
                        -> new NotFoundException(Brand.class)));
    }
}
