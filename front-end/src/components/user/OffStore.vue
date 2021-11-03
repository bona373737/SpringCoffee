<template>
  <div class="wrap">
    <div class="tab-bar">
      <div class="tab-map py-3" style="width:100%;">
        <span class="tab-title">오시는 길</span>
      </div>
    </div>
    <div class="py-2"><br></div>
      <span class="info"><i class="bi bi-signpost"></i></span><br>
      <span style="font-size: 11pt;">오프라인 스토어를 안내해드립니다!</span><br>
      <img src="../.././assets/offstore.png" alt="" style="border-radius: 15px;"><br><br>
      <div class="details">
        <span class="det-text"><i class="bi bi-map-fill"></i> 주소: 서울특별시 송파구 가락동 중대로 135</span><br>
        <span><i class="bi bi-telephone-fill"></i> 연락처: 02-2188-6900</span>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>

.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-map {
  background-color: rgba(0, 0, 0, 0.5);
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.wrap .details {
    padding: 30px;
    width: 400px;
    margin: auto;
    font-size: 11pt;
    border: 1px #333 solid;
    background-color: ivory;
    border-radius: 15px;
    text-align: left;
    font-weight: 600;
}

img {
    padding-top: 20px;
}

.info {
    font-size: 26pt;
}

</style>
