<template>
  <div>
      <div class="mypage">
          <div class="user-info py-3 border-top">
              <div class="user-intro">
                  홍길동
              </div>
          </div>

          <div class="bt-menu">
              <ui class="bt-info">
                  <li>
                      <div class="bt-icon"><i class="bi bi-person-circle"></i></div>
                      <router-link to="/mypage/profile">
                        <div class="bt-text">회원정보</div>
                      </router-link>
                  </li>
                  <li>
                      <div class="bt-icon"><i class="bi bi-cart-check-fill"></i></div>
                      <div class="bt-text">장바구니</div>
                  </li>
                  <li>
                      <div class="bt-icon"><i class="bi bi-question-circle"></i></div>
                      <div class="bt-text">문의내역</div>
                  </li>
                  <li>
                      <div class="bt-icon"><i class="bi bi-currency-dollar"></i></div>
                      <div class="bt-text">결제내역</div>
                  </li>
              </ui>
                <router-view></router-view>

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
.user-info {
    width: 100%;
    background-color: greenyellow;
}

.user-intro {
    color: oldlace;
    text-shadow: 1px 1px 5px green;
    font-size: 30pt;
    font-weight: 900;
}

.bt-menu .bt-info {
    display: flex;
    justify-content: center;
    text-align: center;
    margin: auto;
    list-style-type : none;
    background-color: #333;
}

.bt-menu .bt-info li {
    color: white;
    padding: 10px;
}

.bt-menu .bt-info li:hover {
    cursor: pointer;
    color: greenyellow;
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
    width: 200px;
    height: 250px;
    background-color: white;
    box-shadow: 1px 1px 10px #333;
    border-radius: 10px;
    margin-right: 20px;
}
</style>