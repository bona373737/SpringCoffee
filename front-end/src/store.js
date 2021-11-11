import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){  // 데이터보관하고 싶으면 여기에 기재
    return {
      email: "user95@springCoffee.com",
      cartNo: '',
      boardList: [],  // Map 형식으로 하면 boardNo 가능
      boardDetail: {},
      itemList: [],
      itemDetail: {},
      cartList: [],
      cartDetail: {},
    }
  },
  mutations: { // 변경하길 원하는 것들은 이곳에다가 기재한다
    setMember(state, email) {
      state.email = email;
    },
    setBoard(state, board) {
      state.boardList = board;
    },
    setBoardDetail(state,boardDetail){
      state.boardDetail = boardDetail;
    },
    setItem(state, item) {
      state.itemList = item;
    },
    setItemDetail(state, itemDetail){
      state.itemDetail = itemDetail;
    },
    setCart(state, cart) {
      state.cartList = cart;
      // state.cartNo = cart.cartItemNo;
    },
  },
  actions: { // ajax 넣는곳, 오래 걸리는 작업들 넣는곳
    fetchBoard(context){
      axios.get(`/v1/list`)
          .then(response => {
            context.commit('setBoard', response.data.dtoList);
          })
    },
    fetchBoardDetail(context, boardNo) {
      axios.get(`/v1/${boardNo}`)      // axios dynamic URL,
          .then(response =>{
            context.commit('setBoardDetail', response.data);
          })
    },
    fetchItem(context) {
      axios.get(`/v2/list`)
        .then(response => {
          context.commit('setItem', response.data.dtoList);
        })
    },
    fetchItemDetail(context, itemNo){
      axios.get(`/v2/list/${itemNo}`)      // axios dynamic URL,
        .then(response =>{
          context.commit('setItemDetail', response.data);
        })
    },
    fetchCart(context, email) {
      axios.get(`/v4/cart/${email}`)
        .then(response => {
          context.commit('setCart', response.data);
        })
    },
    // patchCart(context, cartItemNo, count) {
    //   axios.get(`/v4/${cartItemNo}/${count}`)
    //     .then(response => {
    //       context.commit('setCart', response.data);
    //     })
    // },
  },
})

export default store;