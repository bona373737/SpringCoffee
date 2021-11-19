a<template>
<div>
    <div class="wrapper">
        <div class="py-2 text-start">
            <span v-if="isStatus" class="me-5" style="font-size:16pt; font-weight:700; height">전체 목록</span>
            <span v-if="!isStatus" class="me-5" style="font-size:16pt; color:#999 font-weight:700; height">주문 목록</span>
            <span v-if="!isStatus" style="font-size:16pt; font-weight:700; height">취소 목록</span>
            <span v-if="isStatus" style="font-size:16pt; color:#999; font-weight:700; height">취소 목록</span>
        </div>
        <div v-if="isStatus">
            

            <div class="myorder text-start border-top py-1" v-for="(order,i) in this.myOrder" :key="order.orderNo">
                <div @click="cancelOrder(order)" style="position: absolute; margin-left: 445px" v-if="order.orderStatus=='결제완료'">
                    <button class="btn btn-warning" style="font-size: 11pt">취소</button>
                </div>
                <a style="background-color: #A36043; color:white;">주문번호 </a> <a>{{order.orderNo}}</a><br>
                <a style="background-color: #4F2E20; color:white;">주문상태  </a> <a>{{order.orderStatus}}</a><br>
                <div class="d-flex text-end border-bottom border-top">
                    <span class="me-5" style="background-color:">상품</span>
                    <span class="me-5" style="">상품번호</span>
                    <span class="me-5" style="">구매수량</span>
                    <span>결제금액</span>
                </div>
                
                <div class="d-flex text-end" v-for="list in this.myOrder[i].orderItemDTOList" :key="list.itemNo">
                    <span></span>
                    <span class="me-5" style="">{{list.itemNo}}</span>
                    <span class="me-5" style="">{{list.count}}</span>
                    <span style="">{{list.allPrice}}</span><br>
                </div>
            </div>
        </div>
        <div class="navi text-center border-top">
            <div class="move">
                <span class="prev"><i @click="prevPage()"  class="bi bi-caret-left-fill"> </i></span>
                <span class="pageNum"> </span>
                <span class="next"><i @click="nextPage()" class="bi bi-caret-right-fill"> </i></span>            
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name : 'MyOrder',
  data() {
    return {
        isStatus: true,
        myOrder: [],
        myOrderList: [],
        count: 0,
        status: '',
        thumbnail: Map,
    };
  },
  created() {
    this.getMyOrderNo(this.count);
    this.$store.dispatch('fetchItem');
  },

  methods: {
    prevPage() {
      if(this.count < 1) return false;
      this.count--;
      this.getMyOrderNo(this.count)
    },
    nextPage() {
        if(this.count < this.myOrder.totalPages) {
            this.count++;
            this.getMyOrderNo(this.count)
        }
    },
    getMyOrderNo(page) {
        axios.get(`/v6/orders/${page}/${this.$store.state.email}`)
            .then(res => {
                this.myOrder=res.data.content;
                console.log('성공', res)
            }).catch(err => {
                console.log(err)
                if(this.count==0) return false;
                this.count--;
            })
    },

    cancelOrder(order) {
        if(confirm("주문을 취소하시겠습니까?") == true) {
            alert('주문이 취소되었습니다.')
            axios.post(`v6/cancel`, {
                orderNo : order.odrerNo,
                email : this.$store.state.email
            })
                .then(res => {
                console.log('success', res)
            }).catch(err => {
                console.log('failed', err)
            })
        } else {
            return false;
        }
    },
  }
}
</script>

<style scoped>

.wrapper {
    width: 500px;
    margin: auto;
}

.myorder {

    /* background-color: ; */
}

.myorder a {
    width: 100px;
    padding-left: 20px;
}

.myorder span {
    width: 100px;
    padding-left: 20px;
}

.move {
    cursor: pointer;
    font-size: 20pt;
    padding: 10px;
    color: #4F2E20;
}

.next {

}

.prev {

}
</style>