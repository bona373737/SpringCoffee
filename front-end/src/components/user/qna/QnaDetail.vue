<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <tr><td class="table-title"> {{$store.state.qnaBoardDetail.title}} </td></tr>
        <tr><td class="table-context"> {{$store.state.qnaBoardDetail.content}} </td></tr>
      </table>
      <br>

      <div class="BtnWrap">
        <button class="btn btn-success" @click="qnaDelete(this.$route.params.qnaBoardNo)"> 삭제 </button>
        <button class="btn btn-success" @click="goQnaUpdate(this.$route.params.qnaBoardNo)"> 수정 </button>
        <button class="btn btn-success" @click="$router.push({name : 'noticeList'})"> 목록으로 가기 </button>
      </div>

      <div class="answer" >
        <p>{{$store.state.qnaBoardDetail.replyList.content}}</p>
      </div>
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
    this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo); // board_no -> 글번호에 해당하는 내용을 가져와라!
  },
  methods:{
    goQnaUpdate(qnaBoardNo){
      this.$router.push({
        name: 'qnaUpdate',
        params: { qnaBoardNo: qnaBoardNo }
      })
    },
    qnaDelete(qnaBoardNo){
      axios.delete(`/v3/${qnaBoardNo}`)
          .then( res => {
            console.log(res.data)
            alert("문의글이 삭제 되었습니다.")
            this.$router.push({name : 'qnaList'});
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
.answer{
  background: darkgray;
}
</style>