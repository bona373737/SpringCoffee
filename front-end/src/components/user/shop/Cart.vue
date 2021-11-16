<template>
  <div class="outterDiv">
    <div class="tab-bar">
        <div class="tab-shop py-3" style="width:100%;">
            <span @click="this.$store.dispatch('fetchCart')" class="tab-title">장바구니</span>            
        </div>
    </div>
    <div class="py-2"><br></div>
    <div class="align-items-center justify-content-center justify-content-lg-end">
        <span class="bag"><span class="bag "><i class="bi bi-bag-check-fill"></i></span><br></span><br>
        <span style="font-size: 11pt;">장바구니에 담아서 결제하세요!</span>
    </div><br><br>

    <CartDetail></CartDetail>
  </div>
</template>

<script>
import CartDetail from './CartDetail.vue'

export default {
  name : 'Cart',
  components: {
      CartDetail
  },
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

.bag {
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