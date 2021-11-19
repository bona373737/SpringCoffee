<template>
  <div class="outterDiv">
    <div v-if="this.$store.state.isLogin">
      <div class="py-2"><br></div>
      <Order></Order>
    </div>
    <div v-if="!this.$store.state.isLogin" v-on="this.$router.replace('NotfoundPage')"></div>
  </div>
  
</template>

<script>
import Order from './Order.vue'

export default {
  name : 'Cart',
  components: {
    Order
  },
}
</script>

<style scoped>
</style>