<template>
  <div class="content">
    <div class="titleArea"> 
     
    <div class="postTitle">{{reviewList[selectIdx].postTitle}}</div>
    <div class="movieTitle">영화 이름:{{reviewList[selectIdx].movieTitle}}</div>
    <div class="tag" v-for="(item, index) in tagList" :key="index" :item="item"  >
      <b-badge variant="secondary" id="badge">{{item.content}} </b-badge>
    </div><br/>
    <div class="writer">
    작성자:{{reviewList[selectIdx].writer}},
    {{reviewList[selectIdx].registTime}}</div>
   
     <div v-if="userInfo.realId==reviewList[selectIdx].writer">
    <button class="optBtn"  @click="deletePost">삭제</button>
    <button class="optBtn" @click="updatePost">수정</button>
      </div>
   </div>

  <div class="contentArea">
   <div v-html="reviewList[selectIdx].content"/>
   </div>
  </div>
</template>

<script>
import { mapState,mapMutations } from "vuex";
import axios from "axios";
const reviewStore = "reviewStore";
const memberStore="memberStore";
export default {
  name: 'ReviewItem',
   data() {
    return {
      tagList:[]
    };
  },
   computed: {
    ...mapState(reviewStore, ["reviewList"]),
    ...mapState(reviewStore, ["selectIdx"]),
    ...mapState(memberStore,["userInfo"])
  },
  created(){
    this.updateHit();
    this.tagList=this.reviewList[this.selectIdx].tagList;
  },
  methods:{
     ...mapMutations(reviewStore,["SET_IS_UPDATE"]),
     deletePost(){
      axios({
        method: "post",
        url: process.env.VUE_APP_ROOT_URL + "/board/delete/"+this.reviewList[this.selectIdx].postId,
        
      }).then(() => {
        //alert("포스트를 작성했습니다.");
        this.$router.push('/');
      });
     },
     updatePost(){
        //상태를 수정중으로 바꿔놓고 쓰기 페이지로 이동
        this.SET_IS_UPDATE(true);
        this.$router.push('/write');
     },
     updateHit(){
      axios({
        method: "post",
        url: process.env.VUE_APP_ROOT_URL + "/board/updateHit/"+this.reviewList[this.selectIdx].postId,
        
      }) 
     }

  }
}
</script>
<style>
 
.content{
  
 width: 60vw;
  margin: 0 auto;
}
.titleArea{
  margin-top:2em;
}
.postTitle{
    font-size: 3em;
    font-family: 'BMJUA';
}
.movieTitle{
     font-size: 1.5em;
     color:gray;
     font-family: 'BMJUA';
}
.writer{
    font-size:1em; 
    display: inline;
}
.tag{
  display: inline;
  margin-left: 2px;
}
.contentArea{
    margin: 5px;
    margin-top:2em;
    font-size:20px;
    margin-bottom:2em;
    
}
.contentArea img {
  max-width: 100%;
}
.img{
    border:1px solid gray;
    margin-bottom: 1em;
    margin-top:1em;
    height:10 !important;
}
.optBtn{
  float:right;
  margin-right:1em;
  margin-bottom: 1em;
  background-color: white;
  border :0px;
  font-size:1em;
  
}
.optBtn:hover{
  color:gray;
}
#badge{
  padding:5px;
  font-size: 13px;
  font-style:normal;
  background: sandybrown; 
}
</style>