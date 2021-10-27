<template>
<div>
  <div class="tab-bar">
      <div class="tab-shop py-3" style="width:100%;">
          <span class="tab-title">로그인</span>            
      </div>
  </div>
  <div class="py-2"><br></div>
  <span class="qna"><i class="bi bi-person-fill"></i></span><br>
  <span style="font-size: 11pt;">Spring Coffee 회원 시스템 로그인입니다!</span>
  
  <div class="outterborder">
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
</div>

</template>


<script>
export default {
  name: "LogIn"
}
</script>


<style scoped>
.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-shop {
  background-color: rgba(0, 0, 0, 0.5);
}

.tab-title {
    font-size: 22pt;
    color: white;
}

.qna {
    font-size: 26pt;
}

.qna:hover {
    color: green;
    font-size: 26pt;
    transition: 0.3s;
}

.outterborder{
  width: 40%;
  margin: auto;
  padding: 20px;
}

</style>