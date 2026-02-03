package ecom.study.service;

import ecom.study.model.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    void addProduct(ProductDTO productDTO);

}
