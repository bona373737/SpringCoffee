import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){  // 데이터보관하고 싶으면 여기에 기재
    return{
      isShow: true,
      boardList: [],  // Map 형식으로 하면 boardNo 가능
      boardDetail: {},
    }
  },
  mutations: { // 변경하길 원하는 것들은 이곳에다가 기재한다
    checkShow(state) {
      state.isShow = !this.state.isShow;
    },
    setBoard(state, board) {
      state.boardList = board;
    },
    setBoardDetail(state,boardDetail){
      state.boardDetail = boardDetail;
    }
  },
  actions: { // ajax 넣는곳, 오래 걸리는 작업들 넣는곳
    fetchBoard(context){
      axios.get(`/v1/list`)
          .then(response => {
            context.commit('setBoard', response.data.dtoList);
          })
    },
    fetchBoardDetail(context, boardNo){
      axios.get(`/v1/${boardNo}`)      // axios dynamic URL,
          .then(response =>{
            context.commit('setBoardDetail', response.data);
          })
    }
  },
})

export default store;