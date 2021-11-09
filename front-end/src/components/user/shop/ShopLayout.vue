<template>
    <div>
        <div class="tab-bar">
            <div class="tab-shop py-3" style="width:100%;">
                <span class="tab-title">상품목록</span>            
            </div>
        </div>
        <div class="py-2"><br>
        </div>
        <div class="align-items-center justify-content-center justify-content-lg-end">
          <span class="bag" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><span class="bag "><i class="bi bi-bag-check-fill"></i></span><br></span><br>
          <span style="font-size: 11pt;">장바구니에 담아서 결제하세요!</span>
        <div class="text-end">
          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
              <h5 id="offcanvasRightLabel">장바구니</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
              <tr v-for="cart in myCartlist" :key="cart">
                <td>{{ cart.image }}</td>
                <td>{{ cart.title }}</td>
                <td>{{ cart.price }}</td>
              </tr>
              <button type="button" class="btn btn-success"> 구매하기 </button>
            </div>
          </div>
        </div>
      </div>
    <div class="outter-border">
        <div class="ProductListTechnologies d-flex py-5">
            <router-link to="#">
                <div class="ProductListTechnologies__element me-2">
                    <img width="120" height="80" src="../../../assets/shop1.webp" role="presentation" class="" alt="">
                    <div class="ProductListTechnologies__name">오리지널</div>
                </div>
            </router-link>
            <router-link to="#">
                <div class="ProductListTechnologies__element me-2">            
                    <img width="120" height="80" src="../../../assets/shop2.webp" role="presentation" class="" alt="">
                    <div class="ProductListTechnologies__name">버츄오</div>
                </div>
            </router-link>
        </div>

        <table class="table">
            <colgroup>
                <col width="30%" />
                <col width="40%" />
                <col width="10%" />
                <col width="20%" />
            </colgroup>
            <tr class="product-item">
                <td> <img src="" alt="상품img"></td>
                <td> 상품명 </td>
                <td> 가격 </td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                   <button type="button" class="btn btn-success"> - </button>
                   <button type="button" class="btn btn-success"> + </button>
                   </div>
                </td>
            </tr>
            <tr class="product-item" v-for="item in this.$store.state.itemList" v-bind:key="item.itemId" @click="goItemDetail(item.itemNo)">
                <td> <img width="120" height="80" ref="imageOutput" :src="item.image"> </td>
                <td> {{item.title}}</td>
                <td> {{item.content}}</td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-success" @click="disCart(item)"> - </button>
                    <button type="button" class="btn btn-success" @click="addCart(item)"> + </button>
                   </div>
                </td>
            </tr>
        </table>
    </div>
    </div>
</template>

<script>
export default {
    name:'CoffeeProductList',
  created() {
    this.$store.dispatch('fetchItem');
  },
  data() {
    return {
        count: 0,
        myCartlist: [
            {
                image : "?",
                name : "ddd",
                price : "123",
            },
            {
                image : "?",
                name : "ccc",
                price : "321",
            }
        ],

    };
  },

  methods: {
    goItemDetail(itemNo) {
      this.$router.push({
        name: 'itemDetail',
        params: { itemNo: itemNo }
      })
    },
    imageOutput() {

    },
    addCart(item, i) {
        if(this.myCartlist)
        this.myCartlist[i].id = item.itemNo;
        this.myCartlist[i].image = item.image;
        this.myCartlist[i].price = item.price;
        this.myCartlist[i].count++;
    },
    disCart(item, i) {
        localStorage
        if(this.myCartlist[i].count == 0)
            this.myCartlist.slice(i);
        else if(this.myCartlist[i].count > 0) {
            this.myCartlist[i].count--;
        }
    }
  },
};
</script>

<style scoped>
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

.bag:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.outter-border{
    width: 60%;
    margin: auto;
}
.product-item {
    border: 0;

}
.product-item:hover{
    background-color: beige;
    border: 0;
}
.table{
    height: 60%
}

.nav-link {
    text-decoration: none;
}

</style>