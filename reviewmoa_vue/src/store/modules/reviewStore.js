 
const reviewStore = {
    namespaced: true,
    state: {
      reviewList: [],
      selectIdx:null,
      info:[],
      isUpdate:false,
      sort:"postId",
      genre:"모든 장르",
      searchWord:""

      
    },
    getters: {},
    mutations: {
      SET_REVIEW_LIST(state, reviewList) {
        state.reviewList=reviewList;
      },
      SET_SELECT_IDX(state,index){
        state.selectIdx=index;
      },
      SET_UPDATE_INFO(state,info){
        state.info=info;
      },
      SET_IS_UPDATE(state,isUpdate){
        state.isUpdate=isUpdate
      },
      SET_SORT(state,sort){
        state.sort=sort;
      },
      SET_GENRE(state,genre){
        state.genre=genre;
      },
      SET_SEARCH(state,searchWord){
        state.searchWord=searchWord;
      }
      
      
    },
    actions: {
      
    },
    
  };
  
  export default reviewStore;