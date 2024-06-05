package com.wasd.productmicroservice.service.seller;

import com.wasd.eshopcommon.exception.AlreadyExistsException;
import com.wasd.eshopcommon.exception.NotFoundException;
import com.wasd.productmicroservice.data.seller.SellerRequest;
import com.wasd.productmicroservice.data.seller.SellerResponse;
import com.wasd.productmicroservice.exception.persistence.SellerCreationException;
import com.wasd.productmicroservice.persistence.seller.Seller;
import com.wasd.productmicroservice.persistence.seller.SellerRepository;
import com.wasd.productmicroservice.util.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
	private final SellerRepository sellerRepository;

	@Override
	public SellerResponse findById(Long id) {
		return SellerMapper.INSTANCE.sellerToResponse(
			sellerRepository.findById(id).orElseThrow(() -> new NotFoundException(Seller.class)));
	}

	@Transactional
	@Override
	public SellerResponse create(SellerRequest request) {
		Integer tin = request.tin();
		if (tin < 0) {
			throw new SellerCreationException("Invalid TIN");
		} else if (sellerRepository.existsByTin(request.tin())) {
			throw new AlreadyExistsException(Seller.class);
		}

		Seller seller = SellerMapper.INSTANCE.requestToSeller(request);
		sellerRepository.save(seller);
		return SellerMapper.INSTANCE.sellerToResponse(seller);
	}
}