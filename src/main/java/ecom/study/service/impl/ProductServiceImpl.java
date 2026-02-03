package ecom.study.service.impl;


import ecom.study.model.dto.ProductDTO;
import ecom.study.model.entity.ProductEntity;
import ecom.study.repository.ProductRepository;
import ecom.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productDTO = productRepository.findAll();
        return productDTO.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO,ProductEntity.class);
          productRepository.save(productEntity);
    }

}
