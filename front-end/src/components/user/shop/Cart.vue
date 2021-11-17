<template>
  <div class="outterDiv">
    <div v-if="this.$store.state.isLogin">
      <div class="py-2"><br></div>
      <CartDetail></CartDetail>
    </div>
    <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
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
</style>