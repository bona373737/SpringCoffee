<template>
  <div class="outer">
    <div class="py-2">
        <div style="height: 48px;"/>
        <span class="info"><i class="bi bi-question-circle-fill"></i></span><br>
        <span class="info-text">궁금한 것이 있으면 언제든 문의해주세요!</span>
    </div>
    <div class="outterDiv py-5">
      <table class="qna-context">
        <colgroup>
            <col width="20%" />
            <col width="80%" />
          </colgroup>
        <tr class="table-title">
          <td>제목</td>
          <td>{{$store.state.qnaBoardDetail.title}} </td></tr>
        <tr class="table-context">
          <td>문의종류</td>
          <td> {{$store.state.qnaBoardDetail.content}} </td>
        </tr>
      </table>
      <br>

      <div class="BtnWrap">
        <button class="btn btn-success" @click="qnaDelete(this.$route.params.qnaBoardNo)"
                v-show="this.$store.state.qnaBoardDetail.writer === this.$store.state.email"> 삭제 </button>
        <button class="btn btn-success" @click="goQnaUpdate(this.$route.params.qnaBoardNo)"
                v-show="this.$store.state.qnaBoardDetail.writer === this.$store.state.email"> 수정 </button>
        <button class="btn btn-success" @click="$router.push('/qnaLayout')"> 목록 </button>
      </div>
      
      <hr>

      <!-- 답변   -->
      <div class="answer">
        <h5>&gt; 답변</h5>
            <div class="answerDiv" v-show="this.$store.state.role !=='ROLE_ADMIN'"
                 v-for="answer in this.$store.state.qnaBoardDetail.replyList" :key="answer.qnaReplyNo">
              {{answer.content}}
            </div>

          <section v-show="this.$store.state.role ==='ROLE_ADMIN'">
            <div v-show="this.$store.state.qnaBoardDetail.replyList.length < 1">
              <div class="answer-row" >
                <textarea v-model="content" ></textarea>
                <button  class="btn btn-outline-success btn-sm updateBtn" @click="replyAdd">등록</button>
              </div>
            </div>

            <div class="answer-row" v-show="this.$store.state.qnaBoardDetail.replyList.length > 0">
              <div v-if="!isReply" >
                <div class="answerDiv" v-for="answer in this.$store.state.qnaBoardDetail.replyList" :key="answer.qnaReplyNo">
                  {{answer.content}}
                </div>
                <div class="answerBtn">
                <button class="btn btn-outline-success btn-sm" @click="updateReply()">수정</button>
                <div>
                <button class="btn btn-outline-success btn-sm" @click="replyDelete(this.$store.state.qnaBoardDetail.replyList[0].qnaReplyNo)">삭제</button>
                </div>
                </div>
              </div>

              <div v-if="isReply" >
                <textarea v-model="content"></textarea>
                <button class="btn btn-outline-success btn-sm" @click="replyupdate(this.$store.state.qnaBoardDetail.replyList[0].qnaReplyNo)">수정</button>
              </div>
            </div>
          </section>

        </div>
      </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      isReply: false,
      content:'',
      qnaBoardNo:this.$route.params.qnaBoardNo,
      replyer:this.$store.state.email,
    }
  },
  created() {
    this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo);
  },
  methods:{
    updateReply() {
      this.isReply=true;
    },
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
            // const status =
            //     JSON.parse(res.data.response.status);
            // if (status == '200') {
            //   this.$router.push('/qnaLayout');
            // }
            this.$router.push('/qnaLayout');
          })
    },
    replyAdd(){
      axios.post('/v4/register',{
        content:this.content,
        qnaBoardNo:this.qnaBoardNo,
        replyer:this.replyer,
      })
      .then(res =>{
        console.log(res.data)
        alert("답변이 추가되었습니다.")
        this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo)
      })
    },
    replyupdate(qnaReplyNo){
      axios.put(`/v4/${qnaReplyNo}`,{
        content:this.content,
        replyer:this.replyer,
        qnaBoardNo:this.qnaBoardNo,
        qnaReplyNo:qnaReplyNo
      })
      .then(res =>{
        console.log(res.data)
        this.isReply=false;
        alert("답변이 수정되었습니다.")
        this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo)
      })
    },
    replyDelete(qnaReplyNo){
      axios.delete(`/v4/${qnaReplyNo}`)
          .then(res =>{
            console.log((res.data))
            alert("답변이 삭제되었습니다")
            this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo)
          })
    },
  }
};
</script>

<style scoped>
.updateBtn{
  vertical-align: middle ;
  display: table-cell
}

.outer {
  background-color: #f7f7f7;
}

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

.qna-context{
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

.BtnWrap{
  margin: 5px 0px;
}

.BtnWrap button{
  background-color: #663C2A;
  border-color: #663C2A;
}

.BtnWrap button:hover{
  background-color: #A36043;
  border-color: #A36043;
}

.answer h5 {
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
  color: #4F2E20;
}

textarea{
  width: 80%;
  text-indent: 1rem;
  min-height: 100px;
  margin-right: 15px;
  margin-top: 20px;
  border-radius: 8px;
  border-color: #663C2A;
  vertical-align: middle ;
}

/*.answerEdit{*/
/*  !*margin: auto;*!*/
/*  vertical-align: middle;*/
/*  display: block;*/
/*}*/
.answerDiv{
  text-indent: 1rem;
  width:80% ;
  text-align: left;
  margin-top: 20px;
  min-height: 100px;
  border : 1px solid #663C2A;
  border-radius: 8px;
  vertical-align: middle ;
  display: inline-block;

}

.answerBtn{
  vertical-align: middle;
  margin-left: 15px;
  margin-top: 25px;
  /*margin-bottom: 10px;*/
  display: inline-block;


}

/* 여긴 수정필요 */
/*.answer-row .textarea{*/
/*  position: absolute;*/
/*  top:50%;*/
/*  vertical-align: middle;*/
/*  display: table;*/
/*}*/

button{
  margin-right: 5px;
  margin-bottom: 5px;
}

.btn-outline-success{
  margin-right: 5px;
  color: #4F2E20;
  border: 2px solid #4F2E20;
}

.btn-outline-success:hover {
  border: 2px solid #A36043;
  background: #A36043;
  color: white;

}


</style>