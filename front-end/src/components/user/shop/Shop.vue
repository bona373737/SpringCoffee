<template>
    <div>
        <div class="tab-bar">
            <div class="tab-shop py-3">
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
                  <col width="3%" />
              </colgroup>
              <tr class="text-center">
                <td> 이미지</td>
                <td> 이름</td>
                <td> 가격</td>
                <td> 개수</td>
                <td> 총 가격 </td>
                <td></td>
              </tr>
              <tr class="product-item" v-for="cart in this.$store.state.cartList" :key="cart.cartItemNo">
                  <td></td><!-- <td> <img :src="cart.image" alt=""></td> -->
                  <td> {{cart.itemName}}</td>
                  <td> {{cart.price}}</td>
                  <td>
                    <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                    {{cart.count}}
                    <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                  </td>
                  <td v-bind="sumPrice(cart.price, cart.count)"> {{sum}}</td>
                  <td> <i style="cursor:pointer;" @click="deleteCart(cart)" class="bi bi-trash-fill"></i></td>
              </tr>
            </table>
            <span style="font-weight: 800; font-size: 16pt;"> {{price}} 원</span>
              <hr>
              <button type="button" class="btn btn-success text-reset" data-bs-dismiss="offcanvas" aria-label="Close"><router-link to="/cart" style="text-decoration: none; color: white">주문하기</router-link></button>
            </div>
          </div>
        </div>
      </div>
    <div class="outter-border">
        <div class="ProductListTechnologies d-flex py-5">
          <div class="ProductListTechnologies__element me-2" @click="this.$store.dispatch('fetchItem')">
              <img width="120" height="80" src="../../../assets/shop2.webp" role="presentation" class="" alt="">
              <div class="ProductListTechnologies__name" style="color: brown">전체보기</div>
          </div>
          <div class="ProductListTechnologies__element me-2" @click="this.$store.dispatch('getItemCategory', 'coffee')">
              <img width="120" height="80" src="../../../assets/shop1.webp" role="presentation" class="" alt="">
              <div class="ProductListTechnologies__name">커피</div>
          </div>
          <div class="ProductListTechnologies__element me-2" @click="this.$store.dispatch('getItemCategory', 'tumbler')">            
              <img width="120" height="80" src="../../../assets/tumbler.jpg" role="presentation" class="" alt="">
              <div class="ProductListTechnologies__name">텀블러</div>
          </div>
        </div>

        <table class="table">
            <colgroup>
                <col width="10%" />
                <col width="10%" />
                <col width="5%" />
                <col width="10%" />
            </colgroup>
              <tr class="text-center border-top border-bottom" style="height:50px">
                <td>이미지</td>
                <td>이름</td>
                <td>가격</td>
                <td> </td>
              </tr>
            <tr class="product-item" v-for="(item, i) in this.$store.state.itemList" :key="i">
                <td>
                  <img width="100" height="100" alt="상품이미지" :src="thumbnail[i]">
                </td>
                <td @click="goItemDetail(item.itemNo)" > {{item.name}}</td>
                <td> {{item.price}}</td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                     <td class="me-2"><input placeholder="0" type="number" min='0' style="border: none; width:40px; margin:auto; height:100%" v-model="count[i]"></td>
                    <button type="button" class="btn btn-success" @click="postCart(item, count[i])">장바구니에 담기</button>
                   </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="cart-icon">
      <span class="my-cart"><i style="font-size: 50pt" class="bi bi-cart-fill"></i></span>
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
        thumbnail: [],
    };
  },

  methods: {
    sumPrice(price, count) {
      this.sum=price*count;
    },
    postCart(item, count) {
      axios.post(`/v5/cart`,
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
      if(cart.count<1) {
        this.deleteCart(cart);
        return false;
      }

      axios.patch('v5/cartItem', {
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
    },
    fetchThumbnail() {
      console.log('스타트')
      for(let i=0; i<this.$store.state.itemList.length ; i++) {
        console.log('반복')
        axios.get(`/v2-2/thumbnail/${this.$store.state.itemList[i].fileId}`, {
          responseType: 'blob'
        }).then(res => {
          console.log('끝')
          this.thumbnail[i] = window.URL.createObjectURL(new Blob([res.data]))
        })
      }
    },
    deleteCart(cart) {
        axios.delete(`v5/${cart.cartItemNo}/${this.$store.state.email}`)
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
  width: 100%;
  height: 150px;
  display: table;
  }

.tab-title {
    font-weight: bold;
    font-size: 25pt;
    color: white;
    display: table-cell;
    vertical-align: middle;
}

.bag {
    font-size: 26pt;
}

.bag:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
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
.ProductListTechnologies__element {
  cursor: pointer;
}

.ProductListTechnologies__element:hover {
  transform:scale(1.1);
  transition: 0.2s;
}
.ProductListTechnologies__name {
  font-weight: 700;
  color: #333;
}

.cart-icon {
  width: 100px;
  height: 100px;
  border-radius: 10px;
  position: fixed;
  right: 20px;
  bottom: 20px;
}

.my-cart {
  cursor: pointer;
  color: green;
}
</style>