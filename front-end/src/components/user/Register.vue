<template>
  
    <div class="register">
      <div v-if="this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>

      <div class="outer">

        <div class="tab-bar">
          <div class="tab-shop py-3">
              <span class="tab-title">회원가입</span>            
          </div>
        </div>

        <div class="py-2">
          <div class="info"><i class="bi bi-shield-lock-fill"></i></div>
          <div class="info-text" style="font-size: 11pt;">Spring Coffee 의 회원이 되어보세요!</div>
        </div>
        
        <div class="wrapper py-5">
            <div class="container">

                <div class="register-form">
                    <div class="form form-email">
                      <div class="input">
                        <input class="form-input" type="text" placeholder="이메일" v-model="registerForm.email">
                        <button class="btn-sm email-check" @click="emailCheck()">중복확인</button>
                      </div>
                      <div class="tip-email tips">{{emailTipMsg}}</div>
                    </div>

                    <div class="form form-name">
                      <div class="input">
                        <input class="form-input" type="text" placeholder="이름" v-model="registerForm.name" @change="isNameInput">
                      </div>
                      <div class="tip-name tips">{{nameTipMsg}}</div>
                    </div>

                    <div class="form form-password">
                      <div class="input">
                        <input class="form-input" type="password" placeholder="비밀번호" v-model="registerForm.password" @change="passwordFormatCheck">
                      </div>
                      <div class="tip-password tips">{{passwordTipMsg}}</div>
                    </div>

                    <div class="form form-check-password">
                      <div class="input">
                        <input class="form-input" type="password" placeholder="비밀번호 확인" v-model="passwordCheck" @change="pwcheck">
                      </div>
                      <div class="tip-password-check tips">{{passwordCheckTipMsg}}</div>
                      <div class="tip-password-check tips ok">{{passwordCheckSuccessTipMsg}}</div>
                      <div class="tips password-format">비밀번호는 영문 대소문자, 숫자를 혼합하여 8~20자로 입력해주세요.</div>
                    </div>  

                    <div class="btn-box py-2">
                        <a href="javascript:;" class="btn btn-success" @click="register">회원가입</a>
                    </div>

                    <div class="tips py-2">
                        <div class="login">
                          이미 회원이신가요?<span class="login-link" @click="login">로그인</span>
                        </div>
                    </div>                 
                </div>
            </div>
          </div>
      </div>
    </div>

</template>

<script>
import axios from 'axios';
export default {
    name: 'register',
    components: {
    },
    data() {
        return {
          isValidEmail: false,
          isValidPassword: false,
          isPasswordChecked: false,
          preEmailCheck: false,
          emailTipMsg: '',
          nameTipMsg: '',
          passwordTipMsg: '',
          passwordCheckTipMsg: '',
          passwordCheckSuccessTipMsg: '',
          passwordCheck: '',
          registerForm: {
            email: '',
            name: '',
            password: '',
            fromSocial: false,
            isAdmin: 0,
          },
        }
    },
    methods: {

      emailCheck() {
        let email = this.registerForm.email;
        let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if(!email){
          this.isValidEmail=false
          this.emailTipMsg = '이메일을 입력해주세요.'
          return false
        }
        else if (!regEmail.test(email)) {
          this.isValidEmail=false
          this.emailTipMsg = '이메일 형식으로 입력해주세요.'
          return false
        } else{
          axios.post(`/v5/email-check?email=${email}`)
        .then((res) => {
          if(res.data) {
              this.isValidEmail=true
              this.emailTipMsg = '사용가능한 이메일입니다.'
          } else{
              this.isValidEmail=false
              this.emailTipMsg = '사용하고 있는 이메일입니다.'
          }
          })
          return this.isValidEmail
        }
        
      },
      
      isNameInput() {
        let name = this.registerForm.name
        if(name) {
          this.nameTipMsg = ''
          return true
        } else {
          this.nameTipMsg = '이름을 입력해주세요.'
          return false
        }
      },

      passwordFormatCheck() {
        let password = this.registerForm.password;
        let regPass = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,25}$/;
        if (!regPass.test(password)) {
          this.passwordTipMsg = '비밀번호 조건을 확인해주세요.'
          return false 
        } else {
          this.passwordTipMsg = ''
          return true
        }
      },

      pwcheck() {
        if (this.passwordCheck !== this.registerForm.password) {
          this.passwordCheckTipMsg = '비밀번호가 일치하지 않습니다.'
          this.passwordCheckSuccessTipMsg = ''
          return false
        } else {
          this.passwordCheckTipMsg = ''
          this.passwordCheckSuccessTipMsg = '비밀번호가 일치합니다.'
          return true
        }
      },

      register() {
        if (this.preCheck()) {
          axios.post('/v5/signup', this.registerForm)
                    .then(() => {
                      alert(this.registerForm.name + '님 회원가입되었습니다!');
                      this.$router.push('/login');
                    });
        }
      },
      preCheck() {

        if(!this.emailCheck()) {return false}

        if(!this.isNameInput()) {return false}

        if(!this.registerForm.password) {
          this.passwordTipMsg = '비밀번호를 입력해주세요.'
          return false
        }else if(!this.passwordFormatCheck()) {
          return false
        } 
        else if(!this.pwcheck()) {
          return false
        }

        return true;
      },

      login() {
        this.$router.push('/login');
      },
    }
}
</script>

<style scoped>
.outer{
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

.register-form{
  width: 80%;
  margin: auto;
  padding-top: 20px;
  border-top: 1px solid #ddd;
}

.form {
  height: 80px;
}

.input {
  width: 50%;
  height: 40px;
  padding: 5px;
  border: 1px solid #4F2E20;
  border-radius: 5px;
  margin: auto;
  text-align:start;
  display: flex;
  flex-direction: row
}

.input :first-child{
  align-items: center;
}

.input:focus-within{
  border-width: 2px;
}

.input input {
  height: 30px;
  width: 100%;
  padding: 0px 10px;
  border: none;
  border-radius: 0;
}

.input input:focus {
  outline-width: 0;
  border-bottom: 2px solid #4F2E20;
  border-radius: 0;
}

.email-check{
  margin-left: 5%;
  width: 25%;
  height: 30px;
  border: none;
  background-color: #663C2A;
  color: white;
  font-size: 12px;
  text-align:center;
}

.email-check:hover{
  background: #A36043;
  color: white;
}

.tips {
  margin: 3px auto;
  font-size: 9pt;
  color: red;
}

.ok {
  color: green;
}

.valid {
  color: #A36043;
}

.password-format {
  color: #666;
}

.register {
  margin-top: 10px;
  font-size: 9pt;
  color: #666;
}

.btn-success {
  background: #663C2A;
  border: 2px #663C2A;
  margin-top: 10px;
  height: 40px;
}

.btn-success:hover{
  background: #A36043;
  border: 2px #A36043;
  height: 40px;
}

.login {
  margin-top: 10px;
  font-size: 9pt;
  color: #666;
}

.login .login-link {
  font-weight: bolder;
  color: #663C2A;
}

.login .login-link:hover {
  color: #A36043;
}

</style>