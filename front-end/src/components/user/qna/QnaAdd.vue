<template>
  <div>
    <div class="py-2">
        <div style="height: 48px;"/>
        <span class="info"><i class="bi bi-question-circle-fill"></i></span><br>
        <span class="info-text">궁금한 것이 있으면 언제든 문의해주세요!</span>
    </div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="20%" />
          <col width="*" />
        </colgroup>
        <tr class="table-category">
          <th>문의종류</th>
          <td>
            <form>
              <input type="radio" name="categoryOpt" v-model="category" id="상품문의" value="상품문의"> 상품문의
              <input type="radio" name="categoryOpt" v-model="category" id="배송문의" value="배송문의"> 배송문의
              <input type="radio" name="categoryOpt" v-model="category" id="교환 및 반품문의" value="교환 및 반품문의"> 교환 및 반품문의
              <input type="radio" name="categoryOpt" v-model="category" id="기타" value="기타"> 기타
            </form>
          </td>
        </tr>
        <tr class="table-title">
          <th>제목</th>
          <td><input type="text" class="text-area-title" v-model="title"></td>
        </tr>
        <tr class="table-context">
          <th>내용</th>
          <td><textarea v-model="content" class="text-area-content"> 문의글 상세내용</textarea></td>
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="qnaAdd"> Q&A등록 </button>
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
      writer: this.$store.state.email
    }
  },
  methods:{
    qnaAdd(){
      if(!this.category){
        alert("문의유형을 선택해주세요")
      }
      else if(!this.title){
        alert("문의 제목을 입력해주세요")
      }
      else if(!this.content){
        alert("문의 내용을 입력해주세요")
      }
      else{
        axios.post('/v3/register', {
          category: this.category,
          writer:this.writer,
          title: this.title,
          content : this.content
        })
          alert('문의글이 추가되었습니다.')
        // setTimeout(function() {
          this.$router.push('/qnaLayout');
        // }, 1000);
      }
    }
  }
};

</script>

<style scoped>

.py-2 {
    width: 60%;
    margin: auto;
    background-color: white;
}

.info {
    font-size: 30pt;
    color: #663C2A;
}

.info:hover {
    color: #A36043;
    transition: 0.3s;
}

.info-text {
  font-size: 12pt;
  color: #4F2E20;
  display: block;
}

.outterDiv{
    width: 60%;
    margin: auto;
    background-color: white;
    padding: 0px 20px;
}

.notice-context{
  width: 100%;
  text-align: left;
}

.table-category td {
  padding: 20px;
}

 .table-title td, .table-context td{
   padding: 5px 10px;
 }

.table-category th, .table-title th, .table-context th {
  font-weight: bold;
  color: #4F2E20;
  border-right: 1px solid #ddd;
  text-align: center;
}

.table-category{
  border-top: 2px solid #4F2E20 ;
  border-bottom: 2px solid #4F2E20 ;
}

.table-category input{
  margin-left: 15px;
}

.table-category form :first-child{
  margin-left: 0px;
}

.table-context{
  height: 300px;
  border-bottom: 1px solid  #ddd;
}

.text-area-title{
  width: 100%;
  border-color: 1px solid #4F2E20;
}

.text-area-title:focus{
  border-color: 2px solid #4F2E20;
}

.text-area-content{
  width: 100%;
  height: 300px;
  border-color: 1px solid #4F2E20;
}

.text-area-content:focus{
  border-color: 2px solid #4F2E20;
}

.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}

button{
  margin-right: 5px;
  background-color: #663C2A;
  border-color: #663C2A;
}

button:hover{
  background-color: #A36043;
  border-color: #A36043;
}
</style>