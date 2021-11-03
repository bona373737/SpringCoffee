<template>
  <div>
    <p>{{$store.state.boardDetail.title}}</p>
    <p>{{$store.state.boardDetail.content}}</p>


    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="15%" />
          <col width="*" />
        </colgroup>
        <tr>
          <th>제목</th>
          <td class="table-title"><textarea v-model="title"> 공지글 제목 </textarea></td>
        
        </tr>
        <tr>
          <th> 내용 </th>
          <td class="table-context"><textarea v-model="content"> 공지글 상세내용</textarea></td>
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="noticeUpdate()"> 수정사항 등록 </button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      category: 'notice',
      writer: '',
      title : '',
      content: '',
    }
  },
  created() {
    this.$store.dispatch('fetchBoardDetail',this.$route.params.boardNo);
  },
  methods:{
    noticeUpdate(boardNo){
      axios.put(`/v1/${boardNo}`, {
        title: this.title,
        content : this.content
      });
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
textarea{
  width: 100%;
  min-height: 80%;
}
input{
  width: 100%;
}
</style>