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
                v-model="user.userId"
                required
                placeholder="id 입력"
                @blur="checkDuplication"
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
            <b-form-group label="이름 : " label-for="userName">
              <!-- class="form-inline" -->
              <!-- 나중에 별명도 추가 가능 -->
              <b-form-input
                id="userName"
                type="text"
                v-model="user.userName"
                required
                placeholder="이름 입력"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="생년월일 : " label-for="userBirth">
              <b-form-input
                id="userBirth"
                type="text"
                v-model="user.userBirth"
                required
                placeholder="생년월일 8자"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="성별 : " label-for="userGender">
              <b-form-input
                id="userGender"
                type="text"
                v-model="user.userGender"
                required
                placeholder="성별 입력"
              ></b-form-input>
              <!-- type 선택버튼으로 변경해보기 -->
            </b-form-group>
            <b-button type="button" @click="signup">회원등록</b-button>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";
export default {
  name: "UserRegist",
  data() {
    return {
      user: {
        userId: "",
        userPwd: "",
        userName: "",
        userBirth: "",
        userGender: "",
      },
    };
  },
  methods: {
    checkDuplication() {
      // alert("check");
    },
    signup() {
      const userId = this.user.userId;
      const userPwd = this.user.userPwd;
      const userName = this.user.userName;
      const userBirth = this.user.userBirth;
      const userGender = this.user.userGender;

      if (!userId) {
        alert("id check");
      } else if (!userPwd) {
        console.log(userId);
        alert("password check");
      } else if (!userName) {
        console.log(userPwd);
        alert("name check");
      } else if (!userBirth) {
        console.log(userName);
        alert("birth check");
      } else if (!userGender) {
        console.log(userBirth);
        alert("gender check");
      } else {
        axios({
          method: "post",
          url: process.env.VUE_APP_ROOT_URL + "/user/regist",
          data: {
            realId: userId,
            userPwd: userPwd,
            userName: userName,
            userBirth: userBirth,
            userGender: userGender,
          },
        }).then(() => {
          this.$router.push({name:"login"});
        });
      }
    },
  },
};
</script>
<style></style>
