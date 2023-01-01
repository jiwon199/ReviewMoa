<template>
  <div class="itemBox">
  
   <b-card
    no-body
    style="max-width: 20rem; border:1px solid lightgray;"
    :img-src="reviewList[itemIdx]?.thumbnail"
    img-alt="Image"
    img-height="150"
    img-top
    class="item"
    @click="select"
  >
      
    <b-card-body class="cardBody">
      <b-card-title style="font-family: 'BMJUA'; margin:0px;">{{postTitle}}</b-card-title> <!-- 포스트 제목 -->

<div v-if="tagList.length>=1"> 
    <div class="tag" v-for="(item, index) in tagList" :key="index" :item="item"  >
      <b-badge variant="secondary" id="badge">{{item.content}} </b-badge>
    </div><br/></div>
      <b-card-text style="margin:0px;  font-size: 1.1em;">
        영화: {{reviewList[itemIdx]?.movieTitle}} 
         
      </b-card-text>
       <b-card-sub-title class="mb-2" style="margin:0px;"> 조회수: {{reviewList[itemIdx]?.hit}} 장르: {{reviewList[itemIdx]?.genre}}</b-card-sub-title>  
    </b-card-body>
 
  </b-card>
  
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapMutations } from "vuex";
const reviewStore = "reviewStore";
export default {
  name: 'ReviewItem',
   data() {
    return {
      postTitle : "",
      tagList:[]
       
    }
  },
  props:["itemIdx"],
   computed: {
     
    ...mapState(reviewStore, ["reviewList"]),
    
  },
  watch: {
    reviewList() {
     this.makeShortTitle();
    this.tagList=this.reviewList[this.itemIdx].tagList;
    },
  },
  created(){
    this.makeShortTitle();
    this.tagList=this.reviewList[this.itemIdx].tagList;
  },
  methods:{
    ...mapMutations(reviewStore,["SET_SELECT_IDX"]),
    select(){
      this.SET_SELECT_IDX(this.itemIdx);
      this.$router.push('/detail');
    },
    //너무 긴 제목은 줄임
    makeShortTitle(){
      this.postTitle= this.reviewList[this.itemIdx].postTitle;
      if(this.postTitle.length>=13){
        this.postTitle=this.postTitle.slice(0,12);
        this.postTitle+="..";
        //console.log(this.postTitle);
      }
    }
  }
}
</script>
<style>
.linkCard{
  text-decoration: none;
  color:black;
}
.linkCard:hover{
  text-decoration: none;
  color:black;
}
 
.itemBox{
  max-width: 22rem;
   
  display:flex;
}
.item {
  border:1px solid lightgray;
  margin: auto;
  height: 18em;
  width: 20em;
   
}
.item:hover {
  background:#f0f0f0;
  cursor: pointer;
  
}
.cardBody{
  border-top:1px solid lightgray;
}
#badge{
  padding:5px;
  font-size: 13px;
  font-style:normal;
  background: sandybrown; 
}
</style>