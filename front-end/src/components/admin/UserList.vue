<template>
  <div>
    <button class="btn btn-success" @click="$router.push('/adminList')"> 관리자계정관리 </button>
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
        <th>email</th>
        <th>이름</th>
        <th>주소</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr class="tbody-th1" v-for="(member,index) in this.$store.state.memberList.dtoList" :key="index">
        <th>{{ member.email }}</th>
        <th>{{ member.name }}</th>
        <th>{{ member.address }}</th>
        <th>
          <button class="btn btn-outline-success btn-sm" @click="goMemberDetail(member.email)">수정</button>
          <button class="btn btn-outline-success btn-sm" @click="deleteMember(member.email)">삭제</button>
          <button class="btn btn-outline-success btn-sm" @click="grantUserToAdmin(member.email)">관리자전환</button>
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
  data(){
    return{
      email : '',
    }
  },
  mounted() {
    this.$store.dispatch('fetchUserList')
  },
  methods: {
    goMemberDetail(email){
      this.$router.push({
        name:'memberDetail',
        params:{email:email}
      })
    },
    deleteMember(email) {
      let result = confirm('정말 삭제하시겠습니까?')
      if (result) {
        axios.delete('/v5/delete', {params: {email: email}})
            .then(res => {
              console.log(res.data)
              alert("삭제되었습니다.")
              this.$store.dispatch('fetchUserList')
            })
      } else {
        alert("삭제가 취소되었습니다.")
      }
    },
    grantUserToAdmin(email) {
      this.email = email,
      axios.post('/v5/grant-role', null,{params:{email:email}})
          .then(res => {
            console.log(res.data)
            alert("관리자 계정으로 변경되었습니다")
            this.$store.dispatch('fetchUserList')
          })
    },

  }//methods end
}
</script>

<style scoped>
button{
  margin: 3px;
}
</style>