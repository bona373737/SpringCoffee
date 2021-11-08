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
    <ul v-if="pager.pages && pager.pages.length" class="pagination" :style="ulStyles">
        <li class="page-item first" :class="{'disabled': pager.currentPage === 1}" :style="liStyles">
            <a class="page-link" @click="setPage(1)" :style="aStyles">{{labels.first}}</a>
        </li>
        <li class="page-item previous" :class="{'disabled': pager.currentPage === 1}" :style="liStyles">
            <a class="page-link" @click="setPage(pager.currentPage - 1)" :style="aStyles">{{labels.previous}}</a>
        </li>
        <li v-for="page in pager.pages" :key="page" class="page-item page-number" :class="{'active': pager.currentPage === page}" :style="liStyles">
            <a class="page-link" @click="setPage(page)" :style="aStyles">{{page}}</a>
        </li>
        <li class="page-item next" :class="{'disabled': pager.currentPage === pager.totalPages}" :style="liStyles">
            <a class="page-link" @click="setPage(pager.currentPage + 1)" :style="aStyles">{{labels.next}}</a>
        </li>
        <li class="page-item last" :class="{'disabled': pager.currentPage === pager.totalPages}" :style="liStyles">
            <a class="page-link" @click="setPage(pager.totalPages)" :style="aStyles">{{labels.last}}</a>
        </li>
    </ul>

    </div>
  </div>
</template>

<script>

export default {
  name:'NoticeForm',
  created() {
    this.$store.dispatch('fetchBoard', this.$route.params);
  },
  data() {
            return {
                pager: {},
                ulStyles: {},
                liStyles: {},
                aStyles: {}
            }
  },

  methods: {
    onChangePage(pageOfItems) {
      this.pageOfItems = pageOfItems;
    },
    goNoticeDetail(boardNo) {
      this.$router.push({
        name: 'noticeDetail',
        params: { boardNo: boardNo }
      })
    },
  }
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