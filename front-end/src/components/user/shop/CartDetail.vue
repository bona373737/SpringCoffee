<template>
<div>
  <div v-if="this.$store.state.isLogin" class=" wrapper border-bottom text-start" style="font-weight:700; font-size:14pt">장바구니</div>
    <div class="wrapper py-3">
        <div class="temp ">

            <!-- <span class="non-cart"> 장바구니가 비어있습니다.</span><br>
            <button class="go-shop btn btn-primary" @click="this.$router.replace('/shop')">쇼핑하러 가기</button> -->
        
            <table style="table-layout: auto; width: 100%; table-layout: fixed;">
                <colgroup>
                <col width="10%" />
                    <col width="10%" />
                    <col width="10%" />
                    <col width="10%" />
                    <col width="10%" />
                    <col width="3%" />
                </colgroup>

                <tr>
                    <td> 이미지</td>
                    <td> 이름</td>
                    <td> 가격</td>
                    <td> 개수</td>
                    <td> 합 </td>
                    <td></td>
                </tr>

                <tr class="product-item" v-for="cart in this.$store.state.cartList" :key="cart.cartItemNo">
                    <td><img :src="cart.image" alt=""></td>
                    <td @click="goItemDetail(cart)">{{cart.itemName}}</td>
                    <td>{{cart.price}}</td>
                    <td>
                        <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                        {{cart.count}}
                        <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                    </td>
                    <td> {{cart.count*cart.price}}</td>
                    <td> <i style="cursor:pointer;" @click="deleteCart(cart)" class="bi bi-trash-fill"></i></td>
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
            <div class="py-3"></div><br>
            <Order></Order>
        </div>
    </div>
    <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
</div>
</template>

<script>
import axios from 'axios'
import Order from './Order'

export default {
  name : 'CartDetail',
  data() {
    return {
        sum: 0,
        price: 0,
    };
  },
  components: {
    Order
  },
  created() {
      this.$store.dispatch('fetchCart');
  },
  methods: {

    patchCart(cart) {
      if(cart.count<1) {
        this.deleteCart(cart);
        return false;
      }
      axios.patch('v4/cartItem', {
        cartItemNo : cart.cartItemNo,
        count : cart.count,
        email : this.$store.state.email
      })
      .then(res => {
        console.log('success', JSON.stringify(res, null, 2))
      }).catch(err => {
        console.log('failed', err)
      })
    },

    setCountP(cart) {
      cart.count++;
    },
    setCountM(cart) {
      cart.count--;
      if(cart.count < 1) {
          this.deleteCart(cart)
      }
    },

    deleteCart(cart) {
        axios.delete(`v4/${cart.cartItemNo}/${this.$store.state.email}`)
        .then(res => {
          this.$store.dispatch('fetchCart');
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

<style scoped>
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
    margin: auto;
    width: 500px;
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