<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="15%" />
          <col width="*" />
        </colgroup>
        <tr>
          <th>카테고리</th>
          <td class="table-title">
            <form>
              <input type="radio" name="categoryOpt" v-model="category" id="상품문의" value="상품문의"> 상품문의
              <input type="radio" name="categoryOpt" v-model="category" id="배송문의" value="배송문의"> 배송문의
              <input type="radio" name="categoryOpt" v-model="category" id="교환 및 반품문의" value="교환 및 반품문의"> 교환 및 반품문의
              <input type="radio" name="categoryOpt" v-model="category" id="기타" value="기타"> 기타
            </form>
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td class="table-title"><input type="text" placeholder="문의글 제목" v-model="title"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td class="table-context"><textarea v-model="content"> 문의글 상세내용</textarea></td>
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="noticeAdd"> Q&A등록 </button>
        <button class="btn btn-success" @click="$router.push('/qnaLayout')"> 목록 </button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data(){
    return{
      category: '',
      title : '',
      content : '',
      writer: 'user3@springCoffee.com'  //  TODO 로그인한 사용자 이메일로 대체하기
    }
  },
  methods:{
    noticeAdd(){
      axios.post('/v3/register', {
        category: this.category,
        writer:this.writer,
        title: this.title,
        content : this.content
      });
      this.$router.push('/qnaLayout')
    }
  }
};

</script>

<style scoped>
.outterDiv{
    width: 60%;
    margin: auto;
}
.notice-context{
  width: 100%;
  text-align: left;
}
.table-title{
  padding: 15px;
  border-top: 2px solid #444444 ;
  border-bottom: 2px solid #444444 ;
}
.table-context{
  padding: 10px;
  height: 300px;
  border-bottom: 1px solid  #ddd;
}
.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}
button{
  margin-right: 5px;
}

</style>