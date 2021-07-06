package com.citt.wellmart.controller;

import com.citt.wellmart.controller.mappers.ProductMapper;
import com.citt.wellmart.controller.models.ProductDto;
import com.citt.wellmart.entities.*;
import com.citt.wellmart.entities.security.User;
import com.citt.wellmart.repositories.*;
import com.citt.wellmart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
   @Autowired
    private CategoryRepository categoryRepository ;
    @Autowired
    private UserRepository merchantRepository;
    @Autowired
    ImageRepository imageRepository;
    @PostMapping
    public Product saveProduct(@RequestBody ProductDto dto) throws Exception {
        if(productRepository.findByName((dto.getName()))!=null){
            throw new Exception("Product already Exist !");
        }
       Category category = categoryRepository.findById(Long.valueOf(dto.getCategoryId())).get();
       Product product = productMapper.toProduct(dto);
       product.setCategory(category);

        return productService.saveProduct(product);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product)
    { return productService.updateProduct(product);}
    @GetMapping
    public List<Product> getAllProduct(){
        List<Product>  products =    productService.getAllProduct();
          products.stream().forEach(e->{
            final Optional<ImageModel> retrievedImage = imageRepository.findById(Long.valueOf(e.getImageUrl()));
            if(retrievedImage.isPresent()){
                ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                        decompressBytes(retrievedImage.get().getPicByte()));
                e.setImageUrl("data:image/jpeg;base64 ");
                e.setImageModel(img);
            }

        });

        return products;
    }
    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable(name = "id") Long id){ productService.deleteProductById(id); }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    @GetMapping("/categories/{id}")
    public List<Product> getProductByCategoryId(@PathVariable(name = "id") Long id){
        List<Product> products = productRepository.findByCategoryId(id);
        products.stream().forEach(e->{
            final Optional<ImageModel> retrievedImage = imageRepository.findById(Long.valueOf(e.getImageUrl()));
            if(retrievedImage.isPresent()){
                ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                        decompressBytes(retrievedImage.get().getPicByte()));
                e.setImageUrl("data:image/jpeg;base64 ");
                e.setImageModel(img);
            }

        });

        return    products;

    }
}
