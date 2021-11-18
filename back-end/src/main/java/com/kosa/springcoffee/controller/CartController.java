package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.*;
import com.kosa.springcoffee.entity.CartItem;
import com.kosa.springcoffee.entity.Member;
import com.kosa.springcoffee.repository.CartItemRepository;
import com.kosa.springcoffee.repository.MemberRepository;
import com.kosa.springcoffee.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v4")
@Log4j2
public class CartController {
    private final CartService cartService;
    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;


//    @PostMapping("/cart")
//    @ResponseBody
//    public ResponseEntity cart(@RequestBody CartItemDTO cartItemDTO,Principal principal){
//        Long cartItemNo;
//
//        try{
//            cartItemNo = cartService.create(cartItemDTO, principal.getName());
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        System.out.println("logloglog" + principal.getName() + " " + cartItemDTO.getItemNo()  + cartItemDTO.getCount());
//        cartItemNo = cartService.create(cartItemDTO,principal.getName());
//        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);
//
//    }

    @PostMapping("/cart")
    @ResponseBody
    public ResponseEntity cart(@RequestBody CartItemTestDTO cartItemDTO){
        Long cartItemNo;
        CartItemDTO dto = new CartItemDTO();
        dto.setCount(cartItemDTO.getCount());
        dto.setItemNo(cartItemDTO.getItemNo());
        Member member = memberRepository.getByEmail(cartItemDTO.getEmail());
        cartItemNo = cartService.create(dto,member.getEmail());
        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);
    }


//    @GetMapping(value = "/cart")
//    public ResponseEntity cartList(Principal principal, Model model) {
//        List<CartDetailDTO> cartDetailDTOList = cartService.getCartList(principal.getName());
//        model.addAttribute("cartItems", cartDetailDTOList);
//        return new ResponseEntity<List<CartDetailDTO>>(cartDetailDTOList, HttpStatus.OK);
//    }

    @GetMapping(value = "/cart/{email}")
    public ResponseEntity cartList(@PathVariable String email, Model model) {
        Member member = memberRepository.getByEmail(email);
        List<CartDetailDTO> cartDetailDTOList = cartService.getCartList(member.getEmail());
        model.addAttribute("cartItems", cartDetailDTOList);
        return new ResponseEntity<List<CartDetailDTO>>(cartDetailDTOList, HttpStatus.OK);
    }


    @PatchMapping(value = "/cartItem")
    public @ResponseBody ResponseEntity changeCartItemCount(@RequestBody CartPatchDTO cartPatchDTO){
        int count = cartPatchDTO.getCount();
        Long cartItemNo = cartPatchDTO.getCartItemNo();

        Member member = memberRepository.getByEmail(cartPatchDTO.getEmail());
        if (count < 0){
            return new ResponseEntity<String>("최소 1개 이상 담아야합니다.", HttpStatus.BAD_REQUEST);
        }
        else if(cartService.validateCartItem(cartItemNo, member.getEmail()))
            return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);

        cartService.changeCartItemCount(cartItemNo,count);
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);
        System.out.println(cartItem.getCount());
        System.out.println();


        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cartItemNo}/{email}")
    public ResponseEntity deleteCartItem(@PathVariable("email")String email, @PathVariable("cartItemNo") Long cartItemNo){
        Member member = memberRepository.getByEmail(email);
        if(cartService.validateCartItem(cartItemNo, member.getEmail()))
            return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);
        cartService.deleteCartItem(cartItemNo);
        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);
    }

//    @PostMapping(value = "/cartOrder")
//    @ResponseBody
//    public ResponseEntity orderCartItem(@RequestBody CartOrderDTO requestDTO){
//        Member member= memberRepository.getByEmail(requestDTO.getEmail());
//        String address = requestDTO.getAddress();
//        System.out.println("이메일 : " + requestDTO.getEmail() + "  주소 : " + requestDTO.getAddress());
//
//        System.out.println(requestDTO.getCartOrderDTOList());
//        List<CartOrderDTO> cartOrderDTOList = requestDTO.getCartOrderDTOList();
//
//        if (cartOrderDTOList == null || cartOrderDTOList.size() == 0){
//            return new ResponseEntity<String>("상품이 없습니다.", HttpStatus.BAD_REQUEST);
//        }
//
//
//        for (CartOrderDTO dto : cartOrderDTOList){
//            if(cartService.validateCartItem(dto.getCartItemNo(), member.getEmail()))
//                return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);
//        }
//        Long orderNo = cartService.orderCartItem(cartOrderDTOList, member.getEmail(), address);
//        return new ResponseEntity<Long>(orderNo, HttpStatus.OK);
//    }
@PostMapping(value = "/cartOrder")
@ResponseBody
public ResponseEntity orderCartItem(@RequestBody CartOrderRequestDTO requestDTO){
    Member member= memberRepository.getByEmail(requestDTO.getEmail());
    String address = requestDTO.getAddress();
    System.out.println("이메일 : " + requestDTO.getEmail() + "  주소 : " + requestDTO.getAddress());


    List<Long> cartOrderDTOList = requestDTO.getCartItemNo();

    if (cartOrderDTOList == null || cartOrderDTOList.size() == 0){
        return new ResponseEntity<String>("상품이 없습니다.", HttpStatus.BAD_REQUEST);
    }


    for (Long num : cartOrderDTOList){
        if(cartService.validateCartItem(num , member.getEmail()))
            return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);
    }
    Long orderNo = cartService.orderCartItem(cartOrderDTOList, member.getEmail(), address);
    return new ResponseEntity<Long>(orderNo, HttpStatus.OK);
}
}