<template>
  <div>
     <div class="outterDiv py-5">
      
      <div class="py-2">
            <span class="info"><i class="bi bi-info-circle-fill"></i></span><br>
            <span class="info-text">Spring Coffee의 새로운 소식을 접해보세요!</span>
      </div>

      <div class="btnWrap text-end">
        <span class="filter">
         <select v-model="category" @change="movePage()">
           <option v-for="option in categoryOption" :value="option.value" :key="option.value">{{option.title}}</option>
         </select>
       </span>
       <span class="search">
        <input class="me-4" type="text" placeholder="제목" v-model="keyword">
        <button class="btn success" @click="noticeBoardSearch(keyword)" > 검색 </button>
        <button class="btn btn-success" @click="$router.push('/noticeAdd')" v-show="this.$store.state.role == 'ROLE_ADMIN'"> 추가 </button>
       </span>
      </div>

      <div class="notice-list">
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col width="10%" />
            <col width="50%" />
            <col width="15%" />
            <col width="15%" />
          </colgroup>
          <thead>
          <tr>
            <th>No.</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
<!--        <th>조회수</th>-->
          </tr>
          </thead>
          <tbody>
          <tr class="tbody-th1" v-for="board in this.$store.state.noticeBoardList.dtoList" v-bind:key="board.boardNo"
              @click="goNoticeDetail(board.boardNo)">
            <th>{{ board.boardNo }}</th>
            <th>{{ board.title }}</th>
            <th>{{ board.writer }}</th>
            <th>{{ board.modDate }}</th>
          </tr>
          </tbody>
        </table>
      </div>

      <div class="page-btn">
        <button class="btn btn-outline-secondary btn-sm" @click="movePrevPage()">이전</button>
        <button class="btn btn-outline-secondary btn-sm btn-page" v-for="page in this.$store.state.noticeBoardList.pageList" :key="page"
                :class="{pageNo : page === this.$store.state.noticeBoardList.page}"
                @click="movePage(page)">{{page}}</button>
        <button class="btn btn-outline-secondary btn-sm" @click="moveNextPage()">다음</button>
      </div>

    </div>
  </div>
</template>

<script>

export default {
  name:'NoticeList',
  created() {
    this.$store.dispatch('fetchNoticeBoardList');
  },
  data() {
    return {
      category:'',
      keyword:'',
      categoryOption: [
        {value: '', title: '전체'},
        {value: '공지', title: '공지'},
        {value: '이벤트', title: '이벤트'},
      ],
    }
  },
  methods: {
    goNoticeDetail(boardNo) {
      this.$router.push({
        name: 'noticeDetail',
        params: { boardNo: boardNo }
      })
    },
    movePage(page){
      this.$store.dispatch('fetchNoticeBoardList',page)
    },
    movePrevPage(){
      if(this.$store.state.noticeBoardList.prev == true){
        const prevPage = this.$store.state.noticeBoardList.start -1
        this.$store.dispatch('fetchNoticeBoardList',prevPage)
      }
    },
    moveNextPage(){
      if(this.$store.state.noticeBoardList.next == true){
      const nextPage = this.$store.state.noticeBoardList.end +1
      this.$store.dispatch('fetchNoticeBoardList',nextPage)
      }
    },
    noticeBoardSearch(keyword){
      this.$store.dispatch('fetchNoticeBoardSearch',keyword)
    },
    noticeBoardCategory(page) {
      const paramObj = {
        page : page,
        category: this.category
      };
      this.$store.dispatch('fetchNoticeBoardCategory', paramObj);
    },
  }
};
</script>

<style scoped>
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

.outterDiv{
  width: 80%;
  margin: auto;
  background-color: white;
}

.outterDiv notice-head{
  margin-right: 80%;
}

.btnWrap{
  margin: 6px;
}

.filter{
  float: left;
  padding-top: 3px;
  margin-left: 20px;
}

.filter select {
  margin: 2px 10px 0px 10px;
  height: 30px;
  border-color: #4F2E20;
  border-radius: 5px;
  color: #4F2E20;
}

.search {
  margin-right: 15px;
}

.search .me-4 {
  height: 30px;
  padding: 3px 5px 3px;
  margin: 5px 10px 2px;
  border:1px solid #4F2E20;
  border-radius: 5px;
}

.search .success{
  margin-right: 15px;
  color: #4F2E20;
  border: 2px solid #4F2E20;
}

.search .success:hover{
  border: 2px solid #A36043;
}

.search .btn-success {
  background: #663C2A;
  border: 2px #663C2A;
  height: 40px;
}

.search .btn-success:hover{
  background: #4F2E20;
  border: 2px #4F2E20;
  height: 40px;
}

.notice-list{
  margin: 5px 10px;
}

.notice-list thead, .notice-list tbody {
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