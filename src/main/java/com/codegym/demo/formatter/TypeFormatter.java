package com.codegym.demo.formatter;

import com.codegym.demo.model.ProductType;
import com.codegym.demo.service.productType.IProductTypeService;
import com.codegym.demo.service.productType.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class TypeFormatter implements Formatter<ProductType> {
    @Autowired
    private IProductTypeService typeService;

    @Autowired
    private TypeFormatter(ProductTypeServiceImpl typeService){
        this.typeService = typeService;
    }

    @Override
    public String print(ProductType object, Locale locale) {
        return null;
    }

    @Override
    public ProductType parse(String text, Locale locale) throws ParseException {
        return typeService.findById(Long.parseLong(text)).get();
    }
}
