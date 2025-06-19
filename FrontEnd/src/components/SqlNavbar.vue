<template>
  <div v-if="headerMsg" class="header-banner">
    <a v-if="headerLink" :href="headerLink" target="_blank" rel="noopener" class="header-link">
      {{ headerMsg }}
    </a>
    <span v-else>{{ headerMsg }}</span>
  </div>

  <nav>
  <img id="main-logo" alt="Vue logo" src="../assets/logo.png">
  <div class="nav-links">
      <RouterLink
        v-for="route in navRoutes"
        :key="route.path"
        :to="route.path"
        class="nav-link"
        active-class="active-link"
      >
        {{ route.name || route.path }}
      </RouterLink>
    </div>
</nav>
</template>

<script>
import { fetchRuntimeConfig } from '../utils/config'

export default {
  name: 'SqlNavbar',
  data () {
    return {
      headerMsg: null,
      headerLink: null,
      navRoutes: []
    }
  },
  async created () {
    const config = await fetchRuntimeConfig()
    this.headerMsg = config.MAIN_HEADER_MSG
    this.headerLink = config.MAIN_HEADER_HYPERLINK

    const allRoutes = this.$router.getRoutes()
    this.navRoutes = allRoutes.filter(
      r => r.meta?.showInNav !== false && r.path !== '/:pathMatch(.*)*'
    )
  }
}
</script>

<style>
nav {
width: 100%;
border: 0vh solid #2c3e50;
border-width: 0.0vh 0vh 0.1vh 0vh;
background-color: #396ddd;
display: flex;
align-items: center;
gap: 2rem;
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
nav-links {
display: flex;
gap: 1.2rem;
}

.nav-link {
color: white;
font-weight: 500;
text-decoration: none;
padding: 0.4rem 0.6rem;
border-radius: 6px;
transition: background 0.2s;

}

.nav-link:hover {
background-color: rgba(255, 255, 255, 0.2);
}

.active-link {
background-color: white;
color: #396ddd;
}
</style>
