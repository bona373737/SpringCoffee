package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.Member.Member;
import com.kosa.springcoffee.entity.Order;
import com.kosa.springcoffee.Member.security.MemberRepository;
import com.kosa.springcoffee.repository.OrderRepository;
import com.kosa.springcoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/v6")
@Log4j2
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @PostMapping(value = "/submit")
    @ResponseBody
    public ResponseEntity order(@RequestBody OrderDTO orderDTO){
        Long orderNo;
        try {
            orderNo = orderService.create(orderDTO, orderDTO.getEmail());


        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        Order order = orderRepository.findByOrderNo(orderNo);
        OrderResponseDTO dto = OrderResponseDTO.builder().orderNo(orderNo).total(order.getTotalPrice()).build();

        return new ResponseEntity<OrderResponseDTO>(dto, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders/{page}/{email}"})
    public ResponseEntity orderHist(@PathVariable(name = "page") Optional<Integer> page,@PathVariable(name = "email") String email) {
        Member member = memberRepository.getByEmail(email);
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistDTO> orderHistDtos = orderService.getOrderList(member.getEmail(), pageable);

        return new ResponseEntity<Page<OrderHistDTO>>(orderHistDtos, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders"})
    public ResponseEntity orderHist(@RequestParam(name = "page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistDTO> orderHistDtos = orderService.getOrderListForAdmin(pageable);
        return new ResponseEntity<Page<OrderHistDTO>>(orderHistDtos, HttpStatus.OK);
    }

//    @PostMapping("/cancel")
//    public ResponseEntity cancel(@RequestBody OrderStatuslDTO dto){
//        Member member = memberRepository.getByEmail(dto.getEmail());
//        Order order = orderRepository.findByOrderNo(dto.getOrderNo());
//
//        if (order == null) return new ResponseEntity<String>("주문이 없습니다.", HttpStatus.FORBIDDEN);
//        if(!orderService.validateOrder(order.getOrderNo(), member.getEmail())){
//            return new ResponseEntity<String>("주문취소권한이 없습니다", HttpStatus.FORBIDDEN);
//        }
//
//        orderService.cancelOrder(order.getOrderNo());
//        return new ResponseEntity<Long>(order.getOrderNo(), HttpStatus.OK);
//    }

    @PostMapping("/cancel/{orderNo}")
    @ResponseBody
    public ResponseEntity cancel(@PathVariable Long orderNo){
        Order order = orderRepository.findByOrderNo(orderNo);

        if (order == null) return new ResponseEntity<String>("주문이 없습니다.", HttpStatus.FORBIDDEN);

        orderService.cancelOrder(order.getOrderNo());
        return new ResponseEntity<Long>(order.getOrderNo(), HttpStatus.OK);
    }

    @PostMapping("/prepare")
    @ResponseBody
    public ResponseEntity prepare(@RequestParam Long orderNo){
        Order order = orderRepository.findByOrderNo(orderNo);

        if (order == null) return new ResponseEntity<String>("주문이 없습니다.", HttpStatus.FORBIDDEN);

        orderService.prepareOrder(order.getOrderNo());
        return new ResponseEntity<Long>(order.getOrderNo(), HttpStatus.OK);
    }
    @PostMapping("/shipping")
    @ResponseBody
    public ResponseEntity shipping(@RequestParam Long orderNo){
        Order order = orderRepository.findByOrderNo(orderNo);

        if (order == null) return new ResponseEntity<String>("주문이 없습니다.", HttpStatus.FORBIDDEN);

        orderService.shippingOrder(order.getOrderNo());
        return new ResponseEntity<Long>(order.getOrderNo(), HttpStatus.OK);
    }
    @PostMapping("/done")
    @ResponseBody
    public ResponseEntity done(@RequestParam Long orderNo){
        Order order = orderRepository.findByOrderNo(orderNo);
        if (order == null) return new ResponseEntity<String>("주문이 없습니다.", HttpStatus.FORBIDDEN);
        orderService.doneOrder(order.getOrderNo());
        return new ResponseEntity<Long>(order.getOrderNo(), HttpStatus.OK);
    }

}
