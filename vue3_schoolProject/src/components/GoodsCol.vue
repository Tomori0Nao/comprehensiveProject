<script setup lang="ts">
const goodsPictureAddr = "/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png"
const goodsPictureList : string[] = []
for (let index = 0; index < 4; index++) {
  goodsPictureList.push(goodsPictureAddr);
  console.log(goodsPictureList[index])
}
goodsPictureList.push(goodsPictureAddr)
</script>

<template>
  <div >
    <el-row :gutter="250">
    <el-col :span="4" v-for="item in goodsPictureList"> 
      <RouterLink to="/goodsInfo">
      <el-card :body-style="{ padding: '5px' }" style="width: 210px">
        <img
          :src=item
          class="image"
        />
        <div class="bottom">
          <span>s23 2023</span>
            <el-button text class="button">$6999</el-button>
        </div>
      </el-card>
    </RouterLink>
    </el-col>
  </el-row>
  </div>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
