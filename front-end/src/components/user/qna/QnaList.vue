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
           <option value=false>답변대기</option>
           <option value=true>답변완료</option>
         </select>
         <button @click="qnaFilterBoard(category,isAnswered)"> 조회하기 </button>
         <button @click="qnaBoardFilterReset"> 필터초기화 </button>
       </div>

      <div class="btnWrap text-end">
        <select class="me-2" v-model="searchOption">
          <option> 제목 </option>
          <option> 작성자 </option>
        </select>
        <input type="text" class="me-2" placeholder="제목,작성자" v-model="keyword">
        <button class="btn btn-success me-2" @click="qnaBoardSearch(keyword)"> 검색 </button>
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
          <tr class="tbody-th1" v-for="qnaBoard in this.$store.state.qnaBoardList.dtoList" :key="qnaBoard.qnaBoardNo"
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

       <div>
         <button>이전</button>

         <button v-for="page in this.$store.state.qnaBoardList.pageList" :key="page"
                 :class="{pageNo : page === this.$store.state.qnaBoardList.page}"
                 @click="movePage(page)">{{page}}</button>
         <button>다음</button>
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
      isAnswered:'',
      searchOption:'',
      keyword:'',
    }
  },
  methods: {
    goQnaDetail(qnaBoardNo) {
      this.$router.push({
        name: 'qnaDetail',
        params: { qnaBoardNo: qnaBoardNo }
      })
    },
    qnaFilterBoard(category,isAnswered){
      /*
      if(){
        this.$store.dispatch('fetchQnaFilterList1',category,isAnswered)
      }
      if else (isAnswered == null){
        this.$store.dispatch('fetchQnaFilterList2',category)
      }
      if else ( category,isAnswered == null){
        alert("필터조건을 선택해주세요")
      }
       */
      this.$store.dispatch('fetchQnaFilterList',category,isAnswered)

    },
    qnaBoardFilterReset(){
      this.$store.dispatch('fetchQnaBoardList')
    },
    qnaBoardSearch(keyword){
      this.$store.dispatch('fetchQnaBoardSearch',keyword)
    },
    movePage(page){
      this.$store.dispatch('fetchQnaBoardList',page)
    },
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
.pageNo{
  background: tomato;
}
</style>