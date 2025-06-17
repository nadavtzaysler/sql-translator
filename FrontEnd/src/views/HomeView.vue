<template>
  <SqlNavbar />
  <TranslatorInput />
</template>
<script>
import SqlNavbar from '@/components/SqlNavbar.vue'
import TranslatorInput from '@/components/TranslatorInput.vue'

export default {
  name: 'HomeView',
  components: {
    SqlNavbar,
    TranslatorInput
  }
}
</script>

  <style>
body {
  margin: 0px;
}
</style>
