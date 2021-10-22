<template>
<div>
  <Header></Header>
  <Body></Body>

  <!-- <Register></Register> -->
  <Login></Login>
  <RegisterForm></RegisterForm>

  <Footer></Footer>

</div>
</template>

<script>
import Header from './components/Header.vue'
import Body from './components/Body.vue'
import Footer from './components/Footer.vue'
import RegisterForm from './components/RegisterFrom.vue'
import Login from './components/Login.vue'

export default {
  name: 'App',
  components: {
    Header,
    Body,
    Footer,
    RegisterForm,
    Login,
  }
}
</script>

<style>
#app {
  font-family: Helvetica, '나눔고딕', 'Nanum Gothic', '나눔스퀘어', 'Nanum Square', 'Apple SD Gothic Neo', 'Malgun Gothic', '맑은 고딕', Dotum, '돋움', sans-serif;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none;
  /* margin-top: 60px; */
}
</style>