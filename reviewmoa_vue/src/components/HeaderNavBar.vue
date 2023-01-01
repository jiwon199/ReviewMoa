<template>
  <nav class="topmenu">
    <ul class="nav">
      <li><router-link to="/" class="link" id="logo">리뷰 모아</router-link></li>
      <li><router-link to="/" class="link">리뷰 보기</router-link></li>
      <li><router-link to="/write" class="link">리뷰 쓰기</router-link></li>
      <div v-if="userInfo">
        <li style="display:inline-block">{{userInfo.realId}}님 환영합니다.</li>
        <li style="display:inline-block" @click.prevent="onClickLogout" class="link">로그아웃 </li>
        <li style="display:inline-block" @click.prevent="onClickDelete" class="link"> 회원 탈퇴</li>         
      </div>
      <div v-else>
        <li style="display:inline-block"><router-link :to="{name:'regist'}" class="link">회원 가입</router-link></li> 
        <li style="display:inline-block"><router-link :to="{name:'login'}" class="link">로그인</router-link></li>
      </div>
      
    </ul>
  </nav>
</template>

<script>
import {mapState,mapGetters, mapActions,mapMutations} from "vuex";
import axios from "axios";
const memberStore="memberStore";
export default {
  name: "HeaderNavBar",
  data(){
    return {};
  },
  computed:{
    ...mapState(memberStore,["isLogin","userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  created(){
	console.log("환영합니다 ",this.userInfo);
  },
  watch: {
    userInfo() {
      console.log("환영합니다 ",this.userInfo.realId);
      // console.log("바로 읽기",this.$memberStore.state.userInfo.realId);
    },
    
  },
  methods:{
    ...mapActions(memberStore,["userLogout"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN"]),
...mapMutations(memberStore, ["SET_USER_INFO"]),
    onClickLogout(){
      console.log(this.userInfo);
      this.SET_IS_LOGIN(false)
			this.SET_USER_INFO("");

      // this.userLogout(this.userInfo.realId);
      if(this.$route.name!="home") 
      this.$router.push({name:"home"});
    },
    onClickDelete(){
onClickDelete(){
      console.log(this.userInfo.userId);
      const id = this.userInfo.userId;
      axios({
        method:"post",
        url:process.env.VUE_APP_ROOT_URL+"/user/delete/"+id
      }).then(()=>{
        console.log("회원탈퇴 완료");
         this.SET_IS_LOGIN(false);
				this.SET_USER_INFO("");

      // this.userLogout(this.userInfo.realId);
      if(this.$route.name!="home") 
      this.$router.push({name:"home"});
      })
     
    }

      // this.userLogout(this.userInfo.realId);
      if(this.$route.name!="home") 
      this.$router.push({name:"home"});
      })
     
    }
  }
};
</script>
<style>
.topmenu {
  background-color: SandyBrown;
  width: 100%;
  height: 7vh;
  margin: auto;
}

.nav {
  list-style: none;
  line-height: 7vh;
  text-align: center;
}
.link {
  color: PeachPuff;
  font-weight: bold;
  width: 100px;
  display: block;
}
#logo{
  color: white;
  font-weight: bold;
   
  width: 200px;
  display: block;
}

.link:hover {
  text-decoration: none;
  color: white;
  font-weight: bold;
}
nav a.router-link-exact-active {
  color: white;
}
</style>
