<template>
  <div>
     <div class="outterDiv py-5">

        <div class="py-2">
            <span class="info"><i class="bi bi-question-circle-fill"></i></span><br>
            <span class="info-text">궁금한 것이 있으면 언제든 문의해주세요!</span>
        </div>

      <div class="btnWrap text-end">
       <span class="filter">
         <span>문의종류</span>
         <select v-model="category" @change="fetchQnaBoard()">
           <option v-for="option in categoryOption" :value="option.value" :key="option.value">{{option.title}}</option>
         </select>
          <span>답변상태</span>
         <select v-model="isAnswered" @change="fetchQnaBoard()">
           <option value=""> 전체</option>
           <option value=false>답변대기</option>
           <option value=true>답변완료</option>
         </select>
       </span>
       <span class="btns">
        <button class="btn success" @click="fetchMyQna(this.$store.state.email)" v-show="this.$store.state.isLogin == true"> 내 Q&A만보기 </button>
        <button class="btn btn-success" @click="addBtn()"> 문의하기 </button>
        </span>
      </div>

      <div class="qna-list">
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col width="10%" />
            <col width="10%" />
            <col width="40%" />
            <col width="15%" />
            <col width="15%" />
            <col width="15%" />
          </colgroup>
          <thead>
          <tr>
            <th>No.</th>
            <th>문의종류</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>답변상태</th>
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

       <div class="page-btn">
         <button class="btn btn-outline-secondary btn-sm" @click="movePage('prev')" :disabled="!this.$store.state.qnaBoardList.prev">&lt;&lt;</button>
         <button class="btn btn-outline-secondary btn-sm btn-page" v-for="page in this.$store.state.qnaBoardList.pageList" :key="page"
                 :class="{pageNo : page === this.$store.state.qnaBoardList.page}"
                 @click="fetchQnaBoard(page)">{{page}}</button>
         <button class="btn btn-outline-secondary btn-sm" @click="movePage('next')" :disabled="!this.$store.state.qnaBoardList.next">&gt;&gt;</button>
       </div>

    </div>
  </div>
</template>


<script>
export default {
  name: 'QnaList',
  mounted() {
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
      if(this.$store.state.isLogin){
        this.$store.dispatch('fetchMyQna', email);
      }
      else{
        alert("로그인 해주세요");
      }
    },
    addBtn(){
      if(this.$store.state.isLogin){
        this.$router.push('/qnaAdd')
      }
      else{
        alert("로그인 후 문의등록이 가능합니다");
        this.$router.push('/login')
      }
    }
  }
};
</script>

<style scoped>
.outterDiv{
  width: 80%;
  margin: auto;
  background-color: white;
}
.btnWrap{
  margin: 6px;
}
.outterDiv notice-head{
  margin-right: 80%;
}

.py-2 {
    width: 80%;
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
  padding-bottom: 50px;
}

.qna-list{
  margin: 5px 10px;
}

.qna-list thead, .qna-list tbody {
  border-color: #4F2E20;
}

.tbody-th1{
  border-bottom: 1px solid  #ddd;
  border-left: 1px solid white;
  border-right: 1px solid white;
}

.tbody-th1:hover {
  cursor: pointer;
  color: #A36043;
  transition: 0.3s;
}

th{
  border-left: 1px solid white;
  border-right: 1px solid white;
}

.filter{
  float: left;
  padding-top: 3px;
  margin-left: 20px;
}

.filter span {
  margin-right: 5x auto;
}

.filter select {
  margin: 2px 10px 0px 10px;
  height: 30px;
  border-color: #4F2E20;
  border-radius: 5px;
  color: #4F2E20;
}

.btns {
  margin-right: 15px;
}

.btns .success{
  margin-right: 15px;
  color: #4F2E20;
  border: 2px solid #4F2E20;
}

.btns .success:hover{
  border: 2px solid #A36043;
}

.btns .btn-success {
  background: #663C2A;
  border: 2px #663C2A;
  height: 40px;
}

.btns .btn-success:hover{
  background: #4F2E20;
  border: 2px #4F2E20;
  height: 40px;
}

.page-btn{
  padding: 20px;
}

.page-btn .btn-sm {
  width: 40px;
  height: 35px;
}

.page-btn :first-child, .page-btn :last-child{
  width: 50px;
  border: 2px solid #663C2A;
  color: #663C2A;
  font-weight: bold;
  margin: 5px 5px;
}

.page-btn :first-child:hover, .page-btn :last-child:hover{
  border: 2px solid #663C2A;
  background-color: #663C2A;
  color: white;
}

.page-btn .btn-page{
  border: none;
}

.page-btn .btn-page:hover{
  background: #A36043;
  color: white;
}

.pageNo{
  background: #663C2A;
  color: white;
  font-weight: bold;
}

</style>