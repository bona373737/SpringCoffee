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
          <span class="bag" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><span class="bag "><i @click="this.$store.dispatch('fetchCart'), this.getSumPrice()" class="bi bi-bag-check-fill"></i></span><br></span><br>
          <span style="font-size: 11pt;">장바구니에 담아서 결제하세요!</span>
        <div class="text-end">
          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
              <h5 id="offcanvasRightLabel">장바구니</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
            <table style="table-layout: auto; width: 100%; table-layout: fixed;">
              <colgroup>
                <col width="11%" />
                <col width="10%" />
                <col width="7%" />
                <col width="10%" />
                <col width="3%" />
              </colgroup>
              <tr class="text-center border-top" style="height:50px;">
                <td style="color: #A36043; font-weight: 700;"> 이미지</td>
                <td style="color: #A36043; font-weight: 700;"> 이름</td>
                <td style="color: #A36043; font-weight: 700;"> 개수</td>
                <td style="color: #A36043; font-weight: 700;"> 총 가격 </td>
                <td> </td>
              </tr>
              <tr class="product-item text-center" v-for="cart in this.$store.state.cartList" :key="cart.cartItemNo">
                <td>
                  <img width="100" height="100" :src="getThumbnail(cart.fileId)">
                </td>
                <td> {{cart.itemName}}</td>
                <td>
                  <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-caret-up-fill"></i></span>
                  {{cart.count}}
                  <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-caret-down-fill"></i></span>
                </td>
                <td> {{cart.price*cart.count}}</td>
                <td><i style="cursor:pointer;" @click="deleteCart(cart), getSumPrice()" class="bi bi-x-lg"></i></td>
              </tr>
            </table>
              <div style="width:100%; text-align:center;" v-if="Object.keys(this.$store.state.cartList).length==0"> 장바구니가 비어있습니다.</div>
              <hr>
              <div v-if="Object.keys(this.$store.state.cartList).length>0">
                 <!-- <span style="font-weight: 800; font-size: 16pt;"> {{String(this.totPrice).replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}</span>원  -->
                <button type="button" class="btn btn-cart text-reset" data-bs-dismiss="offcanvas" aria-label="Close">
                  <router-link to="/cart" style="text-decoration: none; color: white"> 주문하기</router-link>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    <div class="outter-border">
        <div class="ProductListTechnologies d-flex py-5">
          <div class="ProductListTechnologies__element me-4" @click="this.$store.dispatch('fetchItem')">
            <img width="100" height="100" src="../../../assets/1-all.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top" style="color: brown">전체보기</div>
          </div>
          <div class="ProductListTechnologies__element me-4" @click="this.$store.dispatch('getItemCategory', 'beans')">
            <img width="100" height="100" src="../../../assets/2-coffee-bean.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top">커피빈</div>
          </div>
          <div class="ProductListTechnologies__element me-4" @click="this.$store.dispatch('getItemCategory', 'powder')">            
            <img width="100" height="100" src="../../../assets/3-powder.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top">파우더</div>
          </div>
          <div class="ProductListTechnologies__element me-4" @click="this.$store.dispatch('getItemCategory', 'syrup')">
            <img width="100" height="100" src="../../../assets/4-syrup.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top">시럽</div>
          </div>
          <div class="ProductListTechnologies__element me-4" @click="this.$store.dispatch('getItemCategory', 'md')">            
            <img width="100" height="100" src="../../../assets/5-md.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top">MD</div>
          </div>
          <div class="ProductListTechnologies__element" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight" @click="this.$store.dispatch('fetchCart'), this.getSumPrice()">            
            <img width="100" height="100" src="../../../assets/6-cart.png" role="presentation" class="" alt="">
            <div class="ProductListTechnologies__name py-2 border-top" >장바구니</div>
          </div>
        </div>

        <table class="table">
          <colgroup>
            <col width="10%" />
            <col width="10%" />
            <col width="5%" />
            <col width="8%" />
            <col width="10%" />
          </colgroup>
            <tr class="text-center border-top border-bottom" style="height:50px">
              <td>상품이미지</td>
              <td>상품명</td>
              <td>가격</td>
              <td class="text-center">수량</td>
              <td></td>
            </tr>
          <tr class="product-item" v-for="(item, i) in this.$store.state.itemList" :key="item.fileId">
              <td @click="goItemDetail(item.itemNo)" style="cursor: pointer;">
                <img width="100" height="100" :src="getThumbnail(item.fileId)">
              </td>
              <td @click="goItemDetail(item.itemNo)" style="font-weight:700; cursor: pointer;"> {{item.name}}</td>
              <td @click="goItemDetail(item.itemNo)" style="cursor: pointer;" class="text-end"><span style="font-weight:700; ">{{String(item.price).replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}</span>원</td>
              <td>
                  <div class="btn-group" role="group" aria-label="Basic example">
                    <input placeholder="0" type="number" min='0' style="background-color:transparent; text-align:center; border: 1px solid #999; border-radius: 5px; width:50px; height:30px" v-model="count[i]">
                  </div>
              </td>
              <td>
                <button type="button" class="btn btn-cart" @click="postCart(item, count[i])">장바구니에 담기</button>
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
        sum: [],
        totPrice: 0,
        count: [],
        image: '',
    };
  },

  methods: {
    getThumbnail(fileId) {
      return "http://localhost:8080/v2-2/thumbnail/"+fileId
    },
    getSumPrice() {
      this.$store.dispatch('fetchCart');
      this.totPrice=0;
      this.sum=[];
      let index = Object.keys(this.$store.state.cartList).length
      for(let i=0; i<index; i++) {
        let price = this.$store.state.cartList[i].price
        let count = this.$store.state.cartList[i].count
        this.sum[i]=price*count;
        this.totPrice+=this.sum[i];
      }
    },
    postCart(item, count) {
      axios.post(`/v5/cart`,
      {
        itemNo : item.itemNo,
        count : count,
        email : this.$store.state.email
      })
      .then(res => {
        console.log('success', res)
      }).catch(err => {
        console.log('failed', err)
      })
    },
    patchCart(cart) {
      if(cart.count<1) {
        this.deleteCart(cart);
        return false;
      }

      this.getSumPrice()

      axios.patch('v5/cartItem', {
        cartItemNo : cart.cartItemNo,
        count : cart.count,
        email : this.$store.state.email
      })
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
    },
    deleteCart(cart) {
        axios.delete(`v5/${cart.cartItemNo}/${this.$store.state.email}`)
        .then(res => {
          this.$store.dispatch('fetchCart')
          console.log('success', res)
          this.getSumPrice()
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
    width: 700px;
    margin: auto;
}
.product-item {
    border: 0;

}
.product-item:hover{
    background-color: beige;
    border: 0;
    transition: 0.1s;
}
.table{
    height: 60%;
}

.nav-link {
    text-decoration: none;
}

.plus {
  color: #999;
}

.minus {
  color: #999;
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
  position: fixed;
  right: 40px;
  top: 40%;
  /* border: 2px solid #999; */
  opacity: 1;
  z-index: 1;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
}

input::placeholder {
  color: #999;
  opacity: 0.5;
}

.btn-group {
  align-items: center;
  height: 100%;
}
.btn-group input {
  margin: auto;
  height: 100%;
}

.btn-cart {
  color: white;
  background-color: #A36043;
}

.btn-cart:hover {
  background-color: #4F2E20;
}

</style>