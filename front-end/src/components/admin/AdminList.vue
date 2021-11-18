<template>
  <div>
    <button class="btn btn-success" @click="$router.push('/userList')"> 사용자계정관리 </button>

    <table class="table table-bordered table-condensed">
      <colgroup>
        <col width="10%" />
        <col width="25%" />
        <col width="35%" />
        <col width="40%" />
      </colgroup>
      <thead>
      <tr>
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
          <button class="btn btn-outline-success btn-sm" @click="deleteAdmin(member.email)">삭제</button>
          <button class="btn btn-outline-success btn-sm" @click="deleteAuthority(member.email)">권한박탈</button>
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
      // email : '',
    }
  },
  mounted() {
    this.$store.dispatch('fetchAdminList')
  },
  methods: {
    goMemberDetail(email){
      this.$router.push({
        name:'memberDetail',
        params:{email:email}
      })
    },
    deleteAuthority(email){
      console.log(email)
      axios.post('/v5/remove-role',null,{params:{email:email}})
    },
    deleteAdmin(email) {
      let result = confirm('정말 삭제하시겠습니까?')
      if (result) {
        axios.delete('/v5/delete', {params: {email: email}})
          .then(res => {
            console.log(res.data)
            alert("삭제되었습니다.")
            this.$store.dispatch('fetchAdminList');
          })
      } else {
        alert("삭제가 취소되었습니다.")
      }
    },
  }//methods end
}
</script>

<style scoped>
button{
  margin: 3px;
}
</style>