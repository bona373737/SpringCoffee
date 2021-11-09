import { createStore } from 'vuex'
import axios from "axios";


let store = createStore({
  state(){  // 데이터보관하고 싶으면 여기에 기재
    return{
      boardList: [],  // Map 형식으로 하면 boardNo 가능
      boardDetail: {},
      itemList: [],
      itemDetail: {},
      userList: [],
      userDetail: {},
      cartList: [],
      cartDetail: {},
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
    },
    setItem(state, item) {
      state.itemList = item;
    },
    setItemDetail(state, itemDetail){
      state.itemDetail = itemDetail;
    },
    setUser(state, user) {
      state.userList = user;
    },
    setUserDetail(state, userDetail){
      state.userDetail = userDetail;
    },
    setCart(state, cart) {
      state.cartList = cart;
    },
    setCartDetail(state, cartDetail){
      state.cartDetail = cartDetail;
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
    fetchUser(context) {
      axios.get(`/v0/list`)
        .then(response => {
          context.commit('setUser', response.data.dtoList);
        })
    },
    fetchUserDetail(context, userEmail){
      axios.get(`/v0/${userEmail}`)      // axios dynamic URL,
        .then(response =>{
          context.commit('setUserDetail', response.data);
        })
    },
    fetchCart(context) {
      axios.get(`/v4/cart`)
        .then(response => {
          context.commit('setCart', response.data.dtoList);
        })
    },
    fetchCartDetail(context, cartNo){
      axios.get(`/v4/${cartNo}`)      // axios dynamic URL,
        .then(response =>{
          context.commit('setItemDetail', response.data);
        })
    },
  },
})

export default store;