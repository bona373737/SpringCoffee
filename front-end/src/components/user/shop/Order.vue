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
        <span style="font-size: 11pt;">결제하기</span>
    </div><br><br>

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

<style>
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
</style>