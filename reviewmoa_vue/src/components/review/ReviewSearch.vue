<template>
  <div class="mainArea">
    <div class="content">
    
      <b-form-input
        id="input"
        placeholder="검색어를 입력하세요."
        v-model="searchWord"
      ></b-form-input>
      <b-button id="btn" @click="search">검색</b-button>
       <b-button id="btn" @click="clearSearchWord">초기화</b-button>
      <br />
      <div class="orderArea">
        <div class="sortDiv">
          <b-form-group v-slot="{ ariaDescribedby }">
            <b-form-radio-group
              id="btn-radios-2"
              v-model="selectedOrder"
              :options="orderOptions"
              :aria-describedby="ariaDescribedby"
              button-variant="outline-primary"
              size="md"
              name="radio-btn-outline"
              buttons
              @change="changeSort"
            ></b-form-radio-group>
          </b-form-group>
        </div>
        <div class="genreDiv">
          <b-form-group v-slot="{ ariaDescribedby }">
            <b-form-radio-group
              id="btn-radios-2"
              v-model="selectedGenre"
              :options="genreOptions"
              :aria-describedby="ariaDescribedby"
              button-variant="outline-primary"
              size="md"
              name="radio-btn-outline"
              buttons
              @change="changeGenre"
            ></b-form-radio-group>
          </b-form-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import axios from "axios";
import { mapState, mapMutations } from "vuex";
const reviewStore = "reviewStore";
export default {
  name: "ReviewSearch",
  data() {
    return {
      selectedOrder: "최신순",
      orderOptions: ["최신순", "조회순"],
      selectedGenre: "모든 장르",
      genreOptions: [
        "모든 장르",
        "SF",
        "판타지",
        "로맨스",
        "누아르",
        "공포",
        "코미디",
        "애니메이션",
      ],
      searchWord:"",
    };
  },
  computed: {
    ...mapState(reviewStore, ["sort"]),
    ...mapState(reviewStore, ["genre"]),
    ...mapState(reviewStore, ["searchWord"]),
 
  },
  created(){
    this.init()
  },
  methods: {
    ...mapMutations(reviewStore, ["SET_SORT"]),
    ...mapMutations(reviewStore, ["SET_GENRE"]),
    ...mapMutations(reviewStore, ["SET_SEARCH"]),
    changeSort() {
      if (this.selectedOrder == "최신순") this.SET_SORT("postId");
      else this.SET_SORT("hit");
    },
    changeGenre() {
      this.SET_GENRE(this.selectedGenre);
    },
    search() {
      this.SET_SEARCH(this.searchWord);
    },
    init(){
      this.SET_SEARCH("");
      this.SET_SORT("postId");
      this.SET_GENRE("모든 장르");
    },
    clearSearchWord(){
      this.searchWord="";
      this.SET_SEARCH(this.searchWord);

    }
     
  },
};
</script>
<style scoped>
.mainArea {
  width: 60vw;
  margin: 0 auto;
}
.content {
  margin-top: 2em;
  text-align: center;
}
#orderOption {
  width: 7vw;
  border-radius: 0px;
}
#genreOption {
  width: 7vw;
  border-radius: 0px;
}
#input {
  width: 17vw;
  display: inline-block;
  vertical-align: top;
  border-radius: 0px;
  margin-bottom: 1em;
}
#input:focus {
  text-decoration: none;
  outline: none;
  box-shadow: none;
}
#btn {
  background: sandybrown;
  vertical-align: top;
  border: 1px solid sandybrown;
  border-radius: 0px;
  border-left:1px solid white;
}
#radius {
  display: inline-block;
}
.sortDiv {
  display: inline-block;
  margin-right: 1.5em;
}
.genreDiv {
  display: inline-block;
}
</style>
