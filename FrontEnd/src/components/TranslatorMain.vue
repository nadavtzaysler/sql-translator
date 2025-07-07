<template>
  <DialectSelect v-model="inputDialect" :dialects="dialects" />
  <button id="swap" @click="swapDialects">
    <FontAwesomeIcon :icon="['fas', 'repeat']" />
  </button>
  <DialectSelect v-model="outputDialect" :dialects="dialects" />

  <TranslatorIo
  v-model="inputVal"
  id="input"
  placeholder="input"
  @enter="translate"
/>
<TranslatorIo
  :modelValue="outputVal"
  id="output"
  placeholder="output"
  disabled
  textareaClass="output"
/>
  <TranslatorButtons
  :outputVal="outputVal"
  :runLink="runLink"
  @translate="translate"
  @copy="copyResult"
/>

</template>

<script>
import { mapGetters } from 'vuex'
import { translateQuery } from '@/api/translator'
import { useToast } from 'vue-toastification'
import { fetchRuntimeConfig } from '../utils/config'
import DialectSelect from './DialectSelect.vue'
import TranslatorButtons from './TranslatorButtons.vue'
import TranslatorIo from './TranslatorIo.vue'

export default {
  name: 'TranslatorMain',
  components: { DialectSelect, TranslatorIo, TranslatorButtons },
  setup () {
    const toast = useToast()
    return { toast }
  },
  data () {
    return {
      inputVal: '',
      outputVal: '',
      inputDialect: 'hive',
      outputDialect: 'trino',
      runLink: ''
    }
  },
  computed: {
    ...mapGetters(['getDialects', 'getActiveBackend']),
    dialects () {
      return this.getDialects
    },
    activeBackendUrl () {
      return this.getActiveBackend
    }
  },
  methods: {
    swapDialects () {
      const tempDialect = this.inputDialect
      this.inputDialect = this.outputDialect
      this.outputDialect = tempDialect

      if (this.inputVal && this.outputVal) {
        const tempVal = this.inputVal
        this.inputVal = this.outputVal
        this.outputVal = tempVal
      }
    },
    translate (event) {
      if (event?.target?.blur) {
        event.target.blur()
      }

      translateQuery(this.activeBackendUrl, this.inputDialect, this.outputDialect, this.inputVal)
        .then(response => {
          this.outputVal = response.data
       }).catch(error => {
          if (error.response && error.response.status === 400) {
            this.toast.error(`Bad request: ${error.response?.data?.detail || error.response?.data || 'Unknown error'}`)
          }
        })
    },
    copyResult () {
      navigator.clipboard.writeText(this.outputVal)
      this.toast.info('Translation Coppied!')
    }
  },
  async created () {
    this.$store.dispatch('loadDialects')
    const config = await fetchRuntimeConfig()
    this.runLink = config.RUN_BUTTON_HYPERLINK
  }
}
</script>

<style>
*{
  color: rgb(79, 79, 79);
}

button#swap{
  background: none;
  text-decoration-color: none;
  border: none;
}

.output{
background-color: #ebebeb;
}

.input{
  background-color: #FFFFFF;
}
</style>
