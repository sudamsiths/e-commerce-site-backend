package ecom.study.controller;


import ecom.study.model.dto.ProductDTO;
import ecom.study.model.entity.ProductEntity;
import ecom.study.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

   @GetMapping("/getall")
   public ResponseEntity<List<ProductDTO>> getAllProducts() {
       List<ProductDTO> products = productService.getAllProducts();
       return ResponseEntity.ok(products);
   }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(
        @Valid @RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok().build();
    }
}
