<script setup>
import { ref } from 'vue'
import router from '../router/index.js'

defineProps({
  msg: String,
})

const count = ref(0)
const activeIndex = ref('1')
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}

</script> 

<template>
  <!--<el-button type="primary" @click="count++">count is {{ count }}</el-button>
    <router-link replace to="/login"><el-button>login</el-button></router-link>
    <router-link replace to="/"><el-button>reg</el-button></router-link>
    <router-view></router-view>-->
    <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item>Processing Center</el-menu-item>
    <el-menu-item>Info</el-menu-item>
  </el-menu>

</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
