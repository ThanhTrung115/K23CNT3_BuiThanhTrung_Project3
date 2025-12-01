package com.treeree.shop.review;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewRepository repo;

    public ReviewController(ReviewRepository repo) { this.repo = repo; }

    @GetMapping("/{productId}")
    public String list(@PathVariable Long productId, Model model) {
        model.addAttribute("reviews", repo.findByProductIdOrderByCreatedAtDesc(productId));
        model.addAttribute("productId", productId);
        return "reviews";
    }

    @PostMapping("/{productId}")
    public String add(@PathVariable Long productId,
                      @RequestParam Integer rating,
                      @RequestParam String comment) {
        var rv = new ReviewEntity();
        rv.setProductId(productId);
        rv.setUserId(1L); // demo user
        rv.setRating(Math.max(1, Math.min(5, rating)));
        rv.setComment(comment);
        rv.setCreatedAt(java.time.Instant.now());
        repo.save(rv);
        return "redirect:/reviews/" + productId;
    }
}