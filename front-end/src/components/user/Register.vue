<template>
<div>
    <!-- 회원가입 첫페이지  -->
    <div class="outterborder" v-if="!isStep2">
        <img :src="logoImg" alt="로고이미지" class="logo" >
        <h2>Welcome Spring Coffee!</h2>
        <br />

        <div class="mb-3">
            <label for="email" class="form-label">Email address </label>
            <input  v-model="email"
                    type="email"
                    class="form-control"
                    id="email"
                    aria-describedby="emailHelp"
            />
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input
            v-model="password"
            type="password"
            class="form-control"
            id="password"
            maxlength="16"
            />
        </div>
        <div class="mb-3">
            <label for="passwordConfirm" class="form-label">Confirm password</label>
            <input
            v-model="passwordConfirm"
            type="password"
            class="form-control"
            id="passwordConfirm"
            maxlength="16"
            />
        </div>
        <br />
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1" />
            <label class="form-check-label" for="exampleCheck1">
                <a href="">개인정보제공</a> 및 <a href="">이용약관</a>에동의합니다.
            </label>
        </div>
        <br/>

        <button type="submit" class="btn btn-outline-success" style="width: 50%" @click="moveNextPage">next</button>
  </div>


  <!-- 회원가입 두번째페이지(회원정보입력) -->
  <div class="outterborder" v-else>
    <img :src="logoImg" alt="로고이미지" class="logo" />
    <h2>Spring Coffee 회원정보 입력</h2>
    <br/>
    <form>
      <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input
          v-model="userName"
          type="email"
          class="form-control"
          id="name"
          aria-describedby="emailHelp"
        />
      </div>
      <div class="mb-3">
        <label for="phoneNumer" class="form-label">PhoneNumber</label>
        <input
          v-model="phoneNumber"
          type="text"
          class="form-control"
          id="phoneNumer"
          placeholder="숫자만입력해주세요(-제외)"
        />
      </div>
      <div class="mb-3">
        <label for="address" class="form-label">Address</label>
        <input
          v-model="address"
          type="password"
          class="form-control"
          id="address"
        />
      </div>
      <br/>
      <button type="submit" class="btn btn-outline-success" style="width: 50%" @click="registerUser">Register</button>
    </form>
  </div>
</div>
</template>

<script>

export default {
  name: "SignUpPage",
  data() {
    return {
      isStep2: false,
      registerForm: {
        email: "",
        password: "",
        // password 
        userName: "",
        phoneNumber: 0,
        address: "",
      },
      logoImg: require('../.././assets/springlogo.jpg')
    };
  },
  methods: {
    moveNextPage() {
      this.isStep2 = !this.isStep2;
    },
    registerUser(){
      // 디비에 회원정보를 저장한다
      // 성공적으로 저장되면, 로그인 페이지로 이동하자
    },
    /*
        1PAGE 넘어갈때, 2PAGE 서브밋 - 버튼 클릭 
        필수값 체크
        VALIDATE 체크 - 값이 유효한지, 형식에 맞는지
        keypress, keyup - pwd 두개 똑같은지 대조
    */
  },
};
</script>

<style scoped>
.outterborder{
  width: 30%;
  margin: auto;
}
.logo{
  width: 20%;
  height: 20%;
}
</style>