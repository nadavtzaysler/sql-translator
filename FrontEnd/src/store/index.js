import { createStore } from 'vuex'
import { fetchSupportedDialects } from '@/api/translator'

export default createStore({
  state: {
    sqlDialects: [],
    activeBackendUrl: 'http://localhost:8080'
  },
  getters: {
    getDialects (state) {
      return state.sqlDialects
    },
    getActiveBackend (state) {
      return state.activeBackendUrl
    }
  },
  mutations: {
    SET_DIALECTS (state, dialects) {
      state.sqlDialects = dialects
    },
    async SET_ACTIVE_BACKEND_URL (state, backend) {
      state.activeBackendUrl = backend
    }
  },
  actions: {
    async loadDialects ({ commit, state }) {
      try {
        const response = await fetchSupportedDialects(state.activeBackendUrl)
        commit('SET_DIALECTS', response.data)
      } catch (error) {
        console.error('Failed to load SQL dialects:', error)
      }
    }
  },
  modules: {
  }
})
