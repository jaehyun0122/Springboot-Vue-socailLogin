import { apiInstance } from './index';

const api = apiInstance();
// api.interceptors.request.use(
// 	function(config) {
// 	  // 1. 요청 보내기 전에 실행
// 	  // store의 토큰 값 설정
//     const token = JSON.parse(sessionStorage.getItem("vuex")).userStore.token
// 	  config.headers.Authrozation = "Bearer "+token;
// 	  return config;
// 	},
// 	function(error) {
// 	  // Do something with request error
// 	  return Promise.reject(error);
// 	},
// );
function login(body, success, fail) {
  api.post('/login', body, success, fail)
}
async function getUser(success, fail) {
  await api.get('/user/me').then(success).catch(fail)
}
export default {
  login,
  getUser
};

