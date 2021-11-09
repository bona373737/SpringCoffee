<template>
  <div>
    asd
    <!-- <div class="outterDiv py-5">
      <table class="notice-context">
        <tr><td class="table-title">제목 {{$store.state.itemDetail.title}} </td></tr>
        <tr><td class="table-context"> {{$store.state.itemDetail.content}} </td></tr>
        <tr><td class=""> 작성일 </td></tr>
      </table>
      <br>
      <div class="BtnWrap">
        <button class="btn btn-success" @click="itemDelete(this.$route.params.itemNo)"> 삭제 </button>
        <button class="btn btn-success" @click="goItemUpdate(this.$route.params.itemNo)"> 수정 </button>
        <button class="btn btn-success" @click="$router.push({name : 'itemList'})"> 목록으로 가기 </button>
      </div>
      <br>
    </div> -->
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'itemDetail',

  data(){
    return{

    }
  },
  created() {
    this.$store.dispatch('fetchItemDetail',this.$route.params.itemNo); // board_no -> 글번호에 해당하는 내용을 가져와라!
  },
  methods:{
    goItemUpdate(itemNo){
      this.$router.push({
        name: 'itemUpdate',
        params: { itemNo: itemNo }
      })
    },
    itemDelete(itemNo){
      axios.delete(`/v4/${itemNo}`)
      .then( res => {
        console.log(res.data)
        this.$router.push('#');
      })
    }
  }
};

</script>

<style scoped>
.outterDiv{
    width: 60%;
    margin: auto;
}
.notice-context{
  width: 100%;
  text-align: left;
}
.table-title{
  padding: 15px;
  border-top: 2px solid #444444 ;
  border-bottom: 2px solid #444444 ;
}
.table-context{
  padding: 10px;
  height: 300px;
  border-bottom: 1px solid  #ddd;;
}
.BtnWrap{
  margin-bottom: 5px;
  margin-top: 5px;
}
.updown-page{
  width: 100%;
  border-top: 1px solid #444444;
  border-bottom: 1px solid #444444 ;
  text-align: left;

}
.goToUpperList{
  border-bottom: 1px solid  #ddd;
  text-align: left;
}
</style>