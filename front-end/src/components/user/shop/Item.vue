<template>
  <div>
    <div class="outterDiv py-5">

        <div class="grid d-flex">
            <div class="grid-box py-5">
                <img src="" alt="">
            </div>
            <div class="grid-box">
              <div>{{$store.state.itemDetail.name}}</div>
                <div class="title d-flex border-bottom"> <span class="itemdata">{{name}}</span></div>
                 <div class="price text-center py-5"> <span class="itemdata">{{this.$store.state.itemDetail.content}}</span></div>
                <div class="price text-end border-top"> <span class="itemdata">{{this.$store.state.itemDetail.price}}원</span></div>
                <div class="order border-top py-4 text-end">
                    <button @click="postCart()" class="btn btn-success me-2"> 장바구니</button> 
                    <button class="btn btn-primary"> 구매하기</button>
                </div>
            
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Item',
  data() {
    return {
      name : this.$store.state.itemDetail.name
    }
  },
  created() {
    // this.$store.dispatch('fetchItemDetail', this.$route.params.itemNo)
  },
  methods: {
    postCart() {
      if(!this.$store.state.isLogin) {
        alert('회원가입 하신 유저만 장바구니를 사용 가능합니다.')
        return false;
      }

      axios.post(`/v4/cart`,
      {
        itemNo : this.$store.state.itemDetail.itemNo,
        count : 1,
        email : this.$store.state.email
      })
      .then(res => {
        console.log('success', JSON.stringify(res, null, 2))
      }).catch(err => {
        console.log('failed', err)
      })
    },
  }
}
</script>

<style>

.grid {
    margin: auto;
    width: 1000px;
}

.grid-box {
    width: 45%;
    margin: auto;
}

.grid-box img {
    width: 300px;
    height : 300px;
}

.title {
    font-size: 18pt;
    font-weight: 300;
}

.price {
    font-size: 20pt;
    font-weight: 600;
}

.itemdata {
    padding: 0 30px 0 30px;
}

.order {
    margin: auto;
    padding: 10px;
}
</style>