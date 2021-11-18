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
            <col width="80%" />
          </colgroup>
        <tr class="table-title">
          <td>제목</td>
          <td> {{$store.state.noticeBoardDetail.title}} </td>
        </tr>
        <tr class="table-context">
          <td>내용</td>
          <td> {{$store.state.noticeBoardDetail.content}} </td>
        </tr>
      </table>
      
      <br>

      <div class="BtnWrap">
        <button class="btn btn-success" v-show="this.$store.state.role ==='ROLE_ADMIN'"
                @click="noticeDelete(this.$route.params.boardNo)"> 삭제 </button>
        <button class="btn btn-success" v-show="this.$store.state.role ==='ROLE_ADMIN'"
                @click="goNoticeUpdate(this.$route.params.boardNo)"> 수정 </button>
        <button class="btn btn-success" @click="$router.push('/noticeLayout')"> 목록 </button>
      </div>

      <br>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{

    }
  },
  created() {
    this.$store.dispatch('fetchNoticeBoardDetail',this.$route.params.boardNo); // board_no -> 글번호에 해당하는 내용을 가져와라!
  },
  methods:{
    goNoticeUpdate(boardNo){
      this.$router.push({
        name: 'noticeUpdate',
        params: { boardNo: boardNo }
      })
    },
    noticeDelete(boardNo){
      axios.delete(`/v1/${boardNo}`)
          .then( res => {
            console.log(res.data)
            alert("공지사항 게시글이 삭제 되었습니다.")
            this.$router.push({name : 'noticeList'});
          })
    }
  }
};

</script>

<style scoped>
.outterDiv{
  width: 60%;
  margin: auto;  
  background-color: white;
  padding: 0px 20px;
}

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

.info i{
  margin-top: 48px;
}

.notice-context{
  width: 100%;
  text-align: left;
}

.table-title{
  border-top: 2px solid #4F2E20 ;
  border-bottom: 2px solid #4F2E20 ;
}

.table-title td, .table-context td {
  padding: 20px;
}

.table-title :first-child, .table-context :first-child{
  font-weight: bold;
  color: #4F2E20;
  border-right: 1px solid #ddd;
  text-align: center;
}

.table-context{
  padding: 10px;
  height: 300px;
  border-bottom: 1px solid  #ddd;;
}

.table-context{
  padding: 10px;
  height: 300px;
  border-bottom: 1px solid  #ddd;;
}

.BtnWrap{
  margin: 5px 0px;
}

.BtnWrap button{
  margin-right: 5px;
  background-color: #663C2A;
  border-color: #663C2A;
}

.BtnWrap button:hover{
  background-color: #A36043;
  border-color: #A36043;
}

</style>