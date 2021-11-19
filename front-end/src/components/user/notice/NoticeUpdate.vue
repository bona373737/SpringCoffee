<template>
  <div>
    
    <div class="py-2">
            <div style="height: 48px;"/>
            <span class="info"><i class="bi bi-info-circle-fill"></i></span><br>
            <span class="info-text">Spring Coffee의 새로운 소식을 접해보세요!</span>
    </div>

    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="20%" />
          <col width="*" />
        </colgroup>
        <tr class="table-category">
          <th>카테고리</th>
          <td>{{this.$store.state.noticeBoardDetail.category}}</td>
        </tr>
        <tr class="table-title">
          <th>제목</th>
          <td><input class="text-area-title" v-model="title"/></td>
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
      boardNo: '',
      title : this.$store.state.noticeBoardDetail.title,
      content: this.$store.state.noticeBoardDetail.content,
    }
  },
  created() {
    this.$store.dispatch('fetchNoticeBoardDetail',this.$route.params.boardNo);
  },
  methods:{
    noticeUpdate(boardNo){
      axios.put(`/v1/${boardNo}`, {
        boardNo:this.$route.params.boardNo,
        title: this.title,
        content : this.content,
      });
      this.$router.push('/noticeLayout')
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