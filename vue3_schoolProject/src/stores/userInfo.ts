import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Ref } from 'vue'

export const useUserInfoStore = defineStore('userInfo', () => {
  const userAccount: Ref<string> = ref('1389478935')
  const userNakeName: Ref<string> = ref('hello')
  const userTel: Ref<string> = ref('100860086000')
  const registerDate: Ref<string> = ref('2023-5-24')
  const lastLoginTime: Ref<string> = ref('2023-5-24')
  const userTotalConsumption: Ref<number> = ref(6999)
  const userMoney: Ref<number> = ref(8888)
  const userAvatar: Ref<string> = ref('../assets/github3.svg')

  return {
    userAccount,
    userNakeName,
    userTel,
    registerDate,
    lastLoginTime,
    userTotalConsumption,
    userMoney,
    userAvatar
  }
})
