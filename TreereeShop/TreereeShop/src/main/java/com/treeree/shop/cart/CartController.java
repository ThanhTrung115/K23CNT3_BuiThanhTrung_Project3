package com.treeree.shop.cart;

import com.treeree.shop.catalog.Product;
import com.treeree.shop.catalog.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductRepository productRepo;

    public CartController(ProductRepository productRepo) { this.productRepo = productRepo; }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        var cart = (List<CartItem>) session.getAttribute("CART");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("CART", cart);
        }
        return cart;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        var cart = getCart(session);
        BigDecimal total = cart.stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id,
                      @RequestParam(defaultValue="1") int quantity,
                      HttpSession session) {
        Product p = productRepo.findById(id).orElse(null);
        if (p == null) return "redirect:/";
        var cart = getCart(session);
        var existing = cart.stream().filter(i -> i.getProductId().equals(id)).findFirst();
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            cart.add(new CartItem(p.getId(), p.getName(), p.getSlug(), p.getPrice(), quantity));
        }
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam int quantity,
                         HttpSession session) {
        var cart = getCart(session);
        cart.stream().filter(i -> i.getProductId().equals(id)).findFirst()
                .ifPresent(i -> i.setQuantity(Math.max(1, quantity)));
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session) {
        var cart = getCart(session);
        cart.removeIf(i -> i.getProductId().equals(id));
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clear(HttpSession session) {
        session.removeAttribute("CART");
        return "redirect:/cart";
    }
}