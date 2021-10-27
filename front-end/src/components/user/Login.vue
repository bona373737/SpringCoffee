<template>
  <div class="outterborder">
    <h2>로그인</h2><br>
    <form>
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1">
      </div><br>
    
      <button type="submit" class="btn btn-outline-success" style="width:50%">Log In</button>
    </form><br>
    <div>
    <a href=""><span class="text-success">아이디찾기 | </span></a>
    <a href=""><span class="text-success">비밀번호찾기 | </span></a>
    <a href=""><span class="text-success">회원가입</span></a>
    </div>
  </div>

</template>


<script>
export default {
  name: "LogIn"
}
</script>


<style scoped>

.outterborder{
  width: 30%;
  margin: auto;

}
.logo{
  width: 20%;
  height: 20%;
}

</style>