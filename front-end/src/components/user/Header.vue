<template>
  <!-- header type1 -->
  <header class="p-3 bg-dark text-white">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="25" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><img src="../.././assets/springlogo.jpg" alt="Img" style="width:60px"></li>
          <li><a href="#" class="nav-link px-2 text-secondary" @click="$router.push('path:/')">Home</a></li>
          <li><a href="#" class="nav-link px-2 text-white" @click="$router.push('path:/shop')">Shop</a></li>
          <li><a href="#" class="nav-link px-2 text-white">FAQs</a></li>
          <li><a href="#" class="nav-link px-2 text-white" @click="$router.push('path:/noticeForm')">공지사항</a></li>
        </ul>

        <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
        </form>

        <div class="text-end">

          <button class="btn btn-outline-light me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight" aria-controls="offcanvasRight">장바구니</button>
          <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight" aria-labelledby="offcanvasRightLabel">
            <div class="offcanvas-header">
              <h5 id="offcanvasRightLabel">Offcanvas right</h5>
              <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
              <ul>
                <li></li>
                <li></li>
              </ul>
              <button type="button" class="btn btn-success"> 구매하기 </button>
            </div>
          </div>



          <button type="button" class="btn btn-outline-light me-2" @click="$router.push('path:/login')">Login</button>
          <button type="button" class="btn btn-success" @click="$router.push('path:/register')">Sign-up</button>
        </div>
      </div>
    </div>

  </header>


  <!-- header type2 -->
  <!--<header>
   <div class="px-3 py-2 border-bottom mb-2">
    <div class="container d-flex flex-wrap justify-content-center">
      <div class="text-end col-12 mb-2 mb-lg-0 me-lg-auto">
        <span type="button" class="btn btn-light text-dark me-2" @click="$router.push('path:/login')">로그인</span>
        <span type="button" class="btn btn-primary" @click="$router.push('path:/registerForm')" >회원가입</span>
      </div>
    </div>
  </div>

  <div class="logo">
    <a href="#">
      <img src="../assets/temp.png" alt="" @click="$router.push('path:/')">
    </a>
  </div>

  <nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link"  @click="$router.push('path:/coffeeProductList')">커피</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">텀블러</a>
        </li>
         <li class="nav-item">
          <a class="nav-link" @click="$router.push('path:/NoticeForm')">공지사항</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
  </nav>
</header>
-->
</template>


<script>
export default {
 
}
</script>


<style scoped>
header {
    width: 100%;
    margin-bottom: 1%
}
header .logo img {
    width: 250px;
}
header .navbar {
    top: 50px;
    margin: 0 auto;
    width: 80%;
}
header .navbar .container-fluid {
    padding: 5px;
}

</style> 