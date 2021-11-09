<template>
<div>
    <div class="outterDiv py-5">
      <table class="notice-context">
        <tr><td class="table-title">제목 {{$store.state.cartDetail.title}} </td></tr>
        <tr><td class="table-context"> {{$store.state.cartDetail.content}} </td></tr>
        <tr><td class=""> 작성일 </td></tr>
      </table>
      <br>
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