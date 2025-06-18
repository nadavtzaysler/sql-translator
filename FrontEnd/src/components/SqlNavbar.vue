<template>
    <div v-if="headerMsg" class="header-banner">
      <a v-if="headerLink" :href="headerLink" target="_blank" rel="noopener" class="header-link">
        {{ headerMsg }}
      </a>
      <span v-else>{{ headerMsg }}</span>
    </div>

    <nav>
    <img id="main-logo" alt="Vue logo" src="../assets/logo.png">
  </nav>
</template>

<script>
import { fetchRuntimeConfig } from '../utils/config'

export default {
  name: 'SqlNavbar',
  data () {
    return {
      headerMsg: null,
      headerLink: null
    }
  },
  async created () {
    const config = await fetchRuntimeConfig()
    this.headerMsg = config.HEADER_MSG
    this.headerLink = config.HEADER_LINK
  }
}
</script>

<style>
nav {
  width: 100%;
  border: 0vh solid #2c3e50;
  border-width: 0.0vh 0vh 0.1vh 0vh;
  background-color: #396ddd;
  justify-content: start;
  display: flex;
}

#main-logo{
width: 9vw;
}

.header-banner {
  background-color: #86cf86;
  color: #262626;
  padding: 0.1vw;
  text-align: center;
  font-weight: bold;
}

.header-link {
  color: #2b6b2b;
  text-decoration: underline;
}
</style>
