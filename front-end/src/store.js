import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){
    return{
      isShow: true,
      boardList: [],
      boardDetail: {},
      qnaBoardList: [],
      qnaBoardDetail : {},
    }
  },
  mutations: {
    checkShow(state) {
      state.isShow = !this.state.isShow;
    },
    setBoard(state, payload) {
      state.boardList = payload;
    },
    setBoardDetail(state,payload){
      state.boardDetail = payload;
    },
    setQnaBoardList(state, payload){
      state.qnaBoardList = payload;
    },
    setQnaBoardDetail(state,payload){
      state.qnaBoardDetail = payload
    }
  },
  actions: {
    fetchBoard(context){
      axios.get(`/v1/list`)
          .then(response => {
            context.commit('setBoard', response.data.dtoList);
          })
    },
    fetchBoardDetail(context, boardNo){
      axios.get(`/v1/${boardNo}`)
          .then(response =>{
            context.commit('setBoardDetail', response.data);
          })
    },
    fetchQnaBoardList(context){
      axios.get('/v3/list')
          .then(response =>{
            context.commit('setQnaBoardList', response.data.dtoList);
          })
    },
    fetchQnaBoardDetail(context,qnaBoardNo){
      console.log(qnaBoardNo)
      axios.get(`/v3/${qnaBoardNo}`)
          .then(response => {
            context.commit('setQnaBoardDetail',response.data)
          })
    }

  },
})

export default store;