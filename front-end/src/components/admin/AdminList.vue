<template>
  <div>
    <button @click="$router.push('/memberList')"> 사용자계정관리 </button>
    <table class="table table-bordered table-condensed">
      <colgroup>
<!--        <col width="5%" />-->
        <col width="10%" />
        <col width="25%" />
        <col width="35%" />
        <col width="40%" />
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
          <button class="btn btn-outline-success btn-sm">수정</button>
          <button class="btn btn-outline-success btn-sm" @click="deleteMember(member.email)">삭제</button>
          <button class="btn btn-outline-success btn-sm" @click="removeAdmin">권한박탈</button>
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
    this.$store.dispatch('fetchAdminList')
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
      let result = confirm('정말 삭제하시겠습니까?')
      if (result) {
        axios.delete('/v5/delete', {params: {email: email}})
      } else {
        alert("삭제가 취소되었습니다.")
      }
    },
    removeAdmin(){
      // console.log(email)
      axios.post('/v5/remove-role',{email:this.email})

    }

  }
}
</script>