import { createStore } from 'vuex'

let store = createStore({
  state(){  // 데이터보관하고 싶으면 여기에 기재
      return{
        isShow: true,
      }
  },
  actions :{ // ajax 넣는곳, 오래 걸리는 작업들 넣는곳
      
  },
  mutations:{ // 변경하길 원하는 것들은 이곳에다가 기재한다
    checkShow(state){
      state.isShow = !this.state.isShow;
    }
  },
})

export default store;