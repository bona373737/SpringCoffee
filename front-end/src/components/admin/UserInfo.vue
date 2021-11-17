<template>
  <div>
    <button @click="adminList"> 관리자 </button>
    <button @click="userList"> 고객 </button>
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
        <th>이름</th>
        <th>email</th>
        <th>주소</th>
        <th>권한</th>
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
import axios from "axios";

export default {
  name:'myQna',
  data(){
    return{
    }
  },
  // created() {
  //   this.$store.dispatch('fetchAdminQna', '0')
  // },
  methods:{
    goQnaDetail(qnaBoardNo) {
      console.log(qnaBoardNo)
      this.$router.push({
        name: 'qnaDetail',
        params: {qnaBoardNo: qnaBoardNo}
      })
    },
    adminList(){

    },
    userList(){

    }

  }
}
</script>