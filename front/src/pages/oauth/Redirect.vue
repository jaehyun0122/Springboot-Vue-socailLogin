<template lang="ko">
</template>

<script>
import { mapMutations, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "redirect",
  created() {
    const token = this.$route.query.token;
    if (token) {
      this.setToken(token);
      this.fetchUser();
    }
    this.$router.replace("/");
  },
  methods: {
    ...mapActions(userStore, ["fetchUser"]),
    ...mapMutations(userStore, ["setToken"]),
  },
};
</script>
