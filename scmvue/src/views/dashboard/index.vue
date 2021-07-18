<template>
  <div class="dashboard-container">
    <div class="dashboard-text">{{ userCode }}-{{ userName }} 你好，欢迎登陆系统！</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'userId',
      'userName',
      'userCode'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 14px;
    line-height: 46px;
  }
}
</style>
