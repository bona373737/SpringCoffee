<template>
  <div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <colgroup>
          <col width="15%" />
          <col width="*" />
        </colgroup>
<!--        <tr>-->
<!--          <th>카테고리</th>-->
<!--          <td class="table-title">-->
<!--            <form>-->
<!--              <input type="radio" name="categoryOpt" v-model="category" value="상품문의"> 상품문의-->
<!--              <input type="radio" name="categoryOpt" v-model="category" value="배송문의"> 배송문의-->
<!--              <input type="radio" name="categoryOpt" v-model="category" value="교환 및 반품문의"> 교환 및 반품문의-->
<!--              <input type="radio" name="categoryOpt" v-model="category" value="기타"> 기타-->
<!--            </form>-->
<!--          </td>-->
<!--        </tr>-->
        <tr>
          <th>문의유형</th>
          <td class="table-title">{{this.$store.state.qnaBoardDetail.category}}</td>
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
      qnaBoardNo: '',
      category:this.$store.state.qnaBoardDetail.category,
      title : this.$store.state.qnaBoardDetail.title,
      content: this.$store.state.qnaBoardDetail.content,
    }
  },
  created() {
    this.$store.dispatch('fetchQnaBoardDetail',this.$route.params.qnaBoardNo);
  },
  methods:{
    noticeUpdate(qnaBoardNo){
      axios.put(`/v3/${qnaBoardNo}`, {
        qnaBoardNo:this.$route.params.qnaBoardNo,
        category:this.category,
        title: this.title,
        content : this.content,
      });
      this.$router.push('/qnaLayout');
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