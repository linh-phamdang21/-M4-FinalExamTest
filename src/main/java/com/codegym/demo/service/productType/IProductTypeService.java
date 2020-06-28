package com.codegym.demo.service.productType;

import com.codegym.demo.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IProductTypeService {

    Iterable<ProductType> getAll();

    Page<ProductType> findAll(Pageable pageable);

    Optional<ProductType> findById(Long id);

    Page<ProductType> findAllByNameContaining(String name, Pageable pageable);

    ProductType save(ProductType productType);

    void remove(Long id);
}
