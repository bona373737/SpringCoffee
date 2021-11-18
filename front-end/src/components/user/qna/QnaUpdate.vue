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
          <td>{{this.$store.state.qnaBoardDetail.category}}</td>
        </tr>
        <tr class="table-title">
          <th>제목</th>
          <td class="table-title"><input class="text-area-title" v-model="title"/></td>
        </tr>
        <tr class="table-context">
          <th> 내용 </th>
          <td><textarea class="text-area-content" v-model="content"> </textarea></td>
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="noticeUpdate()"> 수정 </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      qnaBoardNo: '',
      category:this.$store.state.qnaBoardDetail.category,
      title : this.$store.state.qnaBoardDetail.title,
      content: this.$store.state.qnaBoardDetail.content,
    }
  },
  created() {
    this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo);
  },
  methods:{
    noticeUpdate(qnaBoardNo){
      axios.put(`/v3/${qnaBoardNo}`, {
        qnaBoardNo:this.$route.params.qnaBoardNo,
        category:this.category,
        title: this.title,
        content : this.content,
      });
      alert("문의사항이 수정되었습니다");
      this.$router.push('/qnaLayout');
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
  color: #4F2E20;
  font-size: 12pt;
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

.table-category th, .table-title th, .table-context th {
  font-weight: bold;
  color: #4F2E20;
  border-right: 1px solid #ddd;
  text-align: center;
}

.table-category td {
  padding: 20px;
}

 .table-title td, .table-context td{
   padding: 5px 10px;
 }

.table-category{
  border-top: 2px solid #4F2E20 ;
  border-bottom: 2px solid #4F2E20 ;
}

/* 
.table-title{
  padding: 5px 5px 0px 5px
}
*/

.table-context{
  padding: 0px 5px 5px 5px;
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