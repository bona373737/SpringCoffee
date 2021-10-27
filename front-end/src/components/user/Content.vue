<template>
  <div class="outterdiv">
    <div class="content-list">
      <img class="bg2" src="../.././assets/background2.jpg" alt="">
      <div class="carouseldiv">
        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-label="슬라이드 1" aria-current="true"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="슬라이드 2" class=""></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="슬라이드 3" class=""></button>
          </div>

          <div class="carousel-inner">
            <div class="carousel-item active">
              <!-- <img src="../.././assets/content1.jpg" style="width: 100%" alt="" /> -->
              <div class="container-fluid py-5 content-wrap">
                <div class="text-white">
                  <h1 class="display-5 fw-bold">Spring Coffee</h1>
                  <h4 class="fw-bold">회원분들에게만 다양한 혜택을 드립니다!</h4>
                  <p> 스프링 커피와 함께하세요</p>
                  <router-link to="/login">
                    <button class="btn btn-success" type="button">회원가입</button>
                  </router-link>
                </div>
              </div>
            </div>

            <div class="carousel-item">
              <div class="container-fluid py-5 content-wrap">
                <div class="text-white">
                  <h1 class="display-5 fw-bold">1+1 Event !!</h1>
                  <h4 class="fw-bold">리뷰 쓰면 커피 한잔 더!!</h4>
                  <p> 소중한 리뷰 남겨주시면 커피 쿠폰 지급!!</p>
                  <router-link to="/noticeForm">
                    <button class="btn btn-secondary" type="button">자세히</button>
                  </router-link>
                </div>
              </div>
            </div>
            
            <div class="carousel-item">
              <div class="container-fluid py-5 content-wrap">
                <div class="text-white">
                  <h1 class="display-5 fw-bold">파격 할인!!</h1>
                  <h4 class="fw-bold">가을 맞이 이벤트 !!</h4>
                  <p>아메리카노 50% 할인중</p>
                  <router-link to="/shop">
                    <button class="btn btn-primary" type="button">쇼핑하기</button>
                  </router-link>
                </div>
              </div>
            </div>
          </div>

          <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">이전</font></font></span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">다음과 같은</font></font></span>
          </button>
        </div>
      </div>

      <div class="align-items-md-stretch">
        <div class="text-white bg-light rounded-3">
          <img src="../.././assets/c01.jpg" style="max-width: 100%; height: auto;" alt="">
          <img src="../.././assets/c02.jpg" style="max-width: 100%; height: auto;" alt="">
          <img src="../.././assets/c03.jpg" style="max-width: 100%; height: auto;" alt="">
          <img src="../.././assets/c04.jpg" style="max-width: 100%; height: auto;" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.carouseldiv {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.carousel-item img {
  height: 600px;
  /* overflow: hidden; */
}

.content-wrap {
  background-color: rgba(0, 0, 0, 0.5);
}

.content-text {
  opacity: 1;
  color: white;
}

.content-list .bg2 {
  left: 0;
  width: 100%;
  height: 100%;
  position: fixed;
  z-index: -1;
}
</style> 