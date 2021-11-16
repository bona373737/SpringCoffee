<template>
  <div class="form py-5">
    <div class="wrapper">
      <div v-if="!isCorrect">
        <div class="tab-name d-flex py-3" style="font-size: 16pt; padding: 20px;">본인인증</div>
        <div class="py-3">
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이메일</span>
            <span class="tab-2 content">
              <span class="form-input text-start me-3" style="background-color: #999;" disabled>{{this.$store.state.email}}</span>
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
        <div class="tab-name d-flex py-3" style="font-size: 16pt; padding: 20px;">회원정보</div>
        <div class="py-3">
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이메일</span>
            <span class="tab-2">{{this.$store.state.memberProfile.email}}</span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 이름 </span>
            <span class="tab-2">{{this.$store.state.memberProfile.name}}</span>
          </div>
          <div class="m-info d-flex">
            <span class="tab-1 text-end"> 주소 </span>
            <span class="tab-2" v-if="!isAddress">
               {{this.$store.state.memberProfile.address}}
            </span>
            <span class="tab-2" v-if="isAddress">
               <input type="text" v-model="address">
            </span>
          </div>
          <div class="m-info d-flex" v-if="isAddress">
            <span class="tab-1 text-end"> 패스워드 </span>
            <span class="tab-2">
               <input type="password" v-model="checkPW1">
            </span>
          </div>

          <div v-if="isPassword" class="py-2 border-top">
            <div class="py-2">
              <span style="font-weight: 700;"> 변경할 패스워드를 입력하세요 </span>
            </div>
            <div class="m-info d-flex">
              <span class="tab-1 text-end" style="font-size:10pt;">현재 패스워드</span>
              <span class="tab-2" v-if="isPassword">
                <input type="password" v-model="checkPW1">
              </span>
            </div>
            <div class="m-info d-flex">
              <span class="tab-1 text-end" style="font-size:10pt;">변경할 패스워드</span>
              <span class="tab-2" v-if="isPassword">
                <input type="password" v-model="checkPW2" @change="preCheck()">
                <p style="font-size:8pt;">영문 대소문자 및 숫자 포함 6~12자리</p>
              </span>
            </div>
            <div class="m-info d-flex">
              <span class="tab-1 text-end" style="font-size:10pt;">변경할 패스워드 재입력</span>
              <span class="tab-2" v-if="isPassword">
                <input type="password" v-model="checkPW3" @change="preCheck()"> 
                <p style="font-size:8pt; color: red" v-if="!eqPW">패스워드가 일치하지 않습니다.</p>
                <p style="font-size:8pt; color: green" v-if="eqPW">패스워드가 일치합니다 !</p>
              </span>
            </div>
          </div>

          <div class="m-info py-3">
            <span class="btn btn-secondary me-3" v-if="!isAddress&!isPassword" @click="pwUpdate()">비밀번호 변경</span>
            <span class="btn btn-secondary me-3" v-if="!isAddress&isPassword" @click="pwUpdate()">수정하기</span>
            <span class="btn btn-primary" v-if="!isAddress&!isPassword" @click="infoUpdate()">회원정보 수정</span>
            <span class="btn btn-primary" v-if="isAddress&!isPassword" @click="infoUpdate()">수정하기</span>
          </div>
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
      isAddress: false,
      isPassword: false,
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
          this.isAddress=false;
        } else {
          console.log(this.$store.state.email)
          console.log(this.password)
          alert('비밀번호를 확인하세요')
        }
      })
    },

    infoUpdate() {
      if(this.isAddress==false) {
        this.isAddress=true
        return false;
      } else if(this.password != this.checkPW1) {
        alert('비밀번호를 확인해주세요!')
        return false;
      } else {
        axios.post('/v5/update-userinfo', {
          email: this.$store.state.email,
          password: this.checkPW1,
          name: this.$store.state.memberProfile.name,
          address: this.address
        }).then(res => {
          this.isAddress=false;
          this.checkPw1='';
          console.log('success', res)
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

    pwUpdate() {
      if(this.isPassword==false) {
        this.isPassword=true
        return false;
      } else if(this.password != this.checkPW1) {
        alert('현재 비밀번호를 확인해주세요!')
        return false;
      } else if(this.checkPW2 != this.checkPW3) {
        alert('변경하실 비밀번호를 확인해주세요')
        return false;
      } else {
        let password = this.checkPW2;
        let regPass = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,25}$/;
        if (!regPass.test(password)) {
          alert('패스워드 입력 조건을 확인하세요!')
          return false;
        }

        axios.post('/v5/update-password', {
          email: this.$store.state.email,
          password: this.checkPW2,
        }).then(res => {
          alert('비밀번호가 변경되었습니다.')
          this.isCorrect=false;
          this.isPassword=false;
          this.checkPW1='';
          this.checkPW2='';
          this.checkPW3='';
          this.password='';
          console.log('success', res)
        })
      }
    },
  },
}
</script>

<style>
.form {
  width: 100%;
}

.wrapper {
  width: 50%;
  margin: auto;
  border: 2px solid #999;
  border-radius: 15px;
}

.tab-name {
  font-weight: 700;
  border-bottom: 1px solid #999;
}


.tab-1 {
  width: 30%;
  font-size: 12pt;
  padding: 10px;
}

.tab-2 {
  width: 80%;
  font-size: 11pt;
  margin: auto 0 auto 0;
}

.form-input {
  width: 90%;
  border: 1px solid #333;
  border-radius: 5px;
  padding: 5px;
}
</style>