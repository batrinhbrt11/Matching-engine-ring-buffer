import {
  LoginFormRequestModel,
  LoginFormResponseModel,
  RegisterFormRequestModel,
  UserInfo,
} from '@/models/auth';
import { HttpResponse } from '@/models/http';
import axiosClient, { handleRequest } from './axiosClient';
import { getLocalStorage } from '@/utils/sessionStorage';

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
  getUser: (): Promise<HttpResponse<UserInfo>> => {
    const url = `/api/user`;
    return handleRequest(
      axiosClient.get(url, {
        headers: {
          Authorization: `Bearer ${getLocalStorage('access_token')}`,
        },
      }),
    );
  },
};

export default authApi;
