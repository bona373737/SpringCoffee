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
        <!--        <th>No.</th>-->
        <th>주문번호</th>
        <th>주문상태</th>
        <th>구매상품</th>
        <th></th>
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
          <button class="btn btn-outline-success btn-sm" > button </button>
          <button class="btn btn-outline-success btn-sm" > button </button>
        </th>
      </tr>
      </tbody>
    </table>

    <div class="page-btn">
      <button class="btn btn-outline-secondary btn-sm" >&lt;&lt;</button>
      <button class="btn btn-outline-secondary btn-sm btn-page" v-for="page in this.$store.state.adminOrderList.totalpages" :key="page"
              :class="{pageNo : page === this.$store.state.adminOrderList.pageNo}"
              @click="movePage(page)">{{page}}</button>
      <button class="btn btn-outline-secondary btn-sm">>></button>
    </div>

  </div>
</template>

<script>
export default {
  mounted() {
    this.$store.dispatch('fetchAdminOrderList')
  }
}
</script>

<style>
.outterDiv{
  margin-top: 50px;
}

.table{
  width: 90%;
  margin: auto;
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
</style>