<template>
  <div id="app">
    <header-nav-bar/>
    

    <router-view/>
  </div>
</template>

<script>
import HeaderNavBar from '@/components/HeaderNavBar.vue'

export default {
  
  components: {
    HeaderNavBar
  }
}
</script>

<style>
 .BMJUA {
	font-family:'BMJUA';
}
.NanumSquareRound {
	font-family: 'NanumSquareRound';
}
#app{
  font-family: 'NanumSquareRound';
}
 @font-face {
  font-family: "NanumSquareRound";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff") format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
    font-family: 'BMJUA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>
