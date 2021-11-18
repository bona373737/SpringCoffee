<template>
  <div>
    <button @click="adminList()"> 관리자계정관리 </button>
    <table class="table table-bordered table-condensed">
      <colgroup>
<!--        <col width="5%" />-->
        <col width="10%" />
        <col width="35%" />
        <col width="45%" />
        <col width="25%" />
      </colgroup>
      <thead>
      <tr>
<!--        <th>No.</th>-->
        <th>이름</th>
        <th>email</th>
        <th>주소</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr class="tbody-th1" v-for="(member,index) in this.$store.state.memberList.dtoList" :key="index"
          @click="goDetailInfo">
        <th>{{ member.name }}</th>
        <th>{{ member.email }}</th>
        <th>{{ member.address }}</th>
        <th>
          <button class="btn btn-outline-success btn-sm" @click="grantUserToAdmin(member.email)">관리자등록</button>
          <button class="btn btn-outline-success btn-sm">수정</button>
          <button class="btn btn-outline-success btn-sm" @click="deleteMember(member.email)">삭제</button>
        </th>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: 'memberList',
  mounted() {
    this.$store.dispatch('fetchUserList')
  },
  methods: {
    // goMemberDetail() {
    // console.log(qnaBoardNo)
    // this.$router.push({
    //   name: 'qnaDetail',
    //   params: {qnaBoardNo: qnaBoardNo}
    // })
    // },
    adminList() {
      this.$store.dispatch('fetchAdminList')
    },
    deleteMember(email) {
      // let result = confirm('정말 삭제하시겠습니까?')
      // if (result) {
        axios.delete('/v5/delete', {params: {email: email}})
      // } else {
      //   alert("삭제가 취소되었습니다.")
      // }
    },
    grantUserToAdmin(email) {
      axios.post('/v5/grant-role', {
        email: email
      })
          .then(res => {
            console.log(res.data)
            alert("관리자 계정으로 변경되었습니다")
          })
    }
  }
}
</script>