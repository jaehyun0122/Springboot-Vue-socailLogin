<template>
  
</template>

<script>
export default {
    name: 'IndexPage',
    computed: {
        ...mapGetters(['token', 'user']),
        isLoggedIn () {
            return this.token != null
        },
        username () {
        if (!this.user) return ''
            return this.user.username
        },
        isAdmin () {
            return this.user && this.user.roleCode === 'ROLE_ADMIN'
        }
    }
}
</script>

<style>

</style>