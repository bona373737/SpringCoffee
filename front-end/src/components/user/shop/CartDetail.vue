<template>
<div>
  <div v-if="this.$store.state.isLogin" class=" wrapper border-top border-bottom py-3" style="font-weight:700; font-size:14pt">장바구니 목록</div>
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
                    <td> 총 가격 </td>
                    <td></td>
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
            <div class="py-3" style="font-weight: 800; font-size: 16pt;">총: {{price}} 원</div><br>

            <div class="wrap py-3">
              <div class="point">배송 정보</div>
              <div class="m-info border-top border-bottom">
                <div class="infoform">
                  <div class="info d-flex">
                    <span class="info1 text-end me-3">이름</span>
                    <span class="info2 text-start">asasd</span>
                  </div>
                  <div class="info d-flex">
                    <span class="info1 text-end me-3">배송지</span>
                    <span class="info2 text-start">asasd</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="border-top py-3">
                <button type="button" class="btn btn-success"><router-link to="/order" style="text-decoration: none; color: white" >결제하기</router-link></button>
            </div>
        </div>
    </div>
    <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name : 'CartDetail',
  data() {
    return {
        sum: 0,
        price: 0,
    };
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

.m-info {
  width:500px;
  margin: auto;
}

.infoform {
  width:100%;
}

.info {
  height: 30px;
}

.info1 {
  width: 300px;
}

.info2 {
  width:100%;
}

.wrap {
  width: 500px;
}

.point {
  width: 100%;
  border-top: 1px solid #663C2A;
  font-size: 14pt;
  font-weight: 700;
  color: #A36043;
}
</style>