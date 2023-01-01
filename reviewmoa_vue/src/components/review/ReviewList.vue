<template>
  <div class="home">
     
    <b-card-group class="listBox">
      <div v-for="(item, index) in reviewList" :key="index" :item="item">
        <review-item :itemIdx="index" class="item" />
      </div>
       
    </b-card-group>
     <b-button variant="outline-secondary" id="moreBtn" @click="nextList()">더보기</b-button>
  </div>
</template>

<script>
import ReviewItem from "./ReviewItem.vue";
import axios from "axios";
import { mapState, mapMutations } from "vuex";
const reviewStore = "reviewStore";
export default {
  name: "ReviewList",
  components: {
    ReviewItem,
  },
  data() {
    return {
      itemIdx: "",
      reviewList: [],
      page:0,
    };
  },
  created() {
    this.getList();
  },
  computed: {
    ...mapState(reviewStore, ["sort"]),
    ...mapState(reviewStore, ["genre"]),
    ...mapState(reviewStore, ["searchWord"]),
 
  },
  watch: {
    sort() {
     this.init();
    },
    genre(){
      this.init();
    },
    searchWord(){
      this.init();
    }
  },
  methods: {
    ...mapMutations(reviewStore, ["SET_REVIEW_LIST"]),
    ...mapMutations(reviewStore, ["SET_SELECT_IDX"]),
    getList() {
      console.log("searchWord:"+this.searchWord);
      axios
        .get(
          process.env.VUE_APP_ROOT_URL +
            "/board/list?page="+this.page+"&sort=" +
            this.sort +
            ",desc&genre="+this.genre+"&search="+this.searchWord 
        )
        .then((response) => {
          this.reviewList = response.data.content;
          this.SET_REVIEW_LIST(this.reviewList);
          this.setMoreBtn(response.data.last);
           
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    nextList(){
      this.page+=1;
      axios
        .get(
          process.env.VUE_APP_ROOT_URL +
            "/board/list?page="+this.page+"&sort=" +
            this.sort +
            ",desc&genre=모든 장르&search="
        )
        .then((response) => {
          const arr=response.data.content
          
          this.reviewList=this.reviewList.concat(arr);
          
          this.setMoreBtn(response.data.last)
           
          this.SET_REVIEW_LIST(this.reviewList);
        })
        .catch((error) => {
          console.dir(error);
        });
       
    },
    init(){
      this.page=0;
      const target = document.getElementById('moreBtn');
      target.disabled = false;
      this.getList();
    },
    setMoreBtn(last){
       
      if(last){
         const target = document.getElementById('moreBtn');
         target.disabled = true;
      }
    }
  },
};
</script>
<style scoped>
.listBox {
  
   width:90vw;
   margin: 0 auto;
}
.item {
  margin: 0.5em;
}
#moreBtn{
  width: 80vw;;
  margin: 0 auto;
  display: block;
}
</style>
