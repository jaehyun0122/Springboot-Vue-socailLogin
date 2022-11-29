import accountApi from "@/api/account.js"
// import API_BASE_URL from "@/config/index.js"
// import axios from 'axios';


const userStore = {
    namespaced: true,
    state: {
        user: null,
        token: null,
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
        fetchUser({ state, commit }, callback) {
        console.log("token", state.token);
        state.user
            ? callback && callback()
            : accountApi.getUser()
                .then(res => {
                    console.log("fetchUser : " + res.data)
                    commit("setUser", res.user)
            })
        },
    },
};

export default userStore;

// axios.get("http://localhost:8080/api/v1/" + 'user/me', {
//                 headers: {
//                     Authorization: 'Bearer '+state.token
//                 }
//             }).then(res => {
//                 console.log(res.data)
//                 commit("setUser", res.user);
//                 callback && callback();
//             });