<template>
  <!-- header type1 -->
  <header>

    <div class="border-top border-bottom text-end">
      <router-link class="navset me-2" style="text-decoration: none; color: #666;" to="/mypage">마이페이지</router-link>
      <router-link class="navset me-2" style="text-decoration: none; color: #666;" to="/admin">관리자페이지</router-link>
      <router-link class="navset me-2" style="text-decoration: none; color: #666;" to="/login">로그인</router-link>
      <router-link class="navset me-5" style="text-decoration: none;" to="/register">회원가입</router-link>
    </div>

    <div class="p-3 bg-light">
      <a href="/">
        <img src="../.././assets/springlogo.png" alt="Img" style="width:150px">
      </a>
    </div>
    <div class="bg-light border-top ">
      <div class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <router-link class="navbox" to="/shop">
          <p class="tooltip-text">SHOP</p>
        </router-link>
        <router-link class="navbox" to="/noticelayout">
          <p class="tooltip-text">NOTICE</p>
        </router-link>
        <router-link class="navbox" to="/qnalayout">
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
  border-top: 2px green solid;
  color: rgba(0, 200, 0, 0.7);
  transition: 0.1s;
}

.tooltip-text {
  text-decoration: none;
  width: 100%;
  height: 100%;
  padding: 8px;
  font-size: 13pt;
  font-weight: 600;
  color: green;
  transition: all 0.1s;
}

.navset {
  font-size: 10pt;
}
</style> 