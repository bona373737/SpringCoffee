<template>
  <div class="outterdiv">
      <div class="p-5 mb-4 bg-light rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">Spring Coffee</h1>
        <p class="col-md-8 fs-4"> 스프링커피는 ~~ 입니다</p>
        <button class="btn btn-outline-success" type="button">Example button</button>
      </div>
    </div>
    <div class="row align-items-md-stretch">
      <div class="col-md-6">
        <div class="h-100 p-5 text-white bg-dark rounded-3">
          <h2>신제품</h2>
          <p> 가을 신제품이 나왔음~~ </p>
          <button class="btn btn-outline-light" type="button">Example button</button>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 bg-light border rounded-3">
          <h2> 친환경 event </h2>
          <p>스프링커피는 자연을 생각합니다~ 개인텀플러 사용 </p>
          <button class="btn btn-outline-secondary" type="button">Example button</button>
        </div>
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
          <svg class="bd-placeholder-img" width="100%" height="30%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#777"></rect></svg>

          <div class="container">
            <div class="carousel-caption text-start">
              <h2><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">예시 제목.</font></font></h2>
              <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">미국 언어 협회에 따르면 126% 증가했습니다.</font></font></p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">오늘 등록</font></font></a></p> -->
            </div>
          </div>
        </div>

        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="30%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#777"></rect></svg>

          <div class="container">
            <div class="carousel-caption">
              <h2><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">또 다른 예시 제목.</font></font></h2>
              <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">영국 문화원에 따르면 하는 데 기여합니다.</font></font></p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">나는 더 알고있다</font></font></a></p> -->
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="30%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="#777"></rect></svg>

          <div class="container">
            <div class="carousel-caption text-end">
              <h2><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">좋은 측정을 위해 하나 더.</font></font></h2>
              <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">영국 이외의 언어 정치적 수준에서도 계산된다고 믿습니다.</font></font></p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">갤러리 찾아보기</font></font></a></p> -->
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
  </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.outterdiv{
  width: 80%;
  margin: auto;
}
.carouseldiv{
  margin-top: 2%;
  margin-bottom: 2%;
}

</style> 