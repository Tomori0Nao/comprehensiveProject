import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useEditAdminStore = defineStore('EditAdmin', () => {
    // adminType: string
//   adminNakeName: string
//   adminAccount: string
//   adminAccountState: string
//   rigisterDate: string
//   isBaned: boolean
  const adminType = ref('')
  const adminNakeName = ref('')
  const adminAccount = ref('')
  const rigisterDate = ref('')
  const isBaned = ref(false)
//   const rigisterDate = ref('')

  return { adminType, adminNakeName, adminAccount, rigisterDate,isBaned }
})
