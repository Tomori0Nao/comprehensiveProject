<script setup lang="ts">
import axios from 'axios'
import { ref } from 'vue'
import type { Ref } from 'vue'
axios.defaults.withCredentials = true

const props = defineProps(['type', 'currentPage'])
axios.defaults.withCredentials = true
const goodsPictureAddr =
  '/src/assets/galaxy-s23-ultra-highlights-colors-green-back-s (for goods).png'

// goodsCardInfo
// 商品展示卡片信息
// 商品图片url
// 商品名称
// 商品价格
interface GoodsCardInfo {
  goodsNo: string
  goodsPictureAddr: string
  goodsName: string
  goodsPrice: number
}
const goodCardInfoList: Ref<GoodsCardInfo[]> = ref([
  // {
  //   goodsPictureAddr: goodsPictureAddr,
  //   goodsName: '三星 s23',
  //   goodsPrice: 6999
  // }
])

// 后端发送过来的图片，进行一下接收
// const goodsPictureList1 = ref<string[]>([])
//默认访问路径
const imagesURLPrefix = 'http://localhost:8080/static/images/'
// for (let index = 0; index < 5; index++) {
//   goodsPictureList.push(goodsPictureAddr);
// }
const path = 'http://localhost:8080'
if (goodCardInfoList.value.length <= 0) {
  if (props.type == 'home') {
    axios({
      method: 'get',
      url: path + '/homeGoods',
      params: { pageNo: props.currentPage }
    })
      .then((response) => {
        console.log(response.data)
        var goodsInfo = response.data.data
        console.log(goodsInfo)

        for (var i = 0; i < goodsInfo.length; ++i) {
          var imageName = imagesURLPrefix + goodsInfo[i].goodsImageName + '.png'
          let tem: GoodsCardInfo = {
            // 临时变量 接受后端数据后，push到goodCardInfoList中
            goodsNo: '',
            goodsPictureAddr: goodsPictureAddr,
            goodsName: '',
            goodsPrice: 0
          }
          tem.goodsPictureAddr = imageName
          tem.goodsName = goodsInfo[i].goodsName
          tem.goodsPrice = parseFloat(goodsInfo[i].goodsPrice)
          tem.goodsNo = goodsInfo[i].goodsNo
          goodCardInfoList.value.push(tem)
        }
      })
      .catch((error) => {
        console.log('error = ' + error)
      })
  } else {
    axios({
      method: 'get',
      url: path + '/similarGoods',
      params: { type: props.type }
    })
      .then((response) => {
        console.log(response.data)
        var goodsInfo = response.data.data
        console.log(goodsInfo)

        for (var i = 0; i < goodsInfo.length; ++i) {
          var imageName = imagesURLPrefix + goodsInfo[i].goodsImageName + '.png'
          let tem: GoodsCardInfo = {
            // 临时变量 接受后端数据后，push到goodCardInfoList中
            goodsNo: '',
            goodsPictureAddr: goodsPictureAddr,
            goodsName: '',
            goodsPrice: 0
          }
          tem.goodsPictureAddr = imageName
          tem.goodsName = goodsInfo[i].goodsName
          tem.goodsPrice = parseFloat(goodsInfo[i].goodsPrice)
          tem.goodsNo = goodsInfo[i].goodsNo
          goodCardInfoList.value.push(tem)
        }
      })
      .catch((error) => {
        console.log('error = ' + error)
      })
  }
}
</script>

<template>
  <div>
    <el-row :gutter="200">
      <el-col :span="4" v-for="(item, index) in goodCardInfoList">
        <RouterLink :to="{ path: '/goodsInfo', query: { goodsNo: item.goodsNo } }">
          <el-card :body-style="{ padding: '5px' }" style="width: 200px">
            <img
              :key="index"
              :src="item.goodsPictureAddr"
              class="image"
              style="width: 200px; height: 220px"
            />
            <div class="bottom">
              {{ item.goodsName }}<br />
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
