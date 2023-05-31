import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Ref } from 'vue'

export const useUserInfoStore = defineStore('userInfo', () => {
  const userAccount = ref('')
  const userNakeName = ref('')
  const userTel = ref('')
  const registerDate = ref('')
  const lastLoginTime = ref('')
  const userTotalConsumption = ref()
  const userMoney = ref()
  const userAvatar = ref('../assets/github3.svg')

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
