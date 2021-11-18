<template>
  <div class="outter-div">
    <table>
      <tr>
        <td>email</td>
        <td>{{this.$store.state.memberDetail.name}} </td>
      </tr>
      <tr>
        <td>name</td>
        <td> <input v-model="name"></td>
      </tr>
      <tr>
        <td>address</td>
        <td ><input v-model="address"> </td>
      </tr>
      <button @click="memberInfoUpdate(memberInfo)">회원정보 수정</button>
    </table>


  </div>

</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      memberInfo:{
        email : this.$store.state.memberDetail.email,
        name: this.$store.state.memberDetail.name,
        address:this.$store.state.memberDetail.address
      }
    }
  },
  created() {
    this.$store.dispatch('fetchMemberDetail',this.$route.params.email)
  },
  methods:{
  memberInfoUpdate(memberInfo){
    axios.post('/v5/admin/update-userinfo',memberInfo)
        .then(response => {
          console.log(response)
          console.log('회원정보 수정')
        })
  }
  }

}
</script>

<style scoped>
.outter-div{
  text-align: center;
}
</style>