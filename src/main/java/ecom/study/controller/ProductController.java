package ecom.study.controller;

import ecom.study.model.dto.ProductDTO;
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

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("category") String category,
            @RequestParam("images") List<MultipartFile> images) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setCategory(ecom.study.model.enums.Category.valueOf(category));

        productService.addProduct(productDTO, images);
        return ResponseEntity.ok().build();
    }
}