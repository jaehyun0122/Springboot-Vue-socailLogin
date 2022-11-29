import { apiInstance } from './index';

const api = apiInstance;


export default {
  login(body, success, fail) {
    api.post('/login', body, success, fail)
  },
  getUser() {
    api.get('/user/me')
  },
};

