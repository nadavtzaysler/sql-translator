<template>
    <select :value="modelValue" @change="$emit('update:modelValue', $event.target.value)">
      <option v-for="dialect in dialects" :key="dialect" :value="dialect">
        {{ dialect }}
      </option>
    </select>
  </template>

<script>
export default {
  name: 'DialectSelect',
  props: {
    modelValue: String,
    dialects: {
      type: Array,
      required: true
    }
  },
  emits: ['update:modelValue']
}
</script>

  <style scoped>
  select {
    width: 45%;
    margin: 2vh 1vw;
    border-radius: 8px;
    padding: 8px;
    border: 1px solid #D1D5DB;
    background-color: white;
  }
  </style>
