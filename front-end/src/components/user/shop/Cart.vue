<template>
  <div class="outterDiv">
    <div class="tab-bar">
        <div class="tab-shop py-3" style="width:100%;">
            <span @click="this.$store.dispatch('fetchCart')" class="tab-title">장바구니</span>            
        </div>
    </div>
    <div class="py-2"><br></div>
    <div class="align-items-center justify-content-center justify-content-lg-end">
        <span class="bag" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><span class="bag "><i @click="this.$store.dispatch('fetchCart', 'user95@springCoffee.com')" class="bi bi-bag-check-fill"></i></span><br></span><br>
        <span style="font-size: 11pt;">장바구니에 담아서 결제하세요!</span>
    </div><br><br>


    <div class=" wrapper border-bottom py-3">{{this.$store.state.email}} 님의 장바구니</div>
    <div class="wrapper border-top">
        <div class="temp">

                <!-- <span class="non-cart"> 장바구니가 비어있습니다.</span><br>
                <button class="go-shop btn btn-primary" @click="this.$router.replace('/shop')">쇼핑하러 가기</button> -->
            
                <table style="table-layout: auto; width: 100%; table-layout: fixed;">
                    <colgroup>
                    <col width="10%" />
                        <col width="10%" />
                        <col width="10%" />
                        <col width="10%" />
                        <col width="10%" />
                    </colgroup>

                    <tr>
                        <td> 이미지</td>
                        <td> 이름</td>
                        <td> 가격</td>
                        <td> 개수</td>
                        <td> 총 가격 </td>
                    </tr>

                    <tr class="product-item" v-for="cart in this.$store.state.cartList" :key="cart.cartItemNo">
                        <td> <img :src="cart.image" alt=""></td>
                        <td @click="goItemDetail(cart)">{{cart.itemName}}</td>
                        <td>{{cart.price}}</td>
                        <td>
                            <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                            {{cart.count}}
                            <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                        </td>
                        <td> {{sum}}</td>
                    </tr>

                    <!-- <tr class="product-item" v-for="cart in this.$store.state.cartList" :key="cart">
                        <td @click="goItemDetail(cart)"> {{cart.itemName}}</td>
                        <td> {{cart.price}}</td>
                        <td>
                            <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                            {{cart.count}}
                            <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                        </td>
                        <td v-bind="sumPrice(cart.price, cart.count)"> {{sum}}</td>
                    </tr> -->
                </table>
                <span style="font-weight: 800; font-size: 16pt;"> {{price}} 원</span><br>

                <div class="border-top py-3">
                    <button type="button" class="btn btn-success"><router-link to="/order" style="text-decoration: none; color: white" >주문하기</router-link></button>
                </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name : 'Cart',
  data() {
    return {
        sum: 0,
        price: 0,
        count: [],
    };
  },
  created() {
      this.$store.dispatch('fetchCart');
  },
  methods: {
    sumPrice(price, count) {
      this.sum=price*count;
    },

    patchCart(cart) {
      axios.patch(`v4/cartItem/${cart.cartItemNo}/${cart.count}`)
      .then(res => {
        console.log('success', res)
      }).catch(err => {
        console.log('failed', err)
      })
    },

    setCountP(cart) {
      cart.count++;
    },
    setCountM(cart) {
      cart.count--;
      if(cart.count == 0) {
          this.deleteCart(cart)
      }
    },

    deleteCart(cart) {
        axios.delete(`v4/cartItem/${cart.cartItemNo}`, cart.cartItemNo)
        .then(res => {
            console.log('success', res)
        }).catch(err => {
            console.log('failed', err)
        })
    },
    goItemDetail(itemNo) {
      this.$router.push({
        name: 'Item',
        params: { itemNo: itemNo }
      })
    },
  }
}
</script>

<style>
.tab-bar {
  background-image: url('../../../assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
}

.bag {
    font-size: 26pt;
}

.bag:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.product-item {
    border: 0;
}
.product-item:hover{
    background-color: beige;
    border: 0;
}

.plus:hover {
  cursor: pointer;
  color: green;
  transition: 0.2s;
}

.minus:hover {
  cursor: pointer;
  color: red;
  transition: 0.2s;
}

.wrapper {
    border: 1px solid #333;
    margin: auto;
    width: 60%;
}

.outterDiv {
    width: 100%;
    height: 100%;
}

.non-cart {
    color: #333;
}

.go-shop {
    margin-top: 50px;
    font-size: 28pt;
}
</style>