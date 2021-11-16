<template>
<div>
  <div class="login">
    <div class="tab-bar">
      <div class="tab-shop py-3" style="width:100%;">
        <span class="tab-title">로그인</span>
      </div>
    </div>
    <div class="py-2"><br></div>
    <span class="qna"><i class="bi bi-person-fill"></i></span><br>
    <span style="font-size: 11pt;">Spring Coffee 회원 시스템 로그인입니다!</span>

    <div class="wrapper py-5">
      <div class="container">
        <div class="login-form">
          <div class="input">
            <input class="form-input" type="text" placeholder="이메일" v-model="loginForm.email">
            <div class="tips">
                [ 이메일 형식 ]
            </div>
          </div>
          <div class="input">
            <input class="form-input" type="password" placeholder="패스워드" v-model="loginForm.password">
            <div class="tips">
                [ 영문 대소문자 및 숫자 포함 25자리 ]
            </div>
          </div>
          <div class="btn-box">
              <a href="javascript:;" class="btn" @click="login">로그인</a>
          </div>
          <div class="tips">
              <div class="login" @click="register">회원이 아니신가요?<span> 회원가입</span></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  name: 'login',
  components: {
  },
  data() {
    return {
      loginForm: {
          email: '',
          password: '',
      },
    }
  },
  created() {
    this.loginCheck()
  },
  methods: {
    login() {
      if (this.preCheck()) {
        this.$store.dispatch('fetchLogin', this.loginForm);
      }
    },
    preCheck() {
      let email = this.loginForm.email;
      let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!regEmail.test(email)) {
        alert('올바른 이메일 형식을 입력해주세요!')
        return false;
      }
    },

    loginCheck() {
      if(this.$store.state.isLogin) {
        alert("이미 로그인하였습니다.")
        this.$router.replace("/")
      }
    }
  }
}
</script>
<style>
.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}
.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
}
.tab-title {
    font-size: 22pt;
    color: white;
}
.qna {
    font-size: 26pt;
}
.qna:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}
.outterborder{
  width: 40%;
  margin: auto;
  padding: 20px;
}

.tips {
  font-size: 9pt;
  color: #666;
}

.input {
  padding: 10px;
}

.form-input {
  border: 1px solid #333;
  border-radius: 5px;
  padding: 5px;
}

</style>