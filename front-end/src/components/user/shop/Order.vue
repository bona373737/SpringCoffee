<template>
<div>
  <div class="outterDiv" v-if="this.$store.state.isLogin">
    <div class="tab-bar">
        <div class="tab-shop py-3" style="width:100%;">
            <span @click="this.$store.dispatch('fetchCart')" class="tab-title">주문하기</span>            
        </div>
    </div>
    <div class="py-2"><br></div>
    <div class="align-items-center justify-content-center justify-content-lg-end">
        <span class="buy"><span class="bag"><i class="bi bi-currency-dollar"></i></span><br></span><br>
        <span style="font-size: 11pt;"></span>
    </div><br><br>

    <div class="wrapper">
      <span style="font-size: 14pt; font-weight: 700;">배송 정보</span>
      <div class="m-info border-top border-bottom">
        <div class="infoform">
          <div class="info d-flex">
            <span class="info1 text-end me-3">이름</span>
            <span class="info2 text-start">asasd</span>
          </div>
          <div class="info d-flex">
            <span class="info1 text-end me-3">배송지</span>
            <span class="info2 text-start">asasd</span>
          </div>
        </div>
      </div>
    </div>
    <div class="py-5"></div>
    <CartDetail></CartDetail>
  </div>
  <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
</div>
</template>

<script>
import CartDetail from './CartDetail.vue';

export default {
  name: 'Order',
  components: {
    CartDetail,
  }
}
</script>

<style scoped>
.tab-bar {
  background-image: url('../../../assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
}

.buy {
    font-size: 26pt;
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.outterDiv {
    width: 100%;
    height: 100%;
}

.wrapper {
  width: 100%;
}

.m-info {
  width:600px;
  margin: auto;
}

.infoform {
  width:100%;
}

.info {
  height: 30px;
}

.info1 {
  width: 300px;
}

.info2 {
  width:100%;
}
</style>