<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="10%" />
          <col width="*" />
        </colgroup>
        <tr>
          <th>카테코리</th>
          <td>
            <form>
              <input type="radio" name="categoryOpt"> notice
              <input type="radio" name="categoryOpt"> event
            </form>
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td class="table-title"><textarea class="text-area-title" v-model="title"> </textarea></td>
        </tr>
        <tr>
          <th> 내용 </th>
          <td class="table-context"><textarea class="text-area-content" v-model="content"> </textarea></td>
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
      boardNo: '',
      title : this.$store.state.boardDetail.title,
      content: this.$store.state.boardDetail.content,
    }
  },
  created() {
    this.$store.dispatch('fetchBoardDetail',this.$route.params.boardNo);
  },
  methods:{
    noticeUpdate(boardNo){
      axios.put(`/v1/${boardNo}`, {
        boardNo:this.$route.params.boardNo,
        title: this.title,
        content : this.content,
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
  padding: 5px 5px 0px 5px
}
.table-context{
  padding: 0px 5px 5px 5px;
  height: 300px;
  border-bottom: 1px solid  #ddd;
}
.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}
.text-area-title{
  width: 100%;
}
.text-area-content{
  width: 100%;
  min-height: 80%;
}
</style>