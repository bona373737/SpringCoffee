<template>
  <div>
     <div class="outterDiv py-5">
      <div class="btnWrap text-end">
        <input type="text" class="me-2">
        <button class="btn btn-success me-2" > 검색 </button>
        <button class="btn btn-primary" @click="$router.push('/noticeAdd')"> 추가 </button>
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
<!--            <th>조회수</th>-->
          </tr>
          </thead>
          <tbody>
          <tr class="tbody-th1" v-for="board in this.$store.state.boardList" v-bind:key="board.boardNo" @click="goNoticeDetail(board.boardNo)" >
            <th>{{ board.boardNo }}</th>
            <th>{{ board.title }}</th>
            <th>{{ board.writer }}</th>
            <th>{{ board.modDate }}</th>
          </tr>
          </tbody>
        </table>
      </div>
        <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>
    </div>
  </div>
</template>

<script>
export default {
  name:'NoticeForm',
  created() {
    this.$store.dispatch('fetchBoard', this.$route.params.category="notice");
  },
  data() {
    return {
      totalPage: 0,
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,
      page: 0,
      start: 0,
      end: 0,
      prev: false,
      next: false

    };
  },

  methods: {
    goNoticeDetail(boardNo) {
      this.$router.push({
        name: 'noticeDetail',
        params: { boardNo: boardNo }
      })
    },
  },
};
</script>

<style scoped>
.outterDiv{
  width: 60%;
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