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
        <button class="btn btn-success" @click="$router.push('/qnaLayout')"> 목록 </button>
      </div>
      <hr>

      <!-- 답변   -->
      <div class="answer">
        <h5 style="text-align: left"> 답변 </h5>
        <table>
          <tr>
<!--            <td v-for="content in this.$store.state.qnaBoardDetail.replyList" :key="content.qnaReplyNo">-->
<!--              {{content.content}}-->
<!--            </td>-->
            <td>
              {{this.$store.state.qnaBoardDetail.replyList[0].content}}
            </td>
          </tr>
        </table>
        <div class="answer-row">
          <textarea style="width: 100%" v-model="content"></textarea>
          <button @click="replyAdd">답변등록</button><hr>
        </div>
        <div class="answer-row">
          <button @click="replyupdate(this.$store.state.qnaBoardDetail.replyList[0].qnaReplyNo)">답변수정</button>
          <button @click="replyDelete(this.$store.state.qnaBoardDetail.replyList[0].qnaReplyNo)">답변삭제</button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      content:'',
      replyer:'user3@springCoffee.com',  // 로그인ID으로 대체
      qnaBoardNo:this.$route.params.qnaBoardNo,
    }
  },
  created() {
    this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo);
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
    },
    replyAdd(){
      axios.post('/v4/register',{
        content:this.content,
        replyer:this.replyer,
        qnaBoardNo:this.qnaBoardNo
      })
    },
    replyupdate(qnaReplyNo){
      axios.put(`/v4/${qnaReplyNo}`,{
        content:this.content,
        replyer:this.replyer,
        qnaBoardNo:this.qnaBoardNo,
      })
    },
    replyDelete(qnaReplyNo){
      axios.delete(`/v4/${qnaReplyNo}`)
          .then(res =>{
            console.log((res.data))
            alert("문의글이 삭제되었습니다")
            this.$router.go(-1)
          })
    },
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
button{
  margin-right: 5px;
  margin-bottom: 5px;
}
</style>