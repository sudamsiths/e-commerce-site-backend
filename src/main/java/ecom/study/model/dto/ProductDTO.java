package ecom.study.model.dto;

import ecom.study.model.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    @NotEmpty(message = "At least one image URL is required")
    private List<
            @NotBlank
            @Pattern(
                regexp = "^(http|https)://.*$",
                message = "Invalid image URL"
            )
            String
        > imageUrls;
    private Category category;
}
