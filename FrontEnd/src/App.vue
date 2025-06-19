<template>
  <SqlNavbar/>
  <RouterView/>
</template>
<script>
import SqlNavbar from '@/components/SqlNavbar.vue'

export default {
  components: {
    SqlNavbar
  }

}
</script>

<style>
#app {
  font-family: 'Fira Code', monospace;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

body{
  margin: 0px;
  background-color: #F9FAFB;
}

</style>
