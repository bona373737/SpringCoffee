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
        <th>문의종류</th>
        <th>제목</th>
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
        <th>{{ qnaBoard.regDate }}</th>
        <th><span v-if="qnaBoard.isAnswered == 1">답변완료</span>
          <span v-if="qnaBoard.isAnswered == 0">답변대기</span>
        </th>
      </tr>
      </tbody>
    </table>
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
    this.$store.dispatch('fetchMyQna', this.$store.state.email)
  },
  methods:{
    goQnaDetail(qnaBoardNo) {
      console.log(qnaBoardNo)
      this.$router.push({
        name: 'qnaDetail',
        params: {qnaBoardNo: qnaBoardNo}
      })
    },

  }
}
</script>