<template>
    <div class="outter-border">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">Coffee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Tumblr</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">tab3</a>
            </li>
            <li class="nav-item">
                <a class="nav-link">tab4</a>
            </li>
        </ul>
        <hr>
        

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
                   <button type="button" class="btn btn-success"> 장바구니 </button>
                   <button type="button" class="btn btn-success"> + </button>
                   </div>
                </td>
            </tr>
             <tr class="product-item">
                <td> <img src="" alt="상품img"></td>
                <td> 상품명 </td>
                <td> 가격 </td>
                <td>
                   <div class="btn-group" role="group" aria-label="Basic example">
                   <button type="button" class="btn btn-success"> - </button>
                   <button type="button" class="btn btn-success"> 장바구니 </button>
                   <button type="button" class="btn btn-success"> + </button>
                   </div>
                </td>
            </tr>
        </table>
    </div>

</template>

<script>
export default {
    name:'CoffeeProductList'

}
</script>

<style scoped>
.outter-border{
    width: 60%;
    margin: auto;
}
.product-item {
    border: 0;

}
.product-item:hover{
    height: 15%;
    background-color: beige;
    border: 0;
}
.table{
    height: 60%
}


</style>