<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="15%" />
          <col width="*" />
        </colgroup>
        <tr>
          <th>제목</th>
          <td class="table-title"><input type="text" placeholder="공지글 제목" v-model="title"></td>
        </tr>
        <tr>
          <th>내용</th>
          <td class="table-context"><textarea v-model="content"> 공지글 상세내용</textarea></td>
        </tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="noticeAdd"> 추가 </button>
        <button class="btn btn-success"> 목록으로 가기 </button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data(){
    return{
      category: 'notice',
      title : '',
      content : '',
      writer: 'bona'  // TODO 로그인한 사용자 이메일로 대체하기
    }
  },
  methods:{
    noticeAdd(){
      axios.post('/v1/register', {
        category: this.category,
        writer:this.writer,
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
  border-bottom: 1px solid  #ddd;
}
.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}

</style>