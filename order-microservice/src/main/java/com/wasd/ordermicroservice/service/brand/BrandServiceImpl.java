package com.wasd.ordermicroservice.service.brand;

import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.persistence.brand.Brand;
import com.wasd.ordermicroservice.persistence.brand.BrandRepository;
import com.wasd.ordermicroservice.util.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    @Override
    public BrandResponse create(String title, String description) {
        Brand brand = new Brand();
        brand.setTitle(title);
        brand.setDescription(description);
        brandRepository.save(brand);
        return BrandMapper.INSTANCE.brandToResponse(brand);
    }

    @Override
    public BrandResponse findById(Integer id) throws NotFoundException {
        return BrandMapper.INSTANCE.brandToResponse(
                brandRepository.findById(id).orElseThrow(()
                        -> new NotFoundException(Brand.class)));
    }
}
