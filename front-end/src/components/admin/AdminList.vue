<template>
  <div class="outterDiv">
    <div class="btns">
    <button class="btn btn-success" @click="$router.push('/userList')"> 사용자계정관리 </button>
    </div>

    <table class="table table-bordered table-condensed">
      <colgroup>
        <col width="10%" />
        <col width="20%" />
        <col width="30%" />
        <col width="40%" />
      </colgroup>
      <thead class="table-thead">
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
          <div>
            <button class="btn btn-outline-success btn-sm" @click="goMemberUpdate(member.email)">수정</button>
            <button class="btn btn-outline-success btn-sm" @click="deleteAdmin(member.email)">삭제</button>
            <button class="btn btn-outline-success btn-sm" @click="deleteAuthority(member.email)">권한박탈</button>
          </div>
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
  created() {
    this.$store.dispatch('fetchAdminList')
  },
  methods: {
    goMemberUpdate(email){
      this.$router.push({
        name:'memberInfoUpdate',
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
.outterDiv{
  width: 90%;
  margin: auto;
  background-color: white;
}

th{
  border-left: none;
  border-right: none;
  vertical-align: middle;
}

/*.table-thead  {*/
/*  border-color: #4F2E20;*/
/*}*/

button{
  margin: 3px;
}

.btns{
  margin-bottom: 20px;
}

.btns .btn-success {
  background: #663C2A;
  border: 2px #663C2A;
  margin:30px 0px 0px;
  height: 40px;
}

.btn-outline-success{
  margin-right: 5px;
  color: #4F2E20;
  border: 2px solid #4F2E20;
}

.btn-outline-success:hover{
  border: 2px solid #A36043;
  background: #A36043;
}

.btns .btn-success {
  background: #663C2A;
  border: 2px #663C2A;
  height: 40px;
}

.btns .btn-success:hover{
  background: #4F2E20;
  border: 2px #4F2E20;
  height: 40px;
}

</style>