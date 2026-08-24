package com.example.springlab.service;
import com.example.springlab.dto.OrderItemRequest;
import com.example.springlab.dto.OrderRequest;
import com.example.springlab.dto.OrderResponse;
import com.example.springlab.dto.PageResponse;
import com.example.springlab.entity.Customer;
import com.example.springlab.entity.Order;
import com.example.springlab.entity.OrderItem;
import com.example.springlab.entity.Product;
import com.example.springlab.enums.Status;
import com.example.springlab.mapper.OrderMapper;
import com.example.springlab.mapper.PageMapper;
import com.example.springlab.exception.ResourceNotFoundException;
import com.example.springlab.repository.CustomerRepository;
import com.example.springlab.repository.OrderRepository;
import com.example.springlab.repository.ProductRepository;
import com.example.springlab.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final PageMapper pageMapper;


    @Transactional
    public OrderResponse createOrder(OrderRequest request){
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(()->new ResourceNotFoundException(
                       String.format(ErrorMessages.CUSTOMER_NOT_FOUND,request.customerId())));
        Order order = new Order();
        order.setName(request.name());
        order.setCustomer(customer);
        order.setStatus(Status.PENDING);
        attachOrderItem(order,request);
        BigDecimal totalPrice = calculateTotalPrice(order);
        order.setTotalPrice(totalPrice);
        customerService.deductBalance(customer,totalPrice);
        orderRepository.save(order);

        return orderMapper.toResponse(order);
    }

    public OrderResponse findById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.ORDER_NOT_FOUND,id)));
        return orderMapper.toResponse(order);
    }

    public PageResponse<OrderResponse>findByCustomerId(Long id, Pageable pageable){
       Customer customer = customerRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException(
                       String.format(ErrorMessages.CUSTOMER_NOT_FOUND,id)));
        Page<Order> orderPage = orderRepository.findByCustomerId(id,pageable);
       return pageMapper.toPageResponse(orderPage,orderMapper::toResponse);
    }

    public PageResponse<OrderResponse>findAll(Pageable pageable){
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return pageMapper.toPageResponse(orderPage,orderMapper::toResponse);
    }

    public void deleteById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format(ErrorMessages.ORDER_NOT_FOUND,id)));
        orderRepository.delete(order);
    }

    private void attachOrderItem(Order order,OrderRequest request) {
        for (OrderItemRequest itemRequest : request.orderItems()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(()->new ResourceNotFoundException(
                            String.format(ErrorMessages.PRODUCT_NOT_FOUND,itemRequest.productId())));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);

            productService.reduceStock(product, itemRequest.quantity());
        }
    }

    private BigDecimal calculateTotalPrice(Order order) {

        return order.getOrderItems()
                .stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
