<script setup lang="ts">
import axios from 'axios';
import { create } from 'lodash';
import { onBeforeMount } from 'vue';
import { onMounted } from 'vue';
import { ref } from 'vue'


const goodsPictureAddr = "/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png"
const goodsPictureList: string[] = []
// 后端发送过来的图片，进行一下接收
const goodsPictureList1 = ref<string[]>([])
//默认访问路径
const imagesURLPrefix = 'http://localhost:8080/static/images/'
for (let index = 0; index < 5; index++) {
  goodsPictureList.push(goodsPictureAddr);
  //console.log(goodsPictureList[index])
}
const path = 'http://localhost:8080';

axios({
  method: 'get',
  url: path + '/images',
  params: { img: 'img1' }
}).then((response) => {
  //console.log(response.data);
  var imageArr = response.data.data;
  for (var i = 0; i < imageArr.length; ++i) {
    imageArr[i] = imagesURLPrefix + imageArr[i] + '.png';
    goodsPictureList1.value.push(imageArr[i]);
  }
  //console.log(goodsPictureList1);
}).catch((error) => {
  console.log("error 11= " + error);

})

</script>

<template>
  <div>
    <el-row :gutter="200">
      <el-col :span="4" v-for="item, index in goodsPictureList1">
        <RouterLink to="/goodsInfo">
          <el-card :body-style="{ padding: '5px' }" style="width: 210px">
            <img :key="index" :src=item class="image" />
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

.goodsPicture {
  margin-left: 1px;
}
</style>
