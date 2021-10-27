<template>
  <!-- header type1 -->
  <header>

    <div class="border-top border-bottom text-end bg-light">
      <router-link class="navset me-2" style="color: #666;" to="/login">로그인</router-link>
      <router-link class="navset me-5" style="color: ;" to="/register">회원가입</router-link>
    </div>

    <div class="p-4 d-flex flex-wrap align-items-center justify-content-center">
      <a href="/">
      <img src="../.././assets/springlogo.png" alt="Img" style="width:150px">
      </a>
    </div>

    <div class="bg-light border-top border-dark">
      <div class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <router-link class="navbox border-top border-dark" to="/">
          <p class="tooltip-text">홈으로</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/shop">
          <p class="tooltip-text">메뉴보기</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/shop">
          <p class="tooltip-text">쇼핑하기</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/">
          <p class="tooltip-text">문의하기</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/noticeForm">
          <p class="tooltip-text">공지사항</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/noticeForm">
          <p class="tooltip-text">매장찾기</p>
        </router-link>
        <router-link class="navbox border-top border-dark" to="/noticeForm">
          <p class="tooltip-text">검색</p>
        </router-link>
      </div>
      <div class="userInfo">
      </div>
    </div>

    <!-- <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-end">
        <div class="text-end">
          <button class="btn btn-outline-dark me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">장바구니</button>
          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
              <h5 id="offcanvasRightLabel">Offcanvas right</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
              <ul>
                <li></li>
                <li></li>
              </ul>
              <button type="button" class="btn btn-success"> 구매하기 </button>
            </div>
          </div>
          <button type="button" class="btn btn-outline-dark me-2" @click="$router.push('path:/login')">Login</button>
          <button type="button" class="btn btn-success me-2" @click="$router.push('path:/register')">Sign-up</button>
          <router-link to="/admin"><button type="button" class="btn btn-primary" @click="changeShow">관리자</button></router-link>
        </div>
      </div> -->
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
  width: 80px;
  height: 40px;
  font-size: 36pt;
  padding: auto;
  text-align: center;
  opacity: 0.9;
  cursor: pointer;
}

.navbox .tooltip-text:hover {
  border-top: 4px green solid;
  transition: 0.1s;
}

.tooltip-text {
  text-decoration: none;
  width: 100%;
  height: 100%;
  padding: 8px;
  font-size: 11pt;
  color: #111;
  transition: all 0.1s;
}

.navset {
  font-size: 10pt;
}

.userInfo {
  
}
</style> 