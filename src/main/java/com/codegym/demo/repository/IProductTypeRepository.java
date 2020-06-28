package com.codegym.demo.repository;

import com.codegym.demo.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductTypeRepository extends PagingAndSortingRepository<ProductType, Long> {
    Page<ProductType> findAllByNameContaining(String typeName, Pageable pageable);
}
