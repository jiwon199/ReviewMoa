import axios from "axios";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: "",
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkisLogin:function(state) {
      return state.isLogin;
    }
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    // async userConfirm({ commit }, user) {
    //   axios
    //     .post(process.env.VUE_APP_ROOT_URL + "/user/login", JSON.stringify(user), {
    //       headers: {
    //         "Content-Type": `application/json`,
    //       },
    //     })
    //     .then((data ) => {
    //       if (data.status == 200) {
    //         console.log(data.data.realId);
    //         commit("SET_IS_LOGIN", this.state,true);
    //         // console.log(Object.values(this.state.memberStore.isLogin)[1].isLogin.memberStore.isLogin);
    //         console.log("로그인??" + this.state.isLogin);
    //         commit("SET_IS_LOGIN_ERROR", this.state,false);
    //         commit("SET_USER_INFO", this.state,data.data);
    //         // console.log(this.isLogin);
    //         this.$router.push({name:"home"});

    //       } else {
    //         commit("SET_IS_LOGIN", false);
    //         commit("SET_IS_LOGIN_ERROR", this.state,true);
    //       }
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },
    async userLogout({ commit }, userId) {
      axios
        .get(process.env.VUE_APP_ROOT_URL + "/user/logout/" + userId)
        .then(({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
export default memberStore;
