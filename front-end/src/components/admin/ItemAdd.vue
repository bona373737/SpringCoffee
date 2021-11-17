<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context text-start col-5 m-auto">
        <tr>
          <th>제목</th>
          <td class="table-title"><input type="text" v-model="name"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td class="table-context"><textarea v-model="content"></textarea></td>
        </tr>
        <tr>
          <th>이미지</th>
          <input v-on:change="onInputImage" multiple accept="image/*" ref="serveyImage" type="file">
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
            <input type="radio" v-model="category" value="coffee">coffee
            <input type="radio" v-model="category" value="tumbler">tumbler
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="onSubmit"> 추가 </button>
        <button class="btn btn-success"> 목록으로 가기 </button>
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
      image: {},
      stockQuantity: '',
      price: '',
      category: '',
    }
  },
  methods:{
    onSubmit() {
      const input = new FormData();
      input.append('name', this.name);
      input.append('content', this.content);
      input.append('image', this.image);
      input.append('stockQuantity', this.stockQuantity);
      input.append('price', this.price);
      input.append('category', this.category)

      for(let key of input.entries()) {
        console.log(`${key}`);
      }

      axios.post( '/v2/create', input, {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
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
</style>