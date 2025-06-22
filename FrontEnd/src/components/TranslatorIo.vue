<template>
    <textarea
      :id="id"
      :class="textareaClass"
      :placeholder="placeholder"
      :value="modelValue"
      :disabled="disabled"
      @input="$emit('update:modelValue', $event.target.value)"
      @keydown.enter="$emit('enter')"
    ></textarea>
  </template>

<script>
export default {
  name: 'TranslatorIo',
  props: {
    modelValue: String,
    placeholder: String,
    id: String,
    disabled: Boolean,
    textareaClass: {
      type: String,
      default: ''
    }
  },
  emits: ['update:modelValue', 'enter']
}
</script>

  <style scoped>
  textarea {
    width: 45%;
    height: 60vh;
    margin: 1vh 1vw;
    margin-bottom: 0.1vh;
    padding: 0.6vh;
    text-align: left;
    resize: none;
    border: 0.1vh solid rgb(79, 79, 79);
    border-radius: 8px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
  }
  textarea:focus {
    outline: 0.15vh solid rgb(79, 79, 79);
  }
  </style>
