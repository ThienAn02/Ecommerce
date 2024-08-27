package com.sheryians.major.controller;

import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AdminController {
    public static String uploadDir=System.getProperty("user.dir")+"/scr/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("/admin")
    public String adminHome()
    {
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat()
    {
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model)
    {
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category)
    {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id)
    {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model)
    {
        Optional<Category> category= categoryService.getCategoryById(id);
        if(category.isPresent())
        {
            model.addAttribute("category", category.get());
            return "categoryAdd";
        }
        else
            return "404";

    }

    @GetMapping("/admin/products")
    public String products (Model model)
    {
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model)
    {
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",productService.getAllProduct());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("productDTO")ProductDTO productDTO,
                             @RequestParam("productImage")MultipartFile file,
                             @RequestParam("imgName")String imgName )throws IOException
    {
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategory_id().getId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());

        String imageUUID;
        if(!file.isEmpty())
        {
            imageUUID=file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }
        else
        {
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";

    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id)
    {
        productService.removeProductById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model)
    {
        Product product= productService.getProductById(id).get();
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory_id(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("productDTO",productDTO);
        return "productsAdd";
    }

}
