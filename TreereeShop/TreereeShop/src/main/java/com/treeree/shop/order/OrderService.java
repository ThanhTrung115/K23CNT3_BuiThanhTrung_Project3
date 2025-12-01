package com.treeree.shop.order;

import com.treeree.shop.cart.CartItem;
import com.treeree.shop.catalog.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final ProductRepository productRepo;

    public OrderService(OrderRepository orderRepo, OrderItemRepository itemRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo; this.itemRepo = itemRepo; this.productRepo = productRepo;
    }

    @Transactional
    public Long placeOrder(Long userId, List<CartItem> cart) {
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("Cart empty");
        BigDecimal total = cart.stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var order = new OrderEntity();
        order.setUserId(userId);
        order.setStatus("PENDING");
        order.setTotal(total);
        order.setPaymentMethod("COD");
        order.setPaymentStatus("UNPAID");
        order = orderRepo.save(order);

        for (var ci : cart) {
            var item = new OrderItemEntity();
            item.setOrderId(order.getId());
            item.setProductId(ci.getProductId());
            item.setUnitPrice(ci.getPrice());
            item.setQuantity(ci.getQuantity());
            itemRepo.save(item);

            // Giảm tồn kho
            var p = productRepo.findById(ci.getProductId()).orElseThrow();
            p.setQuantity(Math.max(0, p.getQuantity() - ci.getQuantity()));
            productRepo.save(p);
        }
        return order.getId();
    }
}