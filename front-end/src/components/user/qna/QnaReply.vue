<template>
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
</template>

<script>
import axios from "axios";

export default {
  methods:{
    replyDelete(qnaReplyNo){
      axios.delete(`/v4/${qnaReplyNo}`)
          .then(res =>{
            console.log((res.data))
            alert("문의글이 삭제되었습니다")
            this.$router.go(-1)
          })
    },
  }
}
</script>