package com.sheryians.major.controller;


import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String home(Model model)
    {
        model.addAttribute("cart", GlobalData.cart.size());
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model)
    {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product",productService.getAllProduct());
        model.addAttribute("cart", GlobalData.cart.size());

        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id)
    {
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("products",productService.getAllByCategoryId(id));
        model.addAttribute("cart", GlobalData.cart.size());
        return "shop";

    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct (Model model, @PathVariable long id)
    {
        model.addAttribute("products",productService.getProductById(id));
        model.addAttribute("cart", GlobalData.cart.size());
        return "viewProduct";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index)
    {
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model)
    {
        model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice));
        return "checkout";

    }


}
