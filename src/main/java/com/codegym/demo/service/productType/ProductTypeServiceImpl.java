package com.codegym.demo.service.productType;

import com.codegym.demo.model.ProductType;
import com.codegym.demo.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements IProductTypeService{

    @Autowired
    private IProductTypeRepository productTypeRepository;

    @Override
    public Page<ProductType> findAll(Pageable pageable) {
        return productTypeRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductType> findById(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public Page<ProductType> findAllByNameContaining(String name, Pageable pageable) {
        return productTypeRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public ProductType save(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public void remove(Long id) {
        productTypeRepository.deleteById(id);
    }

    @Override
    public Iterable<ProductType> getAll() {
        return productTypeRepository.findAll();
    }
}
