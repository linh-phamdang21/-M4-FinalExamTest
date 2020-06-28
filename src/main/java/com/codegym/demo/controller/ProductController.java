package com.codegym.demo.controller;

import com.codegym.demo.model.Product;
import com.codegym.demo.model.ProductType;
import com.codegym.demo.service.product.IProductService;
import com.codegym.demo.service.productType.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductTypeService typeService;

    @ModelAttribute("types")
    public Iterable<ProductType> types() {
        return typeService.getAll();
    }

    @GetMapping("/product/list")
    public ModelAndView listProduct(@RequestParam("s")Optional<String> s,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByProductNameContaining(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/productList");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/product/create")
    public ModelAndView showCreateForm(){
        Product product = new Product();
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product",product);
        modelAndView.addObject("types", types());
        return modelAndView;
    }

    @PostMapping("/product/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product){
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        Product newProduct = new Product(product.getName(), product.getPrice(),
                timestamp, product.getBrand(), product.getDescription(), product.getType());
        productService.save(newProduct);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("message", "Add new product successfully!");
        modelAndView.addObject("product", newProduct);
        return modelAndView;
    }

    @GetMapping("/product/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()){
            Product editProduct = product.get();
            modelAndView.addObject("product", editProduct);

        } else {
            modelAndView.addObject("product", new Product());
        }
        return modelAndView;
    }

    @PostMapping("/product/edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        if (product.getId() != null){
            modelAndView.addObject("message", "Edit product successfully!");
        } else {
            modelAndView.addObject("message", "Edit error");
        }
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        product.setCreateAt(timestamp);
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/product/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id){
        productService.remove(id);
        return new ModelAndView("redirect:/product/list");
    }

    @GetMapping("/productType/list")
    public ModelAndView showTypeForm(@RequestParam("s")Optional<String> s,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;
        if (s.isPresent()){
            products = productService.findAllByProductNameContaining(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        Iterable<ProductType> types = typeService.getAll();
        ProductType productType = new ProductType();
        ModelAndView modelAndView = new ModelAndView("productType/productTypeList");
        modelAndView.addObject("types", types);
        modelAndView.addObject("productType", productType);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/productType/list/search")
    public ModelAndView searchByType(@ModelAttribute("productType") ProductType productType,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products;
        String typeName = productType.getName();
        products = productService.findAllByType_Name(productType.getName(), pageable);
        ModelAndView modelAndView = new ModelAndView("productType/productTypeList");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
