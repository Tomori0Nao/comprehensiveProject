<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { Ref } from 'vue'

const goodsPictureAddr =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'
// const goodsPictureList: string[] = []

// goodsCardInfo
// 商品展示卡片信息
// 商品图片url
// 商品名称
// 商品价格
interface GoodsCardInfo {
  goodsPictureAddr: string
  goodsName: string
  goodsPrice: number
}
const goodCardInfoList: Ref<GoodsCardInfo[]> = ref([
  {
    goodsPictureAddr: goodsPictureAddr,
    goodsName: '三星 s23',
    goodsPrice: 6999
  }
])

// 后端发送过来的图片，进行一下接收
// const goodsPictureList1 = ref<string[]>([])
//默认访问路径
const imagesURLPrefix = 'http://localhost:8080/static/images/'
// for (let index = 0; index < 5; index++) {
//   goodsPictureList.push(goodsPictureAddr);
// }
const path = 'http://localhost:8080'

axios({
  method: 'get',
  url: path + '/images',
  params: { img: 'img1' }
})
  .then((response) => {
    console.log(response.data)
    var imageArr = response.data.data
    for (var i = 0; i < imageArr.length; ++i) {
      imageArr[i] = imagesURLPrefix + imageArr[i] + '.png'
      let tem: GoodsCardInfo = {
        // 临时变量 接受后端数据后，push到goodCardInfoList中
        goodsPictureAddr: goodsPictureAddr,
        goodsName: '三星 s23',
        goodsPrice: 6933
      }
      tem.goodsPictureAddr = imageArr[i]
      // console.log(imageArr[i])
      // console.log(tem)
      goodCardInfoList.value.push(tem)
      // console.log(goodCardInfoList, 'list')
    }
  })
  .catch((error) => {
    console.log('error = ' + error)
  })
</script>

<template>
  <div>
    <el-row :gutter="200">
      <el-col :span="4" v-for="(item, index) in goodCardInfoList">
        <RouterLink to="/goodsInfo">
          <el-card :body-style="{ padding: '5px' }" style="width: 210px">
            <img :key="index" :src="item.goodsPictureAddr" class="image" />
            <div class="bottom">
              {{ item.goodsName }}
              <el-button text class="button">{{ item.goodsPrice }}</el-button>
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
