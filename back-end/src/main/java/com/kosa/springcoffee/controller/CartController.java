package com.kosa.springcoffee.controller;

import com.kosa.springcoffee.dto.CartDetailDTO;
import com.kosa.springcoffee.dto.CartItemDTO;
import com.kosa.springcoffee.dto.CartItemTestDTO;
import com.kosa.springcoffee.dto.CartOrderDTO;
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


    @PatchMapping(value = "/cartItem/{cartItemNo}/{count}")
    public @ResponseBody ResponseEntity changeCartItemCount(@PathVariable("cartItemNo") Long cartItemNo,@PathVariable("count") int count, Principal principal){
        if (count < 0){
            return new ResponseEntity<String>("최소 1개 이상 담아야합니다.", HttpStatus.BAD_REQUEST);
        }
        else if(cartService.validateCartItem(cartItemNo, principal.getName()))
            return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);

        cartService.changeCartItemCount(cartItemNo,count);
        CartItem cartItem = cartItemRepository.findByCartItemNo(cartItemNo);
        System.out.println(cartItem.getCount());
        System.out.println();


        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cartItem/{cartItemNo}")
    @ResponseBody
    public ResponseEntity deleteCartItem(@PathVariable("cartItemNo") Long cartItemNo, Principal principal){

        if(cartService.validateCartItem(cartItemNo, principal.getName()))
            return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);

        cartService.deleteCartItem(cartItemNo);

        return new ResponseEntity<Long>(cartItemNo, HttpStatus.OK);

    }

    @PostMapping(value = "/cart/cartOrder")
    @ResponseBody
    public ResponseEntity orderCartItem(@RequestBody CartOrderDTO cartOrderDTO, Principal principal){
        List<CartOrderDTO> cartOrderDTOList = cartOrderDTO.getCartOrderDTOList();
        if (cartOrderDTOList == null || cartOrderDTOList.size() == 0){
            return new ResponseEntity<String>("상품이 없습니다.", HttpStatus.BAD_REQUEST);
        }


        for (CartOrderDTO dto : cartOrderDTOList){
            if(cartService.validateCartItem(dto.getCartItemNo(), principal.getName()))
                return new ResponseEntity<String>("자신의 카트가 아닙니다.", HttpStatus.FORBIDDEN);
        }
        Long orderNo = cartService.orderCartItem(cartOrderDTOList, principal.getName());
        return new ResponseEntity<Long>(orderNo, HttpStatus.OK);
    }

}