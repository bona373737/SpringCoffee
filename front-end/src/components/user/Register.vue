<template>
<div>
  <div v-if="this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
    <div class="register">
      <div class="tab-bar">
        <div class="tab-shop py-3" style="width:100%;">
            <span class="tab-title">회원가입</span>            
        </div>
      </div>
      <div class="py-2"><br></div>
      <span class="qna"><i class="bi bi-shield-lock-fill"></i></span><br>
      <span style="font-size: 11pt;">Spring Coffee 의 회원이 되어보세요!</span><br>
      <span style="font-size: 11pt;">다양한 이벤트가 기다리고 있습니다!</span>
        <div class="wrapper py-5">
            <div class="container">
                <div class="register-form">
                    <div class="input">
                      <input class="form-input" type="text" placeholder="이메일" v-model="registerForm.email">
                      <div class="tips">
                          <span class="email-check" @click="emailCheck(registerForm.email)">이메일 중복 체크</span>
                      </div>
                    </div>
                    <div class="input">
                      <input class="form-input" type="text" placeholder="이름" v-model="registerForm.name">
                      <div class="tips">
                          [ 본인 이름을 입력하세요 ]
                      </div>
                    </div>
                    <div class="input">
                      <input class="form-input" type="password" placeholder="패스워드" v-model="registerForm.password" @change="pwcheck">
                      <div class="tips">
                          [ 영문 대소문자 및 숫자 포함 25자리 ]
                      </div>
                    </div>
                    <div class="input">
                      <input class="form-input" type="password" placeholder="패스워드 확인" v-model="password2" @change="pwcheck">
                      <br>

                      <div class="tips">
                          <div v-if="isPW">
                          </div>
                          <div v-if="!isPW">
                            <span style="color: red;">패스워드가 동일한지 확인하세요.</span>
                          </div>
                      </div>
                    </div>
                    <div class="btn-box py-2">
                        <a href="javascript:;" class="btn btn-success" @click="register">회원가입</a>
                    </div>
                    <div class="tips py-2">
                        <div class="login" @click="login">이미 회원이신가요?<span>로그인</span></div>
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
          isPW: true,
          isEmail: false,
          dupEmail: 'temp',
          password2: '',
          registerForm: {
            email: '',
            name: null,
            password: '',
            fromSocial: false,
            isAdmin: 0,
          },
        }
    },
    methods: {
      pwcheck() {
        if (this.password2 !== this.registerForm.password) {
          this.isPW=false;
        } else {
          this.isPW=true;
        }
      },
      register() {
        if(!this.isPW) {
          return false;
        }

        if (this.preCheck()) {
          axios.post('/v5/signup', this.registerForm)
          .then(() => {
            alert(this.registerForm.name + '님 회원가입에 감사드립니다!');
            this.$router.push('/login');
          });
        }
      },
      preCheck() {
        let email = this.registerForm.email;
        let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

        if (!regEmail.test(email)) {
          alert('올바른 이메일 형식을 입력해주세요!')
          return false;
        }

        if (this.dupEmail !== email) {
          alert('이메일 중복 체크를 해주세요!')
          return false;
        }


        let name = this.registerForm.name;
        if (name == null) {
          alert('이름을 입력해주세요!')
          return false;
        }

        let password = this.registerForm.password;
        let regPass = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{6,25}$/;
        if (!regPass.test(password)) {
          alert('패스워드 입력 조건을 확인하세요!')
          return false;
        }

        return true;
      },
      login() {
        this.$router.push('/login');
      },
      emailCheck(email) {
        axios.post(`/v5/email-check?email=${email}`)
        .then((res) => {
          if(res.data) {

            let email = this.registerForm.email;
            let regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;

            if (!regEmail.test(email)) {
              alert('올바른 이메일 형식을 입력해주세요!')
              return false;
            }

            this.isEmail=true;
            this.dupEmail=this.registerForm.email;
            alert('사용가능한 이메일입니다.')
          } else {
            this.isEmail=false;
            alert('중복된 이메일입니다.')
          }
        })
      }
    }
}
</script>

<style scoped>
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
  width: 300px;
  margin: auto;
  padding: 20px;
}

.email-check:hover {
  cursor: pointer;
  font-weight: 700;
  transition: 0.2s;
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