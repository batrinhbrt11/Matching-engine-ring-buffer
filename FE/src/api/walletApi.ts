import { getLocalStorage } from '@/utils/sessionStorage';
import axiosClient, { handleRequest } from './axiosClient';
import { Wallet } from '@/models/wallet';
import { HttpResponse } from '@/models/http';

const walletApi = {
  getWallet: (): Promise<HttpResponse<Wallet>> => {
    const url = `/api/wallet`;
    return handleRequest(
      axiosClient.get(url, {
        headers: {
          Authorization: `Bearer ${getLocalStorage('access_token')}`,
        },
      }),
    );
  },
  getCryptoWallet: (): Promise<HttpResponse<Wallet[]>> => {
    const url = `/api/wallet/crypto`;
    return handleRequest(
      axiosClient.get(url, {
        headers: {
          Authorization: `Bearer ${getLocalStorage('access_token')}`,
        },
      }),
    );
  },
};

export default walletApi;
