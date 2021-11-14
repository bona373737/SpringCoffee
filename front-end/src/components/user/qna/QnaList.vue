<template>
  <div>
     <div class="outterDiv py-5">
      <div class="btnWrap text-end">
       <span class="filter">
         <span>category</span>
         <select v-model="category" @change="fetchQnaBoard()">
           <option v-for="option in categoryOption" :value="option.value" :key="option.value">{{option.title}}</option>
         </select>
          <span>Answered</span>
         <select v-model="isAnswered" @change="fetchQnaBoard()">
           <option value=""> 전체</option>
           <option value=false>답변대기</option>
           <option value=true>답변완료</option>
         </select>
<!--         <button @click="fetchQnaBoard()"> 조회하기 </button>-->
<!--         <button @click="qnaBoardFilterReset"> 필터초기화 </button>-->
       </span>
<!--        <select class="me-2" v-model="searchOption">-->
<!--          <option selected> 제목 </option>-->
<!--          <option> 작성자 </option>-->
<!--        </select>-->
<!--        <input type="text" class="me-2"  v-model="keyword">-->
<!--        <button class="btn btn-success me-2" @click="qnaBoardSearch(keyword)"> 검색 </button>-->
        <button class="btn success" @click="fetchMyQna(email)"> 내 Q&A만보기 </button>
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
              @click="goQnaDetail(qnaBoard.qnaBoardNo)">
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
         <button @click="movePage('prev')" :disabled="this.$store.state.qnaBoardList.prev">이전</button>
         <button v-for="page in this.$store.state.qnaBoardList.pageList" :key="page"
                 :class="{pageNo : page === this.$store.state.qnaBoardList.page}"
                 @click="fetchQnaBoard(page)">{{page}}</button>
         <button @click="movePage('next')" :disabled="this.$store.state.qnaBoardList.next">다음</button>
       </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'QnaList',
  created() {
    this.$store.dispatch('fetchQnaBoardList');
  },
  data() {
    return {
      category: '',
      isAnswered: '',
      searchOption: '',
      keyword: '',
      categoryOption: [
        {value: '', title: '전체'},
        {value: '상품문의', title: '상품문의'},
        {value: '배송문의', title: '배송문의'},
        {value: '교환및반품문의', title: '교환 및 반품문의'},
        {value: '기타', title: '기타'},
      ],
    }
  },
  methods: {
    goQnaDetail(qnaBoardNo) {
      this.$router.push({
        name: 'qnaDetail',
        params: {qnaBoardNo: qnaBoardNo}
      })
    },
    qnaBoardSearch(keyword) {
      this.$store.dispatch('fetchQnaBoardSearch', keyword);
    },
    movePage(type){
      // 이전, 다음 탭 눌렀을때 페이지 번호 계산용
      const prevPageNo = this.$store.state.qnaBoardList.start - 1;
      const nextPageNo = this.$store.state.qnaBoardList.end + 1;

      const page = type === 'prev' ? prevPageNo : nextPageNo;

      this.fetchQnaBoard(page);
    },
    fetchQnaBoard(page) {
      const paramObj = {
        page : page,
        category: this.category,
        isAnswered: this.isAnswered
      };
      this.$store.dispatch('fetchQnaBoard', paramObj);
    },
    fetchMyQna(email){
      this.$store.dispatch('fetchMyQna', email);
    }
  }
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
.filter{
  float: left;
}
</style>