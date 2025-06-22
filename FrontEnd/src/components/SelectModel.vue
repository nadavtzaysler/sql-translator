<template>
  <select v-model="SelectedBackend">
    <option v-for="backend in backends" :key="backend.name" :value="backend.name">
      {{ backend.name }}
    </option>
  </select>
</template>

<script>
import { useToast } from 'vue-toastification'
import { fetchRuntimeConfig } from '../utils/config'

export default {
  name: 'SelectModel',
  setup () {
    const toast = useToast()

    return { toast }
  },
  data () {
    return {
      SelectedBackend: 'SqlGlot',
      backends: [{ name: 'SqlGlot', backendUrl: 'http://localhost:8080' }]
    }
  },
  watch: {
    SelectedBackend (newName) {
      const currentBackend = this.backends.find(backend => backend.name === newName)

      if (currentBackend) {
        this.$store.commit('SET_ACTIVE_BACKEND_URL', currentBackend.backendUrl)

        try {
          this.$store.dispatch('loadDialects')
        } catch {
          this.$store.commit('SET_DIALECTS', [])
        }
      } else {
        this.toast.error('Selected backend not found')
      }
    }
  },

  async created () {
    const config = await fetchRuntimeConfig()

    if (config.ACTIVE_BACKENDS && config.ACTIVE_BACKENDS.length > 0) {
      this.backends = config.ACTIVE_BACKENDS
    }

    await this.$store.commit('SET_ACTIVE_BACKEND_URL', this.backends[0].backendUrl)
  }
}
</script>

<style scoped>
select{
  width: 10%;
  margin: 2vh 1vw;
  border-radius: 8px;
padding: 8px;
border: 0px;
background-color: rgba(255, 255, 255, 0.435);
color: white;
}
</style>
