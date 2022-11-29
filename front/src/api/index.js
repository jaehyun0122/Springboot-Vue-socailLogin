import axios from "axios";
import { API_BASE_URL, APT_DEAL_URL } from "@/config/index.js";
import userStore from '@/store/index';
// axios 객체 생성

const apiInstance = axios.create({
  baseURL: API_BASE_URL,
});
apiInstance.interceptors.request.use(
	function(config) {
	  // 1. 요청 보내기 전에 실행
	  // store의 토큰 값 설정
	  config.headers.Authrozation = userStore.state.token;
	  return config;
	},
	function(error) {
	  // Do something with request error
	  return Promise.reject(error);
	},
);

const houseInstance = axios.create({
  baseURL: APT_DEAL_URL,
});

export { apiInstance, houseInstance };