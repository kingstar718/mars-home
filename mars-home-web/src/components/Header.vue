<script setup>
import { ref } from 'vue'

defineProps({
  msg: String,
})

const count = ref(0)
</script>

<template>
  <el-header>
    <h1>header</h1>
  </el-header>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
