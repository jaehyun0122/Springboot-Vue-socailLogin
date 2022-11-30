import axios from "axios";
import { API_BASE_URL } from "@/config/index.js";
// axios 객체 생성

function apiInstance() {
	return axios.create({
		baseURL: API_BASE_URL,
		headers: {
			Authorization: "Bearer "+JSON.parse(sessionStorage.getItem("vuex")).userStore.token
		}
	})
}

export { apiInstance };

// const apiInstance = axios.create({
//   baseURL: API_BASE_URL,
// });

// const houseInstance = axios.create({
// 	baseURL: APT_DEAL_URL,
//   });