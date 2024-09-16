import axios from 'axios';

import { HttpResponse } from '../models/http';

const axiosClient = axios.create({
  baseURL: process.env.REACT_APP_BASE_GATEWAY_URL || 'http://localhost:8081',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: false,
});

const handleRequest = (promise: Promise<HttpResponse>) => {
  return promise.then((res) => res).catch((err) => err as HttpResponse<any>);
};

export default axiosClient;

export { handleRequest };
