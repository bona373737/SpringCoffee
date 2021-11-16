package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.OrderDTO;
import com.kosa.springcoffee.dto.OrderHistDTO;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v3")
@Log4j2
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberRepository memberRepository;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity order(@RequestBody OrderDTO orderDTO){
        Long orderNo;
        try {
            orderNo = orderService.create(orderDTO, orderDTO.getEmail());
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Long>(orderNo, HttpStatus.OK);
    }

    @GetMapping(value = {"/orders/{email}", "/orders/{page}/{email}"})
    public ResponseEntity orderHist(@PathVariable(name = "page") Optional<Integer> page,@PathVariable(name = "email") String email, Model model) {
        Member member = memberRepository.getByEmail(email);
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);

        Page<OrderHistDTO> orderHistDtos = orderService.getOrderList(member.getEmail(), pageable);
        model.addAttribute("orders", orderHistDtos);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5);
        return new ResponseEntity<Page<OrderHistDTO>>(orderHistDtos, HttpStatus.OK);
    }

    @PostMapping("/{orderNo}/{email}/cancel")
    @ResponseBody
    public ResponseEntity cancel(@PathVariable("orderNo") Long orderNo, @PathVariable("email") String email){
        Member member = memberRepository.getByEmail(email);
        if(!orderService.validateOrder(orderNo, member.getEmail())){
            return new ResponseEntity<String>("주문취소권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        orderService.cancelOrder(orderNo);
        return new ResponseEntity<Long>(orderNo, HttpStatus.OK);
    }

}
