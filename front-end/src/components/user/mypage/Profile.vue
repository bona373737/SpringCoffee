<template>
  <div class="form py-5">
    <div class="wrapper">
      <div v-if="!isCorrect">
        <div class="tab-name d-flex py-3" style="font-size: 16pt; padding: 20px;">본인인증</div>
        <div class="py-3">
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이메일</span>
            <span class="tab-2 content">
              <input class="form-input me-3" style="background-color: #999" disabled v-model="this.$store.state.email">
            </span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 패스워드</span>
            <span class="tab-2 content">
              <input class="form-input me-3" type="password" v-model="password">
            </span>
          </div>
          <div class="m-info py-3">
            <span class="btn btn-primary" @click="verify()">비밀번호 확인</span>
          </div>
        </div>
      </div>


      <!-- 비밀번호 인증 시  -->
      <div v-if="isCorrect">
        <div class="tab-name py-3" style="font-size: 16pt; padding: 20px;">
          회원정보
          <div>
            <span style="font-size:10pt; color: red;">* 표시는 필수로 입력해주세요</span>
          </div>
        </div>
        
        <div class="py-3">
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이메일</span>
            <span class="tab-2">{{this.$store.state.memberProfile.email}}</span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end">현재 비밀번호</span>
            <span style="color:red;">*</span>
            <span class="tab-2">
              <input type="password" v-model="checkPW1">
            </span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end"><br><br>비밀번호 변경
            </span>
            
            <span style="color:white;">*</span>
            <span class="tab-2">
                <input id="pw2" type="password" v-model="checkPW2" @change="preCheck()">
                <br><span style="font-size:8pt;">영문 대소문자 및 숫자 포함 6~12자리</span>
            </span>
          </div>
          <div class="m-info d-flex">
              <span class="tab-1 text-end" style="font-size:10pt;"></span>
              <span style="color:white;">*</span>
              <span class="tab-2">
                  <input id="pw3" type="password" v-model="checkPW3" @change="preCheck()"> 
                  <br><span style="font-size:8pt; color: red" v-if="!eqPW">패스워드가 일치하지 않습니다.</span>
                  <span style="font-size:8pt; color: green" v-if="eqPW">패스워드가 일치합니다 !</span>
              </span>
          </div>
          </div>

          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이름 </span>
            <span class="tab-2">{{this.$store.state.memberProfile.name}}</span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end">배송지</span>
            <span style="color:white;">*</span>
            <span class="tab-2">
              <input type="text" v-model="address">
            </span>
          </div>
            <div class="m-info py-3">
              <span class="btn btn-primary me-2" @click="infoUpdate()">회원정보 수정</span>
              <span class="btn btn-secondary " @click="goBack()">뒤로가기</span>
            </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Profile',
  data() {
    return {
      isCorrect: false,
      eqPW: false,
      password: '',
      checkPW1: '',
      checkPW2: '',
      checkPW3: '',
      address: this.$store.state.memberProfile.address,
    }
  },
  created() {
    this.$store.dispatch('fetchMemberProfile')
  },
  methods: {
    verify() {
      axios.post('/v5/verifyuser', {
        email : this.$store.state.email,
        password : this.password
      })
      .then(res => {
        if(res.data == true) {
          this.isCorrect=true;
        } else {
          console.log(this.$store.state.email)
          console.log(this.password)
          alert('비밀번호를 확인하세요')
        }
      })
    },
    infoUpdate() {
      if(this.password != this.checkPW1) {
        alert('현재 비밀번호를 확인해주세요!')
        return false;
      } else if(this.checkPW2 != this.checkPW3) {
        alert('변경하실 비밀번호를 확인해주세요')
        return false;
      } else {

        if(this.checkPW2 != '') {
          let password = this.checkPW2;
          let regPass = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,25}$/;
          if (!regPass.test(password)) {
            alert('패스워드 입력 조건을 확인하세요!')
            return false;
          }

          axios.post('/v5/update-password', {
            email: this.$store.state.email,
            password: this.checkPW2,
          })
        }

        axios.post('/v5/update-userinfo', {
          email: this.$store.state.email,
          password: this.checkPW1,
          name: this.$store.state.memberProfile.name,
          address: this.address
        }).then(res => {
          alert('회원정보가 변경되었습니다.')
          this.isCorrect=false;
          this.checkPW1='';
          this.checkPW2='';
          this.checkPW3='';
          this.password='';
          this.$store.dispatch('fetchMemberProfile')
          this.$router.replace('/mypage')
          console.log('success',res)
        })
      }
    },

    preCheck() {
      if(this.checkPW2 == '') return false;
      if(this.checkPW3 == '') return false;

      if(this.checkPW2 !== this.checkPW3) {
        this.eqPW=false;
      } else if(this.checkPW2 === this.checkPW3) {
        this.eqPW=true;
      }
    },
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
</style>