<template>
    <div class="btn-container">
      <button @click="$emit('translate')" id="translate-btn">
        Translate <FontAwesomeIcon :icon="['fas', 'arrow-right']" />
      </button>
      <button v-if="outputVal" @click="$emit('copy')" id="copy-btn">
        <FontAwesomeIcon :icon="['fas', 'copy']" />
      </button>
      <a
        v-if="outputVal && runLink"
        :href="`${runLink}/?sql=${outputVal}`"
        id="run-btn"
      >
        Run query <FontAwesomeIcon :icon="['fas', 'bolt']" />
      </a>
    </div>
  </template>

<script>
export default {
  name: 'TranslatorButtons',
  props: {
    outputVal: String,
    runLink: String
  },
  emits: ['translate', 'copy']
}
</script>

  <style scoped>
  .btn-container > button,
  .btn-container > a {
    margin: 0.5vw;
    margin-top: 0.5vh;
    text-decoration: none;
  }

  #translate-btn {
    cursor: pointer;
    background-color: #95beff;
    width: 50%;
    border-radius: 8px;
    font-weight: bold;
    border: 0px;
    padding: 0.5vh;
    transition: background-color 0.2s ease;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  }

  #translate-btn:hover {
    background-color: #a1b4d3;
  }

  #translate-btn > *,
  #translate-btn,
  #copy-btn > *,
  #copy-btn,
  #run-btn > *,
  #run-btn {
    color: rgb(77, 77, 77);
    font-size: 4vh;
  }

  #copy-btn {
    cursor: pointer;
    background-color: #95ffe4;
    width: 5%;
    border-radius: 8px;
    font-weight: bold;
    border: 0px;
    padding: 1vh;
    transition: background-color 0.2s ease;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  }

  #copy-btn:hover {
    background-color: #b1e8db;
  }

  #run-btn {
    cursor: pointer;
    background-color: #75ffc1;
    width: 20%;
    border-radius: 8px;
    font-weight: bold;
    border: 0px;
    padding: 1vh;
    transition: background-color 0.2s ease;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  }

  #run-btn:hover {
    background-color: #b5e8b1;
  }
  </style>
