<template>
  <div>
    <table class="table table-bordered table-condensed">
      <colgroup>
        <col width="5%" />
        <col width="15%" />
        <col width="40%" />
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
      </tr>
      </thead>
      <tbody>
      <tr class="tbody-th1" v-for="qnaBoard in this.$store.state.qnaBoardList.dtoList" :key="qnaBoard.qnaBoardNo"
          @click="goQnaDetail(qnaBoard.qnaBoardNo)">
        <th>{{ qnaBoard.qnaBoardNo }}</th>
        <th>{{ qnaBoard.category }}</th>
        <th>{{qnaBoard.title }}</th>
        <th>{{qnaBoard.writer }}</th>
        <th>{{ qnaBoard.regDate }}</th>
        <th><span v-if="qnaBoard.isAnswered == 1">답변완료</span>
          <span v-if="qnaBoard.isAnswered == 0">답변대기</span>
        </th>
      </tr>
      </tbody>
    </table>

    <div>
      <div>
        <button class="btn btn-outline-secondary btn-sm" @click="movePage('prev')" :disabled="!this.$store.state.qnaBoardList.prev">이전</button>
        <button class="btn btn-outline-secondary btn-sm" v-for="page in this.$store.state.qnaBoardList.pageList" :key="page"
                :class="{pageNo : page === this.$store.state.qnaBoardList.page}"
                @click="fetchQnaBoard(page)">{{page}}</button>
        <button class="btn btn-outline-secondary btn-sm" @click="movePage('next')" :disabled="!this.$store.state.qnaBoardList.next">다음</button>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name:'myQna',
  data(){
    return{
    }
  },
  created() {
    this.$store.dispatch('fetchAdminQna', '0')
  },
  methods:{
    goQnaDetail(qnaBoardNo) {
      console.log(qnaBoardNo)
      this.$router.push({
        name: 'qnaDetail',
        params: {qnaBoardNo: qnaBoardNo}
      })
    },
    movePage(type){
      const prevPageNo = this.$store.state.qnaBoardList.start - 1;
      const nextPageNo = this.$store.state.qnaBoardList.end + 1;

      const page = type === 'prev' ? prevPageNo : nextPageNo;

      this.fetchQnaBoard(page);
    },
    fetchQnaBoard(page) {
      const paramObj = {
        page : page,
        isAnswered: '0'
      };
      this.$store.dispatch('fetchQnaBoard', paramObj);
    },
  }
}
</script>

<style scoped>
.pageNo{
  background : darkgreen;
}

</style>