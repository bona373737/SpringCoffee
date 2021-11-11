<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <tr><td class="table-title"> {{$store.state.qnaBoardDetail.title}} </td></tr>
        <tr><td class="table-context"> {{$store.state.qnaBoardDetail.content}} </td></tr>
        <tr>
          <td class="table-context" v-for="content in this.$store.state.qnaBoardDetail.replyList" :key="content.qnaReplyNo">
            {{content.content}}
          </td>
        </tr>
      </table>
      <br>

      <div class="BtnWrap">
        <button class="btn btn-success" @click="qnaDelete(this.$route.params.qnaBoardNo)"> 삭제 </button>
        <button class="btn btn-success" @click="goQnaUpdate(this.$route.params.qnaBoardNo)"> 수정 </button>
        <button class="btn btn-success" @click="$router.push('/qnaLayout')"> 목록 </button>
      </div>
      <hr>

      <div class="answer">
        <div class="answer-row">
          <textarea style="width: 100%" v-model="content" ></textarea>
          <button @click="replyAdd()">답변등록</button><hr>
        </div>
        <div class="answer-row">
          <p>{{$store.state.qnaBoardDetail.replyList.content}}</p>
          <button>답변수정</button>
          <button @click="replyDelete(this.$store.state.qnaBoardDetail.replyList.qnaReplyNo)">답변삭제</button>
        </div>
      </div>
    <button @click="test">test Btn</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";


export default {
  components: {

  },
  data(){
    return{
      content:'',
      replyer:'user3@springCoffee.com',
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
        replyer:this.replyer,    //email정보들어가야 하는......
      })
    },

    replyDelete(qnaReplyNo){
      console.log(qnaReplyNo)
      axios.delete(`/v4/${qnaReplyNo}`)
        .then(res =>{
          console.log((res.data))
          alert("문의글이 삭제되었습니다")
          this.$router.go(-1)
        })
    },
    test(){
      console.log(this.$store.state.qnaBoardDetail.replyList[0].content)
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
.answer{
  background: lightsteelblue;
}
button{
  margin-right: 5px;
  margin-bottom: 5px;
}
.answer-row{
  margin: 10px;
}
</style>