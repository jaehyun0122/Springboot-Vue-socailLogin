import accountApi from "@/api/account.js"

const userStore = {
    namespaced: true,
    state: {
        user: null,
        token: null,
        test: "testCall"
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
        },
        setToken(state, token) {
            state.token = token;
        },
    },
    getters: {
        user(state) {
        return state.user;
        },
        token(state) {
        return state.token;
        },
    },
    actions: {
        fetchUser({ state, commit }) {
        console.log("token", state.token);
            accountApi.getUser((res) => {
                console.log(res.data)
                commit("setUser", res.data)
        })
        },
    },
};

export default userStore;