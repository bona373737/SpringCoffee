<template>
<div>
  <div class="outterDiv py-5">
    <div>
      <tr class="product-item text-center" v-for="cart in this.$store.state.cartList" v-bind:key="cart.cartItemNo" >
                <td> {{cart.itemName}}</td>
                <td> {{cart.price}}</td>
                <td>
                  <span class="plus" ><i @click="setCountP(cart), patchCart(cart)" class="bi bi-plus-circle"></i></span>
                  {{cart.count}}
                  <span class="minus"><i @click="setCountM(cart), patchCart(cart)" class="bi bi-dash-circle"></i></span>
                </td>
                <td v-bind="sumPrice(cart.price, cart.count)"> {{sum}}</td>
            </tr>
            <span style="font-weight: 800; font-size: 16pt;"> {{price}} 원</span>
    </div>

    
    <div class="BtnWrap">
      <button class="btn btn-success" @click="cartDelete(this.$route.params.cartNo)"> 삭제 </button>
      <button class="btn btn-success" @click="goCartUpdate(this.$route.params.cartNo)"> 수정 </button>
      <button class="btn btn-success" @click="$router.push({name : 'noticeList'})"> 목록으로 가기 </button>
    </div>
    <br>
  </div>
  <button type="button" class="btn btn-success"> 구매하기 </button>
</div>
</template>

<script>
export default {

}
</script>

<style>

</style>