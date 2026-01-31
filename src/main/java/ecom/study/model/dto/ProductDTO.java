package ecom.study.model.dto;

import ecom.study.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String ImageUrl;
    private Category category;
}
