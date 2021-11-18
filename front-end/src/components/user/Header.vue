<template>
  <!-- header type1 -->
  <header>
    <div class="border-top border-bottom text-end py-2">
      <div v-if="this.$store.state.isLogin">
        <div v-if="this.$store.state.role=='ROLE_ADMIN'">
          <router-link class="navset me-5" style="text-decoration: none; color: #666;" to="/admin"> <i class="bi bi-gear-fill"></i> 관리자페이지</router-link>
        </div>
        <span class="navset me-2" style="text-decoration: none; color: #333;"> {{this.$store.state.email}}</span>
        <router-link class="navset me-2" style="text-decoration: none; color: green; font-weight: 700" to="/cart">장바구니</router-link>
        <router-link class="navset me-1" style="text-decoration: none; color: #333; font-weight: 700" to="/mypage">마이페이지</router-link>
        <span class="navset me-5" style="text-decoration: none; color: #666; cursor: pointer;" @click="this.$store.dispatch('logout')"> 로그아웃 </span>
      </div>

      <div v-if="!this.$store.state.isLogin">
        <router-link class="navset me-2" style="text-decoration: none; color: #666;" to="/login">로그인</router-link>
        <router-link class="navset me-5" style="text-decoration: none;" to="/register">회원가입</router-link>
      </div>
    </div>

    <div class="p-5">
      <a href="/">
        <img src="../.././assets/sclogo.png" alt="Img" style="width:150px">
      </a>
    </div>
    <div class="border-top ">
      <div class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 py-1">
        <router-link class="navbox" to="/shop">
          <p class="tooltip-text">SHOP</p>
        </router-link>
        <router-link class="navbox" to="/noticeLayout">
          <p class="tooltip-text">NOTICE</p>
        </router-link>
        <router-link class="navbox" to="/qnaLayout">
          <p class="tooltip-text">Q&A</p>
        </router-link>
        <router-link class="navbox" to="/offstore">
          <p class="tooltip-text">STORE</p>
        </router-link>
        <router-link class="navbox" to="/">
          <p class="tooltip-text">Spring Coffee</p>
        </router-link>
      </div>
      <div class="userInfo">
      </div>
    </div>
  </header>
</template>


<script>
export default {
  name: 'Header',
  methods:{
    changeShow() {
      this.$store.commit('checkShow')
    }
  }
}
</script>

<style scoped>
header {
    width: 100%;
    /* margin-bottom: 10px; */
}

.navbox {
  text-decoration: none;
  width: 180px;
  height: 40px;
  font-size: 36pt;
  padding: auto;
  text-align: center;
  opacity: 0.9;
  cursor: pointer;
}

.navbox .tooltip-text:hover {
  /* border-top: 2px brown solid; */
  transform:scale(1.1);
  color: rgba(255, 145, 0, 0.979);
  transition: 0.1s;
}

.tooltip-text {
  text-decoration: none;
  width: 100%;
  height: 100%;
  padding: 8px;
  font-size: 13pt;
  font-weight: 600;
  color: #666;
  transition: all 0.1s;
}

.navset {
  font-size: 11pt;
}
</style> 