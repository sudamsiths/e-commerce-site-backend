package ecom.study.service.impl;

import ecom.study.model.dto.ProductDTO;
import ecom.study.model.entity.ProductEntity;
import ecom.study.repository.ProductRepository;
import ecom.study.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Value("${file.upload-dir:uploads/products}")
    private String uploadDir;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }

    @Override
    public void addProduct(ProductDTO productDTO, List<MultipartFile> images) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            List<String> imagePaths = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String originalFilename = image.getOriginalFilename();
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    String newFilename = UUID.randomUUID().toString() + fileExtension;

                    Path filePath = uploadPath.resolve(newFilename);
                    Files.copy(image.getInputStream(), filePath);

                    imagePaths.add("/uploads/products/" + newFilename);
                }
            }

            productDTO.setImageUrls(imagePaths);
            ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
            productRepository.save(productEntity);

        } catch (IOException e) {
            throw new RuntimeException("Failed to store images: " + e.getMessage());
        }
    }
}