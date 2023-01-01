<template>
  <div class="writeArea">
    <b-form-input
      v-model="postTitle"
      placeholder="게시글 제목을 입력하세요."
      id="titleInput"
    ></b-form-input>
    <b-form-input
      v-model="movieTitle"
      placeholder="영화 이름을 입력하세요."
      id="titleInput"
      style="width: 50%"
    ></b-form-input>
    <b-form-select v-model="genre" :options="genres" id="genreInput">
      <b-form-select-option value="" disabled selected hidden>
        <span>장르를 선택하세요.</span>
      </b-form-select-option>
    </b-form-select>

    <b-form-tags
      input-id="tags-basic"
      v-model="tagList"
      placeholder="영화 키워드를 입력하세요.(3개 이하)"
      :limit="limit"
    >
    </b-form-tags>

    <!-- 컨텐츠-->
    <div
      class="contentInput"
      contentEditable="true"
      placeholder="내용을 입력하세요."
      id="content"
      v-html="content"
    ></div>

    <b-button
      variant="outline-secondary"
      class="submitBtn"
      size="lg"
      @click="submit"
      >작성하기</b-button
    >
    <!-- 이미지 추가 버튼 -->
    <label
      className="input-file-button"
      for="chooseFile"
      class="submitBtn"
      id="fileLabel"
    >
      사진 업로드
    </label>
    <input
      type="file"
      ref="image"
      accept="image/*"
      id="chooseFile"
      name="chooseFile"
      class="submitBtn"
      @change="uploadImg()"
      style="display: none"
    />
  </div>
</template>

<script>
import { getStorage, ref, uploadBytes, getDownloadURL } from "firebase/storage";
import { initializeApp } from "firebase/app";
import { mapState, mapMutations } from "vuex";
import axios from "axios";
const reviewStore = "reviewStore";
const memberStore="memberStore";
const firebaseConfig = {
  apiKey: process.env.VUE_APP_FIREBASE_API_KEY,
  authDomain: process.env.VUE_APP_AUTH_DOMAIN,
  projectId: process.env.VUE_APP_PROJECT_ID,
  storageBucket: process.env.VUE_APP_STORAGE_BUCKET,
  messagingSenderId: process.env.VUE_APP_MESSAGING_SENDER_ID,
  appId: process.env.VUE_APP_APP_ID,
};
initializeApp(firebaseConfig);
const storage = getStorage();
export default {
  name: "ReviewWrite",
  computed: {
    ...mapState(reviewStore, ["reviewList"]),
    ...mapState(reviewStore, ["selectIdx"]),
    ...mapState(reviewStore, ["isUpdate"]),
    ...mapState(memberStore,["userInfo"])

     
  },
  data() {
    return {
      postTitle: "",
      movieTitle: "",
      genre: "",
      content: "",
      thumbnail: "",
      genres: ["SF", "판타지", "로맨스","누아르","공포","코미디","애니메이션"],
      timestamp: "",
      postId: "",
      tagList: [],
      limit: 3,
      writer:"익명"
    };
  },
  created() {
    this.init();
    if(this.userInfo!="") this.writer=this.userInfo.realId;
    else{
      alert("로그인을 먼저 해주세요");
      this.$router.push("/");
    }
  },
  methods: {
    ...mapMutations(reviewStore, ["SET_IS_UPDATE"]),
    init() {
      //만약 수정중이면 정보를 가져와서 세팅
      if (this.isUpdate == true) {
        this.postId = this.reviewList[this.selectIdx].postId;
        this.postTitle = this.reviewList[this.selectIdx].postTitle;
        this.movieTitle = this.reviewList[this.selectIdx].movieTitle;
        this.genre = this.reviewList[this.selectIdx].genre;
        this.content = this.reviewList[this.selectIdx].content;
        this.initTagList();
        this.SET_IS_UPDATE(false); //수정중을 false로 돌려놓기
      }
    },
    initTagList(){
      for(var i=0;i<this.reviewList[this.selectIdx].tagList.length;i++){
        this.tagList.push(this.reviewList[this.selectIdx].tagList[i].content);
      }
    },
    submit() {
      this.content = document.getElementById("content").innerHTML;
      if (this.isValid()) {
        this.selectThumbnail();
        //썸네일이 없으면 기본 썸네일로 설정
        if (this.thumbnail == "") {
          this.thumbnail =
            "https://firebasestorage.googleapis.com/v0/b/test-f7f1d.appspot.com/o/board%2F%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0.png?alt=media&token=34f2ccf3-5eac-4af5-87fc-3c8653c12b92";
        }
        //글쓰기인 경우
        if (this.postId == "") {
          this.addPost();
        }
        //수정쓰기인 경우
        else {
          this.updatePost();
        }
      } else {
        alert("모든 정보를 입력하세요.");
      }
    },
    //모든 칸이 채워졌는지 확인
    isValid() {
      if (
        this.postTitle == "" ||
        this.movieTitle == "" ||
        this.genre == "" ||
        this.content == ""
      ) {
        return false;
      }
      return true;
    },
    addPost() {
      var tagList=this.makeTagListFormat();
      axios({
        method: "post",
        url: process.env.VUE_APP_ROOT_URL + "/board/write",
        data: {
          writer: this.writer,
          hit: 0,
          genre: this.genre,
          movieTitle: this.movieTitle,
          postTitle: this.postTitle,
          thumbnail: this.thumbnail,
          heart: 0,
          content: this.content,
          tagList: tagList
        },
      }).then(() => {
        //alert("포스트를 작성했습니다.");
        this.$router.push("/");
      });
    },

    //파이어베이스 스토리지에 이미지를 업로드
    uploadImg() {
      this.timestamp = new Date();
      var image = this.$refs["image"].files[0];
      const uploadStorage = ref(
        storage,
        "board/" + image.name + "_" + this.timestamp
      );
      uploadBytes(uploadStorage, image)
        .then(() => {
          console.log("Uploaded a blob or file!");
          this.createImg(image);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //이미지 링크를 다운로드하고 태그 삽입
    createImg(image) {
      //그 링크를 저장
      getDownloadURL(ref(storage, "board/" + image.name + "_" + this.timestamp))
        .then((url) => {
          this.makeHtmlImg(url);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //이미지 태그를 생성
    makeHtmlImg(url) {
      let tagArea = document.getElementById("content");
      let new_pTag = document.createElement("img");
      new_pTag.setAttribute("src", url);
      tagArea.appendChild(new_pTag);
    },
    //가장 처음 오는 img를 썸네일로
    selectThumbnail() {
      var imgElements = document.getElementsByTagName("img");
      if (imgElements.length >= 1) {
        this.thumbnail = imgElements[0].src;
      }
    },
    updatePost() {
      var tagList=this.makeTagListFormat();
      axios({
        method: "post",
        url: process.env.VUE_APP_ROOT_URL + "/board/update",
        data: {
          postId: this.postId,
          writer: "익명",
          hit: 0,
          genre: this.genre,
          movieTitle: this.movieTitle,
          postTitle: this.postTitle,
          thumbnail: this.thumbnail,
          heart: 0,
          content: this.content,
          tagList:tagList
        },
      }).then(() => {
        //alert("포스트를 작성했습니다.");
        this.$router.push("/");
      });
    },
    makeTagListFormat(){
      var list=[];
      for(var i=0;i<this.tagList.length;i++){
         var item={
          content:this.tagList[i]
         }
         list.push(item);
      }
      return list;
    }
  },
};
</script>
<style scope>
.writeArea {
  width: 60vw;
  margin: 0 auto;
  margin-top: 3em;
}
.contentInput {
  font-size: 20px;
  margin-top: 0.5em;
  height: 50vh;
  border: 1px solid lightgray;
  padding: 10px;
  overflow: auto;
}
.contentInput:focus {
  outline: none;
}
.contentInput[placeholder]:empty:before {
  content: attr(placeholder);
  color: #555;
}
.submitBtn {
  margin-top: 0.5em;
  margin-left: 0.5em;
  float: right;
  font-size: 1.5em;
  color: sandybrown !important;
  border: 1px solid sandybrown !important;
}
.submitBtn:hover {
  background: sandybrown !important;
  border: 1px solid sandybrown !important;
  color: white !important;
  cursor: pointer;
}
#titleInput {
  font-size: 1.5em;
  border-radius: 0px;
  display: inline-block;
}
#titleInput:focus {
  text-decoration: none;
  outline: none;
  box-shadow: none;
}
#genreInput {
  font-size: 1.5em;
  border-radius: 0px;
  width: 50%;
  vertical-align: top;
}

#genreInput:focus {
  text-decoration: none;
  outline: none;
  box-shadow: none;
}
#content img {
  max-width: 100%;
}
#fileLabel {
  padding-left: 0.5em;
  padding-right: 0.5em;
  padding-bottom: 0.4em;
  padding-top: 0.4em;
  border-radius: 0.2em;
  font-size: 1.25em;
}
</style>
