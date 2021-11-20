<template>
  <div>
    <div v-if="this.$store.state.role!='ROLE_ADMIN'" v-on="this.$router.replace('NotfoundPage')"></div>

    <div class="outterDiv py-5">
      <table class="notice-context text-start m-auto" style="width:500px;">
        <div class="me-5 border-bottom" style="width:70px; font-size:14pt; height:30px; color: #4F2E20; font-weight: 700;">상품정보</div>
        <tr>
          <th>상품명</th>
          <td class="table-title"><input type="text" v-model="name"></td>
        </tr>
        <tr>
          <th>상품설명</th>
          <td class="table-context"><textarea v-model="content"></textarea></td>
        </tr>
        <tr>
          <th>상품이미지</th>
          <input v-on:change="onInputImage" accept="image/*" ref="serveyImage" type="file">
        </tr>
        <tr>
          <th>재고량</th>
          <td class="table-stockQuantity"><input v-model="stockQuantity"></td>
        </tr>
        <tr>
          <th>가격</th>
          <td class="table-price"><input v-model="price"></td>
        </tr>
        <tr>
          <th>카테고리</th>
            <input class="cate" style="width: 10px;" type="radio" v-model="category" value="coffee"> coffee
            <input class="cate" style="width: 10px; margin-left: 10px" type="radio" v-model="category" value="tumbler"> tumbler
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success me-3" @click="onSubmit"> 추가 </button>
        <button class="btn btn-secondary"> 목록으로 가기 </button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ItemAdd',
    data() {
    return {
      name : '',
      content : '',
      image: '',
      stockQuantity: '',
      price: '',
      category: '',
    }
  },
  methods:{
    onSubmit() {
      if(this.name == '') {
        alert('상품명을 입력해주세요')
        return false;
      } else if(this.content == '') {
        alert('상품 내용을 입력해주세요')
        return false;
      } else if(this.image == '') {
        alert('상품 이미지는 필수 선택입니다.')
        return false;
      } else if(this.stockQuantity == '') {
        alert('재고량을 설명해주세요')
        return false;
      } else if(this.price == '') {
        alert('가격을 선택해주세요')
        return false;
      } else if(this.category == '') {
        alert('카테고리를 선택해주세요')
        return false;
      }



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

      axios.post( '/v2/create', formData, {
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
      // var image = this.$refs.serveyImage.files[0];
      // const url = URL.createObjectURL(image)
      // this.image = url;
      // console.log(url)
      // console.log("this.input.image")
    },
    
  }
};

</script>

<style>
.notice-context input {
  width: 100%
}
.notice-context textarea {
  width: 100%;
  height: 200px;
}

</style>