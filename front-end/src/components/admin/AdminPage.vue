<template>
  <div class="outer">
      <div v-if="this.$store.state.role !== 'ROLE_ADMIN'" v-on="this.$router.replace('NotfoundPage')"></div>
      <div class="adminpage" v-if="this.$store.state.role ==='ROLE_ADMIN'">
        <div class="tab-bar">
            <div class="tab-my py-3" style="width:100%;">
                <span class="tab-title">관리자 페이지</span>            
            </div>
        </div>
        <div class="py-2">
          <div style="height: 48px;"/>
          <span class="info"><i class="bi bi-gear-fill"></i></span><br>
          <span class="info-text">관리자 페이지입니다</span>
        </div>

          <div class="bt-menu" >
              <ui class="bt-info py-2">
                  <li class="content" style="margin-right:15px" @click="$router.replace('/userList')">
                      <div class="con-card">
                        <div class="bt-icon"><i class="bi bi-person-circle"></i></div>
                            <div class="bt-text">회원관리</div>
                      </div>
                  </li>
                  <li class="content" style="margin-right: 5px" @click="$router.push('/itemadd')">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-shop"></i></div>
                        <div class="bt-text">상품등록</div>
                      </div>
                  </li>
                  <li class="content" style="margin-right: 5px" @click="$router.replace('/adminQna')">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-question-circle"></i></div>
                      <div class="bt-text">문의목록</div>
                      </div>
                  </li>
                  <li class="content" @click="$router.push('/adminOrder')">
                      <div class="con-card">
                      <div class="bt-icon"><i class="bi bi-currency-dollar"></i></div>
                      <div class="bt-text">주문관리</div>
                      </div>
                  </li>
              </ui>


          </div>
              <div class="rout">
                <router-view></router-view>
              </div>
      </div>

  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.outer {
  background-color: #f7f7f7;
}

.rout {
    padding: 20px;
    width:80%;
    /*height:100%;*/
    border: none;
    margin: auto;
    background-color: white;
}

.tab-title {
  font-weight: bold;
  font-size: 25pt;
  color: white;
  display: table-cell;
  vertical-align: middle;
}

.tab-bar {
  background-image: url('../.././assets/background.jpg');
  background-repeat : no-repeat;
  background-size : cover;
  position: relative;
}

.tab-my {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 150px;
  display: table;
}

.tab-title {
  font-size: 22pt;
  color: white;
}

.py-2 {
  width: 80%;
  margin: auto;
  background-color: white;
}

.info {
  font-size: 30pt;
  color: #663C2A;
}

.info:hover {
  color: #A36043;
  transition: 0.3s;
}

.info-text {
  font-size: 12pt;
  color: #4F2E20;
  display: block;
}

.bt-menu {
  width: 80%;
  padding: 50px;
  margin: auto;
  background-color: white;
  border-bottom: 1px solid #4F2E20;
}

.bt-menu .bt-info {
  display: flex;
  justify-content: center;
  text-align: center;
  margin: auto;
  list-style-type : none;
}

.bt-menu .bt-info li {
  color: #4F2E20;
  text-align: center;
  margin: auto;
}

.bt-menu .bt-info .bt-icon {
  font-size: 37pt;
}

.content {
  display: flex;
  
  /* justify-content: center; */
  text-align: center;
}

.content .con-card {
  width: 100px;
  height: 100px;
  background-color: white;
  border: 2px solid #4F2E20;
  border-radius: 10px;
}

.content .con-card:hover {
  cursor: pointer;
  color: #A36043;
  border: none;
  box-shadow: 1px 1px 10px #333;
  transition: 0.2s;
}

</style>