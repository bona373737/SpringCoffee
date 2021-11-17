<template>
    <div>
        <div class="tab-bar">
            <div class="tab-shop py-3" style="width:100%;">
                <span class="tab-title">상품목록</span>            
            </div>
        </div>
        <div class="py-2"><br>
        </div>
        <div class="align-items-center justify-content-center justify-content-lg-end">
          <span class="bag" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><span class="bag "><i @click="this.$store.dispatch('fetchCart')" class="bi bi-bag-check-fill"></i></span><br></span><br>
          <span style="font-size: 11pt;">장바구니에 담아서 결제하세요!</span>
        <div class="text-end">
          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
              <h5 id="offcanvasRightLabel">장바구니</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body border-top">
            <table style="table-layout: auto; width: 100%; table-layout: fixed;">
              <colgroup>
                <col width="10%" />
                  <col width="10%" />
                  <col width="10%" />
                  <col width="10%" />
                  <col width="10%" />
              </colgroup>
              <tr class="text-center">
                <td> 이미지</td>
                <td> 이름</td>
                <td> 가격</td>
                <td> 개수</td>
                <td> 총 가격 </td>
              </tr>
              <tr class="product-item text-center" v-for="cart in this.$store.state.cartList" :key="cart.cartItemNo" >
                  <td> <img :src="cart.image" alt=""></td>
                  <td> {{cart.itemName}}</td>
                  <td> {{cart.price}}</td>
                  <td>
                    <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                    {{cart.count}}
                    <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                  </td>
                  <td v-bind="sumPrice(cart.price, cart.count)"> {{sum}}</td>
              </tr>
            </table>
            <span style="font-weight: 800; font-size: 16pt;"> {{price}} 원</span>
              <hr>
              <button type="button" class="btn btn-success"><router-link to="/cart" style="text-decoration: none; color: white">주문하기</router-link></button>
            </div>
          </div>
        </div>
      </div>
    <div class="outter-border">
        <div class="ProductListTechnologies d-flex py-5">
            <router-link to="#">
                <div class="ProductListTechnologies__element me-2">
                    <img width="120" height="80" src="../../../assets/shop1.webp" role="presentation" class="" alt="">
                    <div class="ProductListTechnologies__name">오리지널</div>
                </div>
            </router-link>
            <router-link to="#">
                <div class="ProductListTechnologies__element me-2">            
                    <img width="120" height="80" src="../../../assets/shop2.webp" role="presentation" class="" alt="">
                    <div class="ProductListTechnologies__name">버츄오</div>
                </div>
            </router-link>
        </div>

        <table class="table">
            <colgroup>
                <col width="10%" />
                <col width="10%" />
                <col width="5%" />
                <col width="10%" />
            </colgroup>
            <!-- <tr class="product-item">
                <td> <img src="" alt="상품img"></td>
                <td> 상품명 </td>
                <td> 가격 </td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                   <button type="button" class="btn btn-success"> - </button>
                   <button type="button" class="btn btn-success"> + </button>
                   </div>
                </td>
            </tr> -->
            <tr class="product-item" v-for="(item, i) in this.$store.state.itemList" v-bind:key="item.itemId">
                <td> <img width="120" height="80" ref="imageOutput" :src="item.image"> </td>
                <td @click="goItemDetail(item.itemNo)" > {{item.name}}</td>
                <td> {{item.price}}</td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                     <td class="me-2"> <input placeholder="0" type="number" min='0' style="border: 1px solid #333; border-radius: 5px; width:50px; height:100%" v-model="count[i]"></td>
                    <button type="button" class="btn btn-success" @click="postCart(item, count[i])">장바구니에 담기</button>
                   </div>
                </td>
            </tr>
        </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name:'shop',
  created() {
    this.$store.dispatch('fetchItem');
    this.$store.dispatch('fetchCart');
  },

  data() {
    return {
        sum: 0,
        price: 0,
        count: [],
    };
  },

  methods: {
    sumPrice(price, count) {
      this.sum=price*count;
    },
    postCart(item, count) {
      axios.post(`/v4/cart`,
      {
        itemNo : item.itemNo,
        count : count,
        email : this.$store.state.email
      })
      .then(res => {
        console.log('success', JSON.stringify(res, null, 2))
      }).catch(err => {
        console.log('failed', err)
      })
    },
    patchCart(cart) {
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
      if(cart.count == 0) {
          this.deleteCart(cart)
      }
    },
    deleteCart(cart) {
        axios.delete(`v4/cartItem`, {
            cartItemNo : cart.cartItemNo,
            email : this.$store.state.email
        })
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
  },
};
</script>

<style scoped>
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

.outter-border{
    width: 60%;
    margin: auto;
}
.product-item {
    border: 0;

}
.product-item:hover{
    background-color: beige;
    border: 0;
}
.table{
    height: 60%;
}

.nav-link {
    text-decoration: none;
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

.ProductListTechnologies {
  justify-content: center;
}
</style>