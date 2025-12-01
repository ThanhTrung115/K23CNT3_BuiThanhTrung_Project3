package com.treeree.shop.catalog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class ProductPageController {
    private final ProductService service;

    public ProductPageController(ProductService service) { this.service = service; }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue="0") int page,
                       @RequestParam(defaultValue="12") int size,
                       Model model) {
        var result = service.list(page, size);
        model.addAttribute("page", result);
        return "index";
    }

    @GetMapping("/products/{slug}")
    public String detail(@PathVariable String slug, Model model) {
        var p = service.getBySlug(slug);
        if (p == null) return "redirect:/";
        model.addAttribute("p", p);
        return "product-detail";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q,
                         @RequestParam(defaultValue="0") int page,
                         @RequestParam(defaultValue="12") int size,
                         Model model) {
        var result = service.search(q, page, size);
        model.addAttribute("page", result);
        model.addAttribute("q", q);
        return "search";
    }
}