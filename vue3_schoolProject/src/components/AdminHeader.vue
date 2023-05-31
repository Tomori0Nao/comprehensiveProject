<template>
  <div class="header">
    <div class="left">
      <span style="font-size: 20px">{{ state.name }}</span>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive } from 'vue'
import axios from 'axios'
axios.defaults.withCredentials = true

const state = reactive({
  name: 'dashboard'
})
</script>
<style scoped>
.header {
  height: 50px;
  border-bottom: 1px solid #e9e9e9;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}
/* .header .left {
  border: 1px solid #e9e9e9;
  padding: 5px;
  border-radius: 50%;
  margin-right: 5px;
  cursor: pointer;
} */
</style>
