<template>
  <div class="form py-5">
    <div class="wrapper">
        <div class="tab-name py-3" style="font-size: 16pt; padding: 20px;">
          회원정보
<!--          <div>-->
<!--            <span style="font-size:10pt; color: red;">*표시 필수입력해주세요</span>-->
<!--          </div>-->
        </div>
        <br>

        <table style="margin: auto">
          <tr >
            <td style="border-right: 1px solid #999">
              <span class="tab-1 text-end"> 이메일</span>
            </td>
            <td>
              <span class="tab-2">{{this.$store.state.memberDetail.email}}</span>
            </td>
          </tr>
          <tr>
            <td style="border-right: 1px solid #999">
              <span class="tab-1 text-end"> 이름 </span>
            </td>
            <td>
              <input type="text" v-model="name" >
            </td>
          </tr>
          <tr>
            <td style="border-right: 1px solid #999">
              <span class="tab-1 text-end">주소</span>
              <span style="color:white;">*</span>
            </td>
            <td>
              <input type="text" v-model="address" >
            </td>
          </tr>
        </table>
              <button class="btn btn-primary" @click="memberInfoUpdate(memberInfo)">수정</button>
              <button class="btn btn-primary" @click="$router.go(-1)">뒤로가기</button>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Profile',
  data() {
    return {
      memberInfo:{
        email:this.$store.state.memberDetail.email,
        name:this.name,
        address:this.address
      },
      eqPW: false,
      password: '',
      checkPW1: '',
      checkPW2: '',
      checkPW3: '',
      passwordCheck : false,
      name: this.$store.state.memberDetail.name,
      address: this.$store.state.memberDetail.address,
    }
  },
  mounted() {
    this.$store.dispatch('fetchMemberDetail',this.$route.params.email)
  },
  methods: {
    memberInfoUpdate(memberInfo){
      memberInfo.name = this.name
      memberInfo.address = this.address
      axios.post('/v5/admin/update-userinfo',memberInfo)
          .then(response => {
            console.log(response)
            alert("회원정보가 수정되었습니다")
            this.$router.go(-1)
          })
    }
  },

}
</script>

<style scoped>
.form {
  width: 100%;
}

.wrapper {
  width: 500px;
  margin: auto;
  border: 2px solid #999;
  border-radius: 15px;
}

.tab-name {
  font-weight: 700;
  border-bottom: 1px solid #999;
}


.tab-1 {
  width: 300px;
  margin: auto;
  font-size: 12pt;
  padding: 10px;
}

.tab-2 {
  width: 80%;
  font-size: 11pt;
  margin: auto 0 auto 0;
}

.form-input {
  width: 200px;
  border: 1px solid #333;
  border-radius: 5px;
  padding: 5px;
}

.btn-primary {
  background-color: #663C2A;
  border: 2px #663C2A;
  margin:30px 0px 0px;
  height: 40px;
}

.btn-primary:hover{
  background: #A36043;
  border: 2px #A36043;
  height: 40px;
}

table{
  width: 90%;
}

tr{
  height: 70px;
  vertical-align: top;
}

.btn{
  margin-right: 5px;
  margin-bottom: 10px;
}

</style>