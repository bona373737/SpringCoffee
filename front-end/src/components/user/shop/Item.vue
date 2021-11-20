<template>
  <div>
    <div style="background-color: #4F2E20; width:100%; height:30px;"></div>

    <div class="outterDiv">
      <div class="grid d-flex">
        <div class="grid-box py-5">
          <img v-if="!isCheck" src="" alt="">
          <img v-if="isCheck" src="" alt="">
          <input v-if="isCheck" v-on:change="onInputImage" accept="image/*" ref="serveyImage" type="file">
        </div>
        <div class="grid-box">
            <div v-if="!isCheck">
              <div class="title d-flex border-bottom"> <span class="itemdata">{{this.$store.state.itemDetail.name}}</span></div>
              <div class="con text-center py-5"><span class="itemdata">{{this.$store.state.itemDetail.content}}</span></div>
              <div class="con text-end border-top"><span class="itemdata">{{this.$store.state.itemDetail.price}} 원</span></div>
            </div>
            <div v-if="isCheck" class="text-start">
              <div style="width:100px;">상품명</div><input style="width:100%;" v-model="this.name" type="text">
              <div style="width:100px;">내용</div><textarea style="width:100%;" v-model="this.content"></textarea>
              <div style="width:100px;">가격</div><input style="width:100px;" v-model="this.price">원
              <div style="width:100px;">재고량</div><input style="width:100px;;" v-model="this.stockQuantity">
              <div class="py-2"></div>
            </div>
            <!-- <div class="title d-flex border-bottom"> <span class="itemdata">{{this.$store.state.itemDetail.name}}</span></div>
            <div class="con text-center py-5"><span class="itemdata">{{this.$store.state.itemDetail.content}}</span></div>
            <div class="con text-end border-top"><span class="itemdata">{{this.$store.state.itemDetail.price}}원</span></div> -->
            <div class="order border-top py-4 text-end">
              <button @click="updateItem()" class="btn btn-primary me-2" v-if="this.$store.state.role=='ROLE_ADMIN' && !isCheck">수정하기</button>
              <button @click="deleteItem()" class="btn btn-warning me-2" v-if="this.$store.state.role=='ROLE_ADMIN' && !isCheck">삭제하기</button>
              <button @click="putItem" class="btn btn-success me-2" v-if="this.$store.state.role=='ROLE_ADMIN' && isCheck">변경하기</button>
              <button @click="cancelUpdate()" class="btn btn-secondary me-2" v-if="this.$store.state.role=='ROLE_ADMIN' && isCheck">취소하기</button>
              <button @click="postCart()" v-if="!isCheck" class="btn btn-success me-2"> 장바구니</button> 
              <button @click="this.$router.push('/shop')" v-if="!isCheck" class="btn btn-secondary"> 목록</button>
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
      name : this.$store.state.itemDetail.name,
      isCheck: false,
      content: this.$store.state.itemDetail.content,
      stockQuantity: this.$store.state.itemDetail.stockQuantity,
      price: this.$store.state.itemDetail.price,
      image: this.$store.state.itemDetail.image,
    }
  },
  created() {
    this.$store.dispatch('fetchItemDetail', this.$route.params.itemNo)
  },
  methods: {
    postCart() {
      if(!this.$store.state.isLogin) {
        alert('회원가입 하신 유저만 장바구니를 사용 가능합니다.')
        return false;
      }

      axios.post(`/v5/cart`,
      {
        itemNo : this.$store.state.itemDetail.itemNo,
        count : 1,
        email : this.$store.state.email
      })
      .then(res => {
        this.$router.replace('/cart')
        console.log('success', JSON.stringify(res, null, 2))
      }).catch(err => {
        console.log('failed', err)
      })
    },
    cancelUpdate() {
      if(this.isCheck == true) {
        this.isCheck = false;
      }
    },
    updateItem() {
      if(this.isCheck == false) {
        this.name = this.$store.state.itemDetail.name
        this.isCheck = true
        this.content = this.$store.state.itemDetail.content
        this.stockQuantity = this.$store.state.itemDetail.stockQuantity
        this.price = this.$store.state.itemDetail.price
        this.image = 
        console.log(this.image)
      }
    },

    deleteItem() {
      axios.delete(`v2/${this.store.state.itemDetail.itemNo}`)
      .then(res => {
        alert('삭제되었습니다.')
        this.$router.push('/shop')
        console.log('성공', res)
      })
    },

    putItem() {
      console.log('aaa')

      const formData = new FormData();
      formData.append('name', this.name);
      formData.append('content', this.content);
      formData.append('image', this.image[0]);
      formData.append('stockQuantity', this.stockQuantity);
      formData.append('price', this.price);
      formData.append('category', this.category)
      
      for(let key of formData.entries()) {
        console.log(`${key}`);
      }
      
      if(confirm("등록하시겠습니까?") == true) {
        alert('등록되었습니다.')
      } else {
        return false;
      }
      console.log(this.$store.state.itemDetail.itemNo)
      axios.put(`v2/update/${this.$route.params.itemNo}`, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          this.$router.push('#')
          console.log('SUCCESS!!', res);
        })
    },
    onInputImage() {
      this.image = this.$refs.serveyImage.files;
      console.log(this.image);
    },
  }
}
</script>

<style scoped>

.grid {
    margin: auto;
    width: 1000px;
    height: 600px;
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
    font-weight: 600;
}

.con {
    font-size: 20pt;
    font-weight: 300;
}

.itemdata {
    padding: 0 30px 0 30px;
}

.order {
    margin: auto;
    padding: 10px;
}
</style>