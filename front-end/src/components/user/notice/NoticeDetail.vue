<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <tr><td class="table-title">제목 {{$store.state.boardDetail.title}} </td></tr>
        <tr><td class="table-context"> {{$store.state.boardDetail.content}} </td></tr>
        <tr><td class=""> 작성일 </td></tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="noticeDelete(this.$route.params.boardNo)"> 삭제 </button>
        <button class="btn btn-success" @click="goNoticeUpdate(this.$route.params.boardNo)"> 수정 </button>
        <button class="btn btn-success" @click="$router.push({name : 'noticeList'})"> 목록으로 가기 </button>
      </div>
      <br>
      <table class="updown-page">
      <colgroup>
        <col style="width: 25%" />
        <col style="width: 85%" />
      </colgroup>
      <tr class="goToUpperList">
        <th style="background-color:#a9a9a92e"> 윗글 </th>
        <td style="padding: 8px" @click="$route.push('')" > 글 제목 </td>
      </tr>
      <tr>
        <th style="background-color:#a9a9a92e">  아랫글 </th>
        <td style="padding: 8px" > 글 제목 </td>
      </tr>
      </table>

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
    this.$store.dispatch('fetchBoardDetail',this.$route.params.boardNo); // board_no -> 글번호에 해당하는 내용을 가져와라!
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
  border-bottom: 1px solid  #ddd;;
}
.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}
.updown-page{
  width: 100%;
  border-top: 1px solid #444444;
  border-bottom: 1px solid #444444 ;
  text-align: left;

}
.goToUpperList{
  border-bottom: 1px solid  #ddd;
  text-align: left;
}
</style>