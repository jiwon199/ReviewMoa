<template>
  <div class="home">
    <review-search/>
     <review-list/>
      
  </div>
</template>

<script>
import ReviewList from '@/components/review/ReviewList.vue'
import ReviewSearch from '@/components/review/ReviewSearch.vue'
export default {
  name: 'HomeView',
  components: {
    ReviewList,
    ReviewSearch
  }
}
</script>
