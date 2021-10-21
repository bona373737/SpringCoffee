<template>
<header>
  <div class="px-3 py-2 border-bottom mb-2">
    <div class="container d-flex flex-wrap justify-content-center">
      <div class="text-end col-12 mb-2 mb-lg-0 me-lg-auto">
        <span type="button" class="btn btn-light text-dark me-2">로그인</span>
        <span type="button" class="btn btn-primary">회원가입</span>
      </div>
    </div>
  </div>

  <div class="logo">
    <a href="#">
      <img src="../assets/temp.png" alt="">
    </a>
  </div>

  <nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="#">커피</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">텀블러</a>
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
</template>

<script>
export default {

}
</script>

<style>
header {
    width: 100%;
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