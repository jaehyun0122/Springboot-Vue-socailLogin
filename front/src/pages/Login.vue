<template>
  <v-col>
    <v-card-title class="headline"> 로그인 </v-card-title>

    <v-btn color="success" @click="kakao">카카오</v-btn>
    <v-btn @click="naver">네이버</v-btn>

    <hr class="my-3" />
    <input
      id="email"
      v-model="email"
      class="base_input"
      type="text"
      placeholder="email"
      style="background: #fff; border: 1px solid #fff"
    />
    <br /><br />
    <input
      v-model="password"
      class="base_input"
      type="password"
      placeholder="password"
      style="background: #fff; border: 1px solid #fff"
    />
    <br /><br />
    <v-btn color="warning" @click="login">Login</v-btn>
  </v-col>
</template>

<script>
import axios from "axios";
import { mapMutations, mapActions, mapState } from "vuex";
const userStore = "userStore";
import {API_BASE_URL} from "@/config"
export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  computed:{
    ...mapState(userStore, ["user", "token"])
  },
  methods: {
    ...mapActions(userStore, ["fetchUser"]),
    ...mapMutations(userStore, ["setToken"]),
    kakao() {
      // location.href =
      //   "http://localhost:8080/oauth2/authorization/kakao?redirect_uri=http://localhost:3000/oauth/redirect";
      axios.get(API_BASE_URL+'/user/me',{
        headers:{
          Authorization: "Bearer "+this.token
        }
      }).then(res=>{
        console.log(res.data)
      })
    },
    naver() {
      location.href =
        "http://localhost:8080/oauth2/authorization/naver?redirect_uri=http://localhost:3000/oauth/redirect";
    },
    login() {
      axios
        .post("http://localhost:8080/api/v1/auth/login", {
          email: this.email,
          password: this.password,
        })
        .then((res) => {
          const token = res.data.body.token;
          if (token) {
            this.setToken(token);
            this.fetchUser();
          }

          this.$router.replace("/");
        });
    },
  },
};
</script>

<style></style>
