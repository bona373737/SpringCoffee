<template>
  <div class="outterDiv">
    <table class="table table-bordered table-condensed">
      <colgroup>
        <col width="10%" />
        <col width="20%" />
        <col width="45%" />
        <col width="35%" />
      </colgroup>
      <thead>
      <tr>
        <th>주문번호</th>
        <th>주문상태</th>
        <th>구매상품</th>
        <th>주문상태변경</th>
      </tr>
      </thead>
      <tbody>
      <tr class="tbody-th1" v-for="order in this.$store.state.adminOrderList.content" :key="order.orderNo">
        <th>{{ order.orderNo }}</th>
        <th>{{ order.orderStatus }}</th>
        <th>
          <ul>
            <li v-for="item in order.orderItemDTOList" :key="item.itemNo">
              제품번호:{{ item.itemNo }},
              주문수량:{{item.count}},
              총금액:{{item.allPrice}}</li>
          </ul>
        </th>
        <th>
          <div class="status">
            <select :value="order.orderStatus" :id="order.orderNo" @change="changeOrderStatus">
              <option value="ORDER">주문접수</option>
              <option value="PREPARE">상품준비중</option>
              <option value="SHIPPING">배송중</option>
              <option value="DONE">배송완료</option>
            </select>
          </div>
        </th>
      </tr>
      </tbody>
    </table>

    <div class="page-btn">
      <button class="btn btn-outline-secondary btn-sm" :disabled="this.$store.state.adminOrderList.first">이전</button>
      <button class="btn btn-outline-secondary btn-sm btn-page" v-for="page in this.$store.state.adminOrderList.totalPages" :key="page"
              :class="{pageNo : page === this.$store.state.adminOrderList.number}"
              @click="movePage(page)">{{page}}</button>
      <button class="btn btn-outline-secondary btn-sm" :disabled="this.$store.state.adminOrderList.last">다음</button>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  mounted() {
    this.$store.dispatch('fetchAdminOrderList')
  },
  data(){
    return{
      orderStatus: '',
      // realPage: this.page -1,
    }
  },
  methods:{
    orderStatusPrepare(orderNo){
      axios.post('/v6/prepare',null,{params:{orderNo:orderNo}})
          .then(res =>{
            console.log(res.data)
            this.$store.dispatch('fetchAdminOrderList')
          })
    },
    orderStatusShipping(orderNo){
      axios.post('/v6/shipping',null,{params:{orderNo:orderNo}})
          .then(res =>{
            console.log(res.data)
            this.$store.dispatch('fetchAdminOrderList')
          })
    },
    orderStatusDone(orderNo){
      axios.post('/v6/done',null,{params:{orderNo:orderNo}})
          .then(res =>{
            console.log(res.data)
            this.$store.dispatch('fetchAdminOrderList')
          })
    },
    changeOrderStatus(e){
      const orderNo = e.target.id;
      const orderStatus = e.target.value;

      if(orderStatus === 'PREPARE'){
        this.orderStatusPrepare(orderNo)
      }else if(orderStatus ==='SHIPPING'){
        this.orderStatusShipping(orderNo)
      }else if(orderStatus === 'DONE'){
        this.orderStatusDone(orderNo)
      }
    },
    movePage(page){
      const realPage = page -1
      this.$store.dispatch('fetchAdminOrderList',realPage)
    }
  }
}
</script>

<style scoped>
.outterDiv{
  margin-top: 50px;
}

.table{
  width: 90%;
  margin: auto;
}

th{
  vertical-align: middle;
  text-align: center;
}

ul{
  list-style: none;
}

.btn-outline-success{
  margin-right: 5px;
  color: #4F2E20;
  border: 2px solid #4F2E20;
}

.btn-outline-success:hover{
  border: 2px solid #A36043;
  background: #A36043;
}

.page-btn{
  padding: 20px;
}

.page-btn .btn-sm {
  width: 40px;
  height: 35px;
}

.page-btn :first-child, .page-btn :last-child{
  width: 50px;
  border: 2px solid #663C2A;
  color: #663C2A;
  font-weight: bold;
  margin: 5px 5px;
}

.page-btn :first-child:hover, .page-btn :last-child:hover{
  border: 2px solid #663C2A;
  background-color: #663C2A;
  color: white;
}

.page-btn .btn-page{
  border: none;
}

.page-btn .btn-page:hover{
  background: #A36043;
  color: white;
}

.pageNo{
  background: #663C2A;
  color: white;
  font-weight: bold;
}

.status{
  float: left;
  padding-top: 3px;
  margin-left: 20px;
}

.status select {
  text-align: center;
  width: 100%;
  margin: 2px 10px 0px 10px;
  height: 30px;
  border-color: #4F2E20;
  border-radius: 5px;
  color: #4F2E20;
}

</style>