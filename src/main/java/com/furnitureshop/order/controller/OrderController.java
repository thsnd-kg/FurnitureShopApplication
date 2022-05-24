package com.furnitureshop.order.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.order.dto.order.CreateOrderDetailDto;
import com.furnitureshop.order.dto.order.GetOrderDto;
import com.furnitureshop.order.dto.order.UpdateOrderDto;
import com.furnitureshop.order.entity.OrderStatus;
import com.furnitureshop.order.entity.PaymentStatus;
import com.furnitureshop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public Object getOrders() {
        try {
            List<GetOrderDto> orders = service.getOrders().stream().map(GetOrderDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(orders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{order-id}")
    public Object getOrder(@PathVariable("order-id") Long orderId) {
        try {
            GetOrderDto order = new GetOrderDto(service.getOrder(orderId));
            return ResponseHandler.getResponse(order, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public Object getYourOrders() {
        try {
            List<GetOrderDto> orders = service.getYourOrders().stream().map(GetOrderDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(orders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cart")
    public Object getYourCart() {
        try {
            GetOrderDto cart = new GetOrderDto(service.getYourCart());
            return ResponseHandler.getResponse(cart, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/checkout")
    public Object checkOutOrder() {
        try {
            return ResponseHandler.getResponse(new GetOrderDto(service.checkout()), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-item")
    public Object addCartItem(@Valid @RequestBody CreateOrderDetailDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(new GetOrderDto(service.addCartItem(dto)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove-item")
    public Object removeCartItem(@RequestBody Long variantId, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(new GetOrderDto(service.deleteCartItem(variantId)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-voucher")
    public Object addVoucher(@RequestBody Long voucherId, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(new GetOrderDto(service.addVoucher(voucherId)), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove-voucher")
    public Object removeVoucher() {
        try {
            return ResponseHandler.getResponse(new GetOrderDto(service.removeVoucher()), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-order-status")
    public Object changeOrderStatus(@RequestBody UpdateOrderDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetOrderDto order = new GetOrderDto(service.changeOrderStatus(dto));
            return ResponseHandler.getResponse(order, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
