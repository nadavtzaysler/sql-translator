<template>
    <select v-model="inputDialect">
      <option v-for="dialect in dialects" :key="dialect" :value="dialect">
        {{ dialect }}
      </option>
    </select>
    <button id="swap" @click="swapDialects">
      <FontAwesomeIcon :icon="['fas', 'repeat']" />
    </button>
  <select v-model="outputDialect">
      <option v-for="dialect in dialects" :key="dialect" :value="dialect">
        {{ dialect }}
      </option>
    </select>
    <textarea v-on:keydown.enter="translate"  v-model="inputVal" id="input" placeholder="input"></textarea>
    <textarea id="output" class="output" placeholder="output" :value="outputVal" disabled></textarea>
    <button v-on:click="translate" id="translate-btn">Translate <FontAwesomeIcon :icon="['fas', 'arrow-right']" /></button>
    <button v-if="outputVal" v-on:click="copyResult" id="copy-btn"> <FontAwesomeIcon :icon="['fas', 'copy']" /></button>
</template>

<script>
import { mapGetters } from 'vuex'
import { translateQuery } from '@/api/translator'
import { useToast } from 'vue-toastification'

export default {
  name: 'TranslatorInput',
  setup () {
    const toast = useToast()

    return { toast }
  },
  data () {
    return {
      inputVal: '',
      outputVal: '',
      inputDialect: 'hive',
      outputDialect: 'trino'
    }
  },
  computed: {
    ...mapGetters(['getDialects']),
    dialects () {
      return this.getDialects
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
      event.target.blur()

      translateQuery(this.inputDialect, this.outputDialect, this.inputVal)
        .then(response => {
          this.outputVal = response.data
        })
    },
    copyResult () {
      navigator.clipboard.writeText(this.outputVal)

      this.toast.info('Translation Coppied!')
    }
  },
  created () {
    this.$store.dispatch('loadDialects')
  }
}
</script>

<style>
*{
    color: rgb(79, 79, 79);
}

select{
    width: 45%;
    margin: 2vh 1vw;
    border-radius: 8px;
  padding: 8px;
  border: 1px solid #D1D5DB;
  background-color: white;
}
textarea{
    width: 45%;
    height: 60vh;
    margin: 1vh 1vw;
    padding: 0.6vh;
    text-align:left;
    resize: none;
    border: 0.1vh solid rgb(79, 79, 79);
    border-radius: 8px;
  box-shadow: 0 1px 2px rgba(0,0,0,0.05);
}
textarea:focus{
    outline: 0.15vh solid rgb(79, 79, 79);
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

#translate-btn{
    cursor: pointer;
    background-color: #95beff;
    width: 50%;
    border-radius: 8px;
    font-weight: bold;
    border: 0px;
    padding:1vh;
    transition: background-color 0.2s ease;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}
#translate-btn:hover {
  background-color: #a1b4d3;
}

#translate-btn>*, #translate-btn{
    color: rgb(77, 77, 77);
    font-size: 4vh;
}

#copy-btn{
    cursor: pointer;
    background-color: #95ffe4;
    width: 5%;
    border-radius: 8px;
    font-weight: bold;
    border: 0px;
    padding:1vh;
    transition: background-color 0.2s ease;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

#copy-btn>*, #copy-btn{
    color: rgb(77, 77, 77);
    font-size: 4vh;
}

#copy-btn:hover {
  background-color: #b1e8db;
}
</style>
