<template>
<div class="outter">
  <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
  <div class="wrapper text-start">
    <span style="font-size: 14pt; font-weight: 700;">배송 정보</span>
    <div class="border-top">
      <div class="m-info">
        <div class="infoform">
          <div class="info d-flex py-3">
            <span class="info1 text-end me-2">이름</span>
            <span class="info2 text-start">{{this.$store.state.memberProfile.name}}</span>
          </div>
          <div class="info d-flex py-3">
            <span class="info1 text-end me-2">배송지</span>
            <span v-if="!isUpdate" class="info2 text-start">{{this.$store.state.memberProfile.address}} <i @click="updateAddress()" class="bi bi-stickies onoff"></i></span>
            <input v-model="address" v-if="isUpdate" class="info2 text-start me-2" type="text" style="height: 25px; width:300px;">
          </div>
          <div class="info d-flex">
            <span class="info1 text-end me-4"></span>
            <span v-if="isUpdate" class="info2 text-start" style="font-size:10pt; width:190px; padding-left:5px">변경하실 주소를 입력해주세요</span>
            <span v-if="isUpdate" class="info2 onoff text-center" style="font-size:9pt; width:10px; height:20px;"><i @click="updateAddress()" class="bi bi-x-lg"></i></span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class=" py-4"></div>
  <div class="wrapper border-bottom text-start" style="font-weight:700; font-size:14pt">장바구니</div>
    <div class="wrapper">
      <div class="temp ">
        <table style="table-layout: auto; width: 100%; table-layout: fixed;">
            <colgroup>
            <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="10%" />
                <col width="3%" />
            </colgroup>

            <tr style="font-weight:700; height:40px;">
              <td class="order-tab"> 구매</td>
              <td class="order-tab"> 이미지</td>
              <td class="order-tab"> 이름</td>
              <td class="order-tab"> 가격</td>
              <td class="order-tab"> 개수</td>
              <td class="order-tab"> 합 </td>
              <td class="order-tab"></td>
            </tr>

            <tr class="product-item" v-for="(cart, i) in this.$store.state.cartList" :key="cart">
                <td><input type="checkbox" @click="onCart(cart,i)"></td>
                <td><img width="100" height="100" :src="getThumbnail(cart.fileId)"></td>
                <td @click="goItemDetail(cart)">{{cart.itemName}}</td>
                <td>{{cart.price}}</td>
                <td>
                    <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-caret-up-fill"></i></span>
                    {{cart.count}}
                    <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-caret-down-fill"></i></span>
                </td>
                <td> {{cart.count*cart.price}}</td>
                <td> <i style="cursor:pointer; color: #333" @click="deleteCart(cart)" class="bi bi-x-lg"></i></td>
            </tr>
        </table>
        <div class="py-3"></div><br>
      
        <div >
          <button type="button" class="btn me-3" @click="onSubmit()">결제하기</button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name : 'Order',
  data() {
    return {
      isUpdate: false,
      sum: 0,
      price: 0,
      buyItem: [],
      myCart: [],
      address: this.$store.state.memberProfile.address,
    };
  },
  created() {
    this.$store.dispatch('fetchCart');
    this.$store.dispatch('fetchMemberProfile');
  },
  methods: {
    getThumbnail(fileId) {
      return "http://localhost:8080/v2-2/thumbnail/"+fileId
    },

    updateAddress() {
      if(this.isUpdate==false)
        this.isUpdate=true;
      else
        this.isUpdate=false;
    },

    onCart(cart, index) {
      if(this.buyItem[index] == '') {
        this.buyItem[index]=true;
        this.myCart.splice(index,0,cart.cartItemNo)
      } 
      else if(this.buyItem[index] != true) {
        this.myCart.push(cart.cartItemNo);
        this.buyItem[index]=true
      } else if(this.buyItem[index] == true) {
        this.buyItem[index]=false;
        this.myCart=this.myCart.filter((element) => element !== cart.cartItemNo);
        // this.myCart.splice(index, 1);
      }
      console.log(this.myCart)
      
      
      // //값이 있는 경우
      // if(this.myCart.includes(cart.cartItemNo)){
      //   console.log(this.myCart.includes(cart.cartItemNo,0))
      //   let includeIndex = this.myCart.indexOf(cart.cartItemNo)
      //   this.myCart.splice(includeIndex, 1)
      //   console.log(this.myCart)
      // }
      // //값이 없는 경우
      // else {
      //   this.myCart.push(cart.cartItemNo)
      //   console.log(this.myCart)
      // }
    },

    onSubmit() {
      // if(this.isUpdate == false) {
      //   this.address=this.$state.store.memberProfile.address;
      // }

      if(confirm("결제하시겠습니까?") == true) {
        alert('결제되었습니다.')
      } else {
        return false;
      }

      axios.post('/v5/cartOrder', 
      {
        cartItemNo : this.myCart,
        email : this.$store.state.memberProfile.email,
        address : this.address
      })
      .then(res => {
        alert('등록 되었습니다.')
        this.$router.push('/shop')
        console.log(res)
      })
    },
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
    transition: 0.1s;
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

.outter {
  top: -100px;
  background-color: white;
  width: 700px;
  height: 100%;
  margin: auto;
}

.wrapper {
    margin: auto;
    width: 700px;
    height: 100%;
}

.non-cart {
    color: #333;
}

.btn {
  background-color: #663C2A;
  color: white;
}

.btn:hover {
  background-color: #A36043;
}

.wrap {
  width: 500px;
}

.info {
  height: 50px;
}

.info1 {
  width: 100px;
  position: absolute;
}

.m-info {
  width:500px;
  margin: auto;
}

.infoform {
  width:100%;
}

.info2 {
  width:400px;
  left:130px;
  position: relative;
}

.point {
  width: 100%;
  border-top: 1px solid #663C2A;
  font-size: 14pt;
  font-weight: 700;
  color: #A36043;
}

.bt-change:hover {
  font-weight: 700;
  transition: 0.2s;
}

.onoff:hover {
  cursor: pointer;
  color: #663C2A;
  transition: 0.1s;
}
</style>