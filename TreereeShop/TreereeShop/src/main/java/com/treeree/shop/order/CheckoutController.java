package com.treeree.shop.order;

import com.treeree.shop.cart.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderService orderService;

    public CheckoutController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public String page(HttpSession session, Model model) {
        var cart = (List<CartItem>) session.getAttribute("CART");
        if (cart == null || cart.isEmpty()) return "redirect:/cart";
        model.addAttribute("cart", cart);
        return "checkout";
    }

    @PostMapping
    public String place(HttpSession session) {
        var cart = (List<CartItem>) session.getAttribute("CART");
        if (cart == null || cart.isEmpty()) return "redirect:/cart";
        Long userId = 1L; // demo: sau sẽ lấy từ người dùng đăng nhập
        Long orderId = orderService.placeOrder(userId, cart);
        session.removeAttribute("CART");
        return "redirect:/orders/" + orderId;
    }
}