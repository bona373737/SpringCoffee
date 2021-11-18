<template>
<div>
  <div class="outterDiv" v-if="this.$store.state.isLogin">
    <div class="wrapper text-start">
      <span style="font-size: 14pt; font-weight: 700;">배송 정보</span>
      <div class="m-info border-top border-bottom">
        <div class="infoform">
          <div class="info d-flex py-3">
            <span class="info1 text-end me-4">이름</span>
            <span class="info2 text-start">{{this.$store.state.memberProfile.name}}</span>
          </div>
          <div class="info d-flex py-3">
            <span class="info1 text-end me-4">배송지</span>
            <span v-if="!isUpdate" class="info2 text-start">{{this.$store.state.memberProfile.address}}</span>
            <span v-if="!isUpdate" class="bt-change" @click="updateAddress()" style="position:absolute; left: 505px">변경하기</span>
            <input v-model="address" v-if="isUpdate" class="info2 text-start me-2" type="text" style="height: 25px; width:290px;">
            <span v-if="isUpdate" class="bt-change" @click="updateAddress()" style="position:absolute; left: 505px">취소하기</span>
          </div>
        </div>
      </div>
    </div>
    <div class="border-top py-3">
      <button type="button" class="btn me-3" @click="onSubmit()">결제하기</button>
    </div>
    <div class="py-5"></div>
    
    <CartDetail></CartDetail>
  </div>
  <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
</div>
</template>

<script>
import CartDetail from './CartDetail.vue';
import axios from 'axios';

export default {
  name: 'Order',
  components: {
    CartDetail,
  },
  data() {
    return {
      isUpdate: false,
      address: '',
      cartItemNo: [],
    }
  },
  methods: {
    onSubmit() {
    if(!this.isUpdate) {
      this.address=this.$store.state.memberProfile.address;
    }
    for(let i=0; i<this.$store.state.cart.length; i++) {
      this.cartItemNo[i]=this.$store.state.cart.cartItemNo;
    }

    if(confirm("등록하시겠습니까?") == true) {
      alert('등록되었습니다.')
    } else {
      return false;
    }

    axios.post('/v5/cartOrder', 
      {
        cartItemNo : this.cartItemNo,
        email : this.$store.state.email,
        address : this.address
      })
    },

    updateAddress() {
      if(this.isUpdate==false)
        this.isUpdate=true;
      else
        this.isUpdate=false;
    }
  }
}
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

.buy {
    font-size: 26pt;
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.outterDiv {
    width: 100%;
    height: 100%;
}

.m-info {
  width:500px;
  margin: auto;
}

.infoform {
  width:100%;
}

.info {
  height: 50px;
}

.info1 {
  width: 100px;
  position: absolute;
}

.info2 {
  width:400px;
  left:130px;
  position: relative;
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

.btn {
  background-color: #663C2A;
  color: white;
}

.btn:hover {
  background-color: #A36043;
}

.bt-change:hover {
  font-weight: 700;
  transition: 0.2s;
}
</style>