import type { RouterLink } from 'vue-router';

<template>
    <div class="container">
        <MallHeader></MallHeader>
    </div>
</template>
  
<script setup lang="ts">
import MallHeader from '../components/MallHeader.vue';
</script>

<style>
.container {
    padding-left: 10%;
    padding-right: 10%;
  }
</style>