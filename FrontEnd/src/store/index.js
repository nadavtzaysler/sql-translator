import { createStore } from 'vuex'
import { fetchSupportedDialects } from '@/api/translator'

export default createStore({
  state: {
    sqlDialects: []
  },
  getters: {
    getDialects (state) {
      return state.sqlDialects
    }
  },
  mutations: {
    SET_DIALECTS (state, dialects) {
      state.sqlDialects = dialects
    }
  },
  actions: {
    async loadDialects ({ commit }) {
      try {
        const response = await fetchSupportedDialects()
        commit('SET_DIALECTS', response.data)
      } catch (error) {
        console.error('Failed to load SQL dialects:', error)
      }
    }
  },
  modules: {
  }
})
