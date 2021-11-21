<template>
<footer class="page-footer font-small stylish-color-dark pt-5 border-top bg-light">
  <table class="footer-content">
    <colgroup>
      <col width=85%/>
      <col width=15%/>
    </colgroup>
    <tr>
      <td class="about">
        <div>
          <h4>Spring Coffee</h4>
          상호 : 스프링커피 | 사업자 : 은낯가<br>
          사업자등록번호 : 000-00-00000 | 이메일 : sc@springCoffee.com<br>
          주소 : 서울특별시 송파구 가락동 중대로 135
        </div>
      </td>
      <td class="nav">
        <div>
          <ul>
            <li @click="$router.push('/shop')">SHOP</li>
            <li @click="$router.push('/noticeLayout')">NOTICE</li>
            <li @click="$router.push('/qnaLayout')">Q&A</li>
            <li @click="$router.push('/offstore')">STORE</li>
          </ul>
        </div>
      </td>
    </tr>
  </table>
  <div class="footer-copyright text-center py-4">© 2021 Copyright:
    <a>Kosa-3team</a>
  </div>
</footer>
</template>

<script>
export default {
}
</script>

<style>
footer {
  width: 100%;
  bottom: 0;
  margin-top: 85px;
  color: #4F2E20;
}

.bg-light {
  padding: 0;
}

.footer-content{
  width: 80%;
  margin: auto;
}

.footer-content tr {
  text-align: left;
}

.footer-content td {
 
  height: 100px;
  color: #4F2E20;
}

.footer-content .about{
  border-right: 1px solid #666;
}

.footer-content .nav ul {
  list-style: none;
}

.footer-content .nav ul li:hover{
  color: #A36043;
}
</style> 