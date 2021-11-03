<template>
  <div>
      <div class="mypage">
        <div class="tab-bar">
            <div class="tab-my py-3" style="width:100%;">
                <span class="tab-title">관리자 페이지</span>            
            </div>
        </div>
        <div class="py-2"><br></div>
        <span class="info"><i class="bi bi-gear-fill"></i></span><br>
        <span style="font-size: 11pt;">관리자 페이지입니다</span>
        

          <!-- <div class="user-info py-3 border-top">
              <div class="user-intro">
                  홍길동
              </div>
          </div> -->

          <div class="bt-menu">
              <ui class="bt-info py-3">
                  <li class="content">
                      <div class="con-card">
                        <div class="bt-icon"><i class="bi bi-person-circle"></i></div>
                        <router-link to="/mypage/profile">
                            <div class="bt-text">회원정보</div>
                        </router-link>
                      </div>
                  </li>
                  <li class="content">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-cart-check-fill"></i></div>
                      <div class="bt-text">장바구니</div>
                      </div>
                  </li>
                  <li class="content">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-question-circle"></i></div>
                      <div class="bt-text">문의내역</div>
                      </div>
                  </li>
                  <li class="content">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-currency-dollar"></i></div>
                      <div class="bt-text">결제내역</div>
                      </div>
                  </li>
              </ui>
              <div class="rout border-top border-dark">
                <router-view></router-view>

              </div>

                <!-- <div class="content py-5">
                    <div class="con-card">
                        dasdas
                    </div>
                    <div class="con-card">
                        dasdas
                    </div>
                    <div class="con-card">
                        dasdas
                    </div>
                    <div class="con-card">
                        dasdas
                    </div>
                </div> -->
          </div>
      </div>

  </div>
</template>

<script>
export default {

}
</script>

<style>
.rout {
    padding: 20px;
    width:100%;
    height:100%;
}

.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-my {
  background-color: rgba(0, 0, 0, 0.5);
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.info {
    font-size: 26pt;
}

.user-info {
    width: 100%;
    background-color: greenyellow;
}

.user-intro {
    color: white;
    text-shadow: 1px 1px 5px green;
    font-size: 30pt;
    font-weight: 900;
}

.bt-menu {
    padding: 30px;
}

.bt-menu .bt-info {
    display: flex;
    justify-content: center;
    text-align: center;
    margin: auto;
    list-style-type : none;
}

.bt-menu .bt-info li {
    color: #333;
    padding: 10px;
}

.bt-menu .bt-info .bt-icon {
    font-size: 38pt;
}

.bt-menu .bt-info .bt-text {

}

.content {
    display: flex;
    justify-content: center;
}

.content .con-card {
    width: 100px;
    height: 100px;
    background-color: white;
    box-shadow: 1px 1px 10px #333;
    border-radius: 10px;
    margin-right: 20px;
}

.content .con-card:hover {
    cursor: pointer;
    color: green;
    transition: 0.3s;
}
</style>