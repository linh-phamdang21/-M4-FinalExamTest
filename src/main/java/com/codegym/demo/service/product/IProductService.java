package com.codegym.demo.service.product;

import com.codegym.demo.model.Product;
import com.codegym.demo.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> getAll();

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long id);

    Page<Product> findAllByProductNameContaining(String name, Pageable pageable);

    Product save (Product product);

    void remove(Long id);

    Page<Product> findAllByType_Name(String name, Pageable pageable);
}
