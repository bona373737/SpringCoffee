<template>
  <div class="outterdiv">
    <div class="content-list">
      <img class="bg2" src="../.././assets/background2.jpg" alt="">
      <div class="container-fluid py-5 content-wrap">
        <div class="text-white">
          <h1 class="display-5 fw-bold">Spring Coffee</h1>
          <h4 class="fw-bold">회원분들에게만 다양한 혜택을 드립니다!</h4>
          <p> 스프링 커피와 함께하세요</p>
          <button class="btn btn-success" type="button">회원가입</button>
        </div>
      </div>

      <div class="carouseldiv">
        <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-label="슬라이드 1" aria-current="true"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="슬라이드 2" class=""></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="슬라이드 3" class=""></button>
          </div>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="../.././assets/content1.jpg" style="width: 100%" alt="" />
              <div class="container ">
                <div class="carousel-caption">
                  <h2 class="display-7 fw-bold">가을 맞이 이벤트 !!</h2>
                  <p>아메리카노 50% 할인 중!!</p>
                  <button class="btn btn-outline-light" type="button">쇼핑하기</button>
                </div>
              </div>
            </div>

            <div class="carousel-item">
              <img src="../.././assets/content2.jpg" style="width: 100%" alt="" />
            </div>
            
            <div class="carousel-item">
              <img src="../.././assets/content3.jpg" style="width: 100%" alt="" />
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
        <div class="h-100 p-5 text-white bg-dark rounded-3">
          <h2 class="display-7 fw-bold">가을 맞이 이벤트 !!</h2>
          <p>아메리카노 50% 할인 중!!</p>
          <button class="btn btn-outline-light" type="button">쇼핑하기</button>
        </div>

        <div class="h-100 p-5 text-dark bg-light rounded-3">
          <h2 class="display-7 fw-bold">가을 맞이 이벤트 !!</h2>
          <p>아메리카노 50% 할인 중!!</p>
          <button class="btn btn-primary" type="button">쇼핑하기</button>
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
.outterdiv {
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