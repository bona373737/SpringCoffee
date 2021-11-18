<template>
  <div>
     <div class="outterDiv py-5">
      <div class="btnWrap text-end">
        <span class="filter">
         <select v-model="category" @change="movePage()">
           <option v-for="option in categoryOption" :value="option.value" :key="option.value">{{option.title}}</option>
         </select>
       </span>
       <span class="search">
        <input type="text" class="me-2" placeholder="제목" v-model="keyword">
        <button class="btn btn-success me-2" @click="noticeBoardSearch(keyword)" > 검색 </button>
        <button class="btn btn-primary" @click="$router.push('/noticeAdd')" v-show="this.$store.state.role == 'ROLE_ADMIN'"> 추가 </button>
       </span>
      </div>
      <div>
        <table class="table table-bordered table-condensed">
          <colgroup>
            <col width="5%" />
            <col width="55%" />
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
       <button class="btn btn-outline-secondary btn-sm" @click="movePrevPage()">이전</button>
       <button class="btn btn-outline-secondary btn-sm" v-for="page in this.$store.state.noticeBoardList.pageList" :key="page"
               :class="{pageNo : page === this.$store.state.noticeBoardList.page}"
               @click="movePage(page)">{{page}}</button>
       <button class="btn btn-outline-secondary btn-sm" @click="moveNextPage()">다음</button>
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
  background: darkgreen;
}

.filter{
  float: left;
  padding-top: 3px;
  margin-left: 20px;
}

.filter select {
  margin-top: 2px auto;
  height: 30px;
}

.search {
  margin-right: 15px;
}
</style>