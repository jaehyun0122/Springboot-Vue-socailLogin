<template>
  <v-app>
    <Header/>
    <v-main>
    <v-btn color="success" @click="$router.push('/login')"> Login </v-btn>
    <v-btn color="success" nuxt to="/join"> Join </v-btn>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import Header from "@/components/Header.vue";
export default {
  name: "App",

  data: () => ({
    //
  }),
  components:{
    Header
  }
};
</script>
