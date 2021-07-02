package eu.ensup.myresto.mapper;

import eu.ensup.myresto.business.Product;
import eu.ensup.myresto.dto.ProductDTO;

/**
 * The type PRODUCT mapper.
 */
public class ProductMapper {

    public static ProductDTO businessToDto(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setAllergen(product.getAllergen());
        productDTO.setImage(product.getImage());
        productDTO.setStock(product.getStock());
        productDTO.setCategory(product.getCategory());
        return productDTO;
    };

    /**
     * Dto to business director.
     *
     * @param productDTO the director dto
     * @return the director
     */
    public static Product dtoToBusiness(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice() );
        product.setAllergen(productDTO.getAllergen());
        product.setImage(productDTO.getImage());
        product.setStock(productDTO.getStock());
        product.setCategory(productDTO.getCategory());
        return product;
    };
}
