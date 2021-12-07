<template>
  <div style="height:100%">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style lang="css">
    *{
      margin: 0;
      padding: 0;
    }

    html,body{
      width: 100%;
      height: 100%;
    }

    body{
      background: #ebedee;
    }   
</style>

