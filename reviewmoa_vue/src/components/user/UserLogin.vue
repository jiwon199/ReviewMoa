//UserLogin.Vue

<template>
    <b-container>
    <b-row class="my-1">
      <b-col>
        <b-card>
          <b-form>
            <b-form-group label="아이디 : " label-for="userId">
              <b-form-input
                id="userId"
                type="text"
                v-model="user.realId"
                required
                placeholder="id 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 : " label-for="userPwd">
              <b-form-input
                id="userPwd"
                type="password"
                v-model="user.userPwd"
                required
                placeholder="비밀번호 입력"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" @click="signin">로그인</b-button>
            <b-button type="button" @click="signup">회원가입</b-button>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import {mapState, mapMutations} from "vuex";
const memberStore = "memberStore";
import axios from "axios";
export default {
    name:"UserLogin",
    data(){
        return{
            user:{
                realId:null,
                userPwd:null,
            },
        };
    },
    computed:{
        ...mapState(memberStore,["isLogin","isLoginError","userInfo"]),
    },
    methods:{
        //...mapActions(memberStore,["userConfirm","getUserInfo"]),
		...mapMutations(memberStore, ["SET_IS_LOGIN"]),
...mapMutations(memberStore, ["SET_USER_INFO"]),
    
        signin(){
            console.log(JSON.stringify(this.user));
            axios
        .post(process.env.VUE_APP_ROOT_URL + "/user/login", JSON.stringify(this.user), {
          headers: {
            "Content-Type": `application/json`,
          },
        })
        .then((data ) => {
            this.SET_IS_LOGIN(true)
						this.SET_USER_INFO(data.data);
            console.log(data.data.realId);
            this.$router.push({name:"home"});

          }
        )
        .catch((error) => {
          console.log(error);
        });
        },
        signup(){
            this.$router.push({name:"regist"});
        }
    }
}
</script>