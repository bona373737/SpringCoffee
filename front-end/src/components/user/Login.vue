<template>
  <div class="login">
    <div class="outer">

      <div class="tab-bar">
        <div class="tab-shop py-3">
          <span class="tab-title">로그인</span>
        </div>
      </div>

      <div class="py-2">
        <div class="info"><i class="bi bi-person-fill"></i></div>
        <span class="info-text">Spring Coffee를 이용하기 위해서 로그인해주세요!</span>
      </div>
      
      <div class="wrapper py-5">
        <div class="container">
          <div class="login-form">

            <div class="input">
              <input class="form-input" type="text" placeholder="이메일" v-model="loginForm.email" @waiting="emailCheck">
              <div class="tip-email" v-show="!isEmail">이메일 형식으로 입력해주세요.</div>
            </div>

            <div class="input">
              <input class="form-input" type="password" placeholder="비밀번호" v-model="loginForm.password">
            </div>
          </div>
          <div class="btn-box py-2">
              <a class="btn" @click="login">로그인</a>
          </div>
          <div class="tips py-2">
              <div class="login" @click="register">회원이 아니신가요?<span> 회원가입</span></div>
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
      isEmail: false,
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
      return true;
    },

    loginCheck() {
      if(this.$store.state.isLogin) {
        alert("이미 로그인하였습니다.")
        this.$router.replace("/")
      }
    },
    async emailCheck() {
    let email = this.loginForm.email;
    console.log(email)
    console.log(this.isEmail)
      let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!regEmail.test(email)) {
        this.isEmail = false;
      } else{
      this.isEmail = true;
      }
    console.log(this.isEmail)

    },
    register() {
      this.$router.replace("/register")
    }
  }
}
</script>
<style>
.outer {
  background-color: #f7f7f7;
}

.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  width: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.tab-title {
    font-weight: bold;
    font-size: 25pt;
    color: white;
}

.outer .py-2 {
    width: 60%;
    margin: auto;
    background-color: white;
}

.info {
    padding-top: 48px;
    font-size: 30pt;
    color: #663C2A;
}

.info:hover {
    color: #A36043;
    transition: 0.3s;
}

.info-text {
  color: #4F2E20;
  font-size: 12pt;
  display: block;
  /* padding-bottom: 50px; */
}

.wrapper {
  width: 60%;
  margin: auto;
  background-color: white;
}

.login-form{
  width: 60%;
  margin: auto;
  padding-top: 20px;
  border-top: 1px solid #ddd;
}

.tip-email {
  margin-top: 2px;
  font-size: 9pt;
  color: red;
}

.input {
  padding: 10px;
}

.form-input {
  border: 1px solid #4F2E20;
  border-radius: 5px;
  padding: 5px;
}

.btn-box a {
  background-color: #663C2A;
  color: white;
}

.btn-box a:hover {
  background-color: #A36043;
  color: white;
}
</style>