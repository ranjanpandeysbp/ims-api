package com.obify.hy.ims.service.impl;

import com.obify.hy.ims.dto.CategoryDTO;
import com.obify.hy.ims.dto.ErrorDTO;
import com.obify.hy.ims.dto.ProductDTO;
import com.obify.hy.ims.entity.Category;
import com.obify.hy.ims.entity.Product;
import com.obify.hy.ims.exception.BusinessException;
import com.obify.hy.ims.repository.ProductRepository;
import com.obify.hy.ims.service.ImsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ImsService<ProductDTO, ProductDTO> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO add(ProductDTO input) {
        Product product = new Product();
        BeanUtils.copyProperties(input, product);
        product.setCreatedDateTime(LocalDateTime.now());
        product.setUpdatedDateTime(LocalDateTime.now());
        product = productRepository.save(product);
        BeanUtils.copyProperties(product, input);
        return input;
    }

    @Override
    public ProductDTO update(ProductDTO input, String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->{
                    List<ErrorDTO> errors =
                            List.of(new ErrorDTO("PROD_001", "Error occurred while saving new product"));
                    return new BusinessException(errors);
                });
        BeanUtils.copyProperties(input, product);
        product = productRepository.save(product);
        BeanUtils.copyProperties(product, input);
        return input;
    }

    @Override
    public String delete(String id) {
        productRepository.deleteById(id);
        return "Deleted successfully with Id: "+id;
    }

    @Override
    public ProductDTO get(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()->{
                    List<ErrorDTO> errors =
                            List.of(new ErrorDTO("PROD_003", "Error occurred while while finding the element"));
                    return new BusinessException(errors);
                });
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setProductId(product.getId());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        ProductDTO productDTO =  null;
        List<ProductDTO> dtos = new ArrayList<>();
        for(Product product: products){
            productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);;
            productDTO.setProductId(product.getId());
            dtos.add(productDTO);
        }
        return dtos;
    }

    @Override
    public List<ProductDTO> search(ProductDTO input) {
        List<Product> products = null;
        if(input.getName() != null){
            products = productRepository.findAllByNameContaining(input.getName());
        }else {
            products = new ArrayList<>();
        }
        ProductDTO productDTO =  null;
        List<ProductDTO> dtos = new ArrayList<>();
        for(Product product: products){
            productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);;
            productDTO.setProductId(product.getId());
            dtos.add(productDTO);
        }
        return dtos;
    }
}
