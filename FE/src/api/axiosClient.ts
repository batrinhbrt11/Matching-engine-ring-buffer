import axios from 'axios';

import { HttpResponse } from '../models/http';

const axiosClient = axios.create({
  baseURL: 'https://matching-engine-ring-buffer-1.onrender.com',
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
