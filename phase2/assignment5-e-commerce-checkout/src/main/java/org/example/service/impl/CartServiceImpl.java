package org.example.service.impl;

import org.example.Validation.CartValidator;
import org.example.exception.NotFoundException;
import org.example.model.Cart;
import org.example.model.CartItem;
import org.example.model.Product;
import org.example.repository.CartRepository;
import org.example.service.CartService;
import org.example.service.ProductService;
import org.example.utility.ErrorMessages;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartValidator cartValidator;
    public CartServiceImpl(CartRepository cartRepository ,
                           ProductService productService,
                           CartValidator cartValidator){
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartValidator = cartValidator;
    }
    @Override
    public void addItem(Long customerId, Product product, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        cartValidator.validateQuantity(quantity);
        productService.checkInStock(product.getProductId(), quantity);
        addCartItem(cart,product,quantity);
        cartRepository.save(cart);
    }

    @Override
    public void removeItem(Long customerId, Long productId) {
        Cart cart = getCartByCustomerId(customerId);
        cartValidator.validateCartItemExists(cart, productId);
        cart.getCartItems().remove(productId);
        cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getItems(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cartValidator.validateCartNotEmpty(cart);
        return cart.getCartItems().values().stream().toList();
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
       return cartRepository.findByCustomerId(customerId)
                .orElseThrow(()->new NotFoundException(ErrorMessages.CART_NOT_FOUND));
    }

    @Override
    public BigDecimal calculateSubtotal(Cart cart) {
        cartValidator.validateCartNotEmpty(cart);
        return cart.getCartItems().values().stream()
                .map(item->item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Override
    public void clearCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.getCartItems().clear();
        cartRepository.save(cart);

    }

    private void addCartItem(Cart cart,Product product,int quantity){
        Map<Long, CartItem> items = cart.getCartItems();

        if (items.containsKey(product.getProductId())) {
            CartItem existing = items.get(product.getProductId());
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            items.put(product.getProductId(), new CartItem(product, quantity));
        }
    }
}
