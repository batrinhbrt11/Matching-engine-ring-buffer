import {
  LoginFormRequestModel,
  LoginFormResponseModel,
  RegisterFormRequestModel,
} from '@/models/auth';
import { HttpResponse } from '@/models/http';
import axiosClient, { handleRequest } from './axiosClient';

const authApi = {
  login: (
    body: LoginFormRequestModel,
  ): Promise<HttpResponse<LoginFormResponseModel>> => {
    const url = `/api/login`;
    return handleRequest(axiosClient.post(url, body));
  },
  register: (body: RegisterFormRequestModel) => {
    const url = `/api/register`;
    return handleRequest(axiosClient.post(url, body));
  },
};

export default authApi;
