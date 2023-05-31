import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useAddAddressStore = defineStore('AddAddress', () => {
  const addressNo = ref('')
  const consigneeName = ref('')
  const consigneeTel = ref('')
  const address = ref('')
  return { addressNo, consigneeName, consigneeTel, address }
})
