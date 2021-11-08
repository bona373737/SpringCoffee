<template>
  <div>
     <div class="outterDiv py-5">

       <div class="filter">
         <span>filter</span>
         <select v-model="category">
           <option value="상품문의" >상품문의</option>
           <option value="배송문의" >배송문의</option>
           <option value="교환 및 반품문의" >교환및반품문의</option>
           <option value="기타" >기타</option>
         </select>
         <select v-model="isAnswered">
           <option value=0>답변대기</option>
           <option value=1>답변완료</option>
         </select>
         <button @click="qnaFilterList(this.category,this.isAnswered)"> 조회하기 </button>
       </div>

      <div class="btnWrap text-end">
        <input type="text" class="me-2" placeholder="제목,작성자">
        <button class="btn btn-success me-2" > 검색 </button>
        <button class="btn btn-primary" @click="$router.push('/qnaAdd')"> 문의하기 </button>
      </div>

      <div>
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col width="5%" />
            <col width="15%" />
            <col width="40%" />
            <col width="15%" />
            <col width="15%" />
            <col width="15%" />
          </colgroup>
          <thead>
          <tr>
            <th>No.</th>
            <th>Q&A유형</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>답변상태</th>
<!--<th>조회수</th>-->
          </tr>
          </thead>
          <tbody>
          <tr class="tbody-th1" v-for="qnaBoard in this.$store.state.qnaBoardList" :key="qnaBoard.qnaBoardNo"
              @click="goQnaDetail(qnaBoard.qnaBoardNo)" >
            <th>{{ qnaBoard.qnaBoardNo }}</th>
            <th>{{ qnaBoard.category }}</th>
            <th>{{qnaBoard.title }}</th>
            <th>{{ qnaBoard.writer }}</th>
            <th>{{ qnaBoard.regDate }}</th>
            <th><span v-if="qnaBoard.isAnswered == 1">답변완료</span>
                <span v-if="qnaBoard.isAnswered == 0">답변대기</span>
            </th>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'QnaList',
  created() {
    this.$store.dispatch('fetchQnaBoardList')
  },
  data(){
    return{
      category:'',
      isAnswered:Boolean,
    }
  },
  methods: {
    goQnaDetail(qnaBoardNo) {
      this.$router.push({
        name: 'qnaDetail',
        params: { qnaBoardNo: qnaBoardNo }
      })
    },
    qnaFilterList(category, isAnswered){
      console.log(category)
      this.$store.dispatch('fetchQnaBoardList',category, isAnswered)
    }
  },
};
</script>

<style scoped>
.outterDiv{
  width: 80%;
  margin: auto;
}
.btnWrap{
  margin: 6px;
}
.outterDiv notice-head{
  margin-right: 80%;
}
.tbody-th1{
  border-bottom: 1px solid  #ddd;
  border-left: 1px solid white;
  border-right: 1px solid white;
}

.tbody-th1:hover {
  cursor: pointer;
  color: green;
  transition: 0.3s;
}
th{
  border-left: 1px solid white;
  border-right: 1px solid white;
}
</style>