<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="10%" />
          <col width="*" />
        </colgroup>
        <tr>
          <th>카테고리</th>
          <td >
            <form>
              <input type="radio" name="categoryOpt" v-model="category"> notice
              <input type="radio" name="categoryOpt" v-model="category"> event
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
        <button class="btn btn-success" @click="noticeAdd"> 추가 </button>
        <button class="btn btn-success" @click="$router.push('/noticeLayout')"> 목록 </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data(){
    return{
      category:'',
      writer: this.$store.state.email,    // 로그인한 사용자 이메일
      title : '',
      content : '',
    }
  },
  methods:{
    noticeAdd(){
      if(!this.title){
        alert("공지글 제목을 입력해주세요.")
      }
      else if(!this.content){
        alert("공지글 내용을 입력해주세요.")
      }
      else if(!this.category){
        alert("공지글 카테고리를 선택해주세요.")
      }
      else {
        axios.post('/v1/register', {
          category: this.category,
          writer:this.writer,
          title: this.title,
          content : this.content
        });
        alert("공지글이 추가되었습니다")
        this.$router.push('/noticeLayout')
      }
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
  padding: 5px 5px 0px 5px;
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
button{
  margin-right: 5px;
}
.text-area-title{
  width: 100%;
}
.text-area-content{
  width: 100%;
  min-height: 80%;
}

</style>