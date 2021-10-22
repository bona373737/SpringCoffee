<template>
<div>
  <Header></Header>
  <Body></Body>
  <Footer></Footer>
</div>
</template>

<script>
import Header from './components/Header.vue'
import Body from './components/Body.vue'
import Footer from './components/Footer.vue'
export default {
  name: 'App',
  components: {
    Header,
    Body,
    Footer,

  }
}
</script>

<style>
#app {
  font-family: Helvetica, '나눔고딕', 'Nanum Gothic', '나눔스퀘어', 'Nanum Square', 'Apple SD Gothic Neo', 'Malgun Gothic', '맑은 고딕', Dotum, '돋움', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  -webkit-user-select:none;
  -moz-user-select:none;
  -ms-user-select:none;
  user-select:none;
}
</style>
