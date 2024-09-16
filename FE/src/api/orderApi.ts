import { HttpResponse } from '@/models/http';
import { OrderRequest, OrderResponse } from '@/models/order';
import axiosClient, { handleRequest } from './axiosClient';
import { getLocalStorage } from '@/utils/sessionStorage';

const orderApi = {
  placeOrder: (request: OrderRequest) => {
    const url = `/api/order`;
    return handleRequest(
      axiosClient.post(url, request, {
        headers: {
          Authorization: `Bearer ${getLocalStorage('access_token')}`,
        },
      }),
    );
  },
  getOrder: (): Promise<HttpResponse<OrderResponse[]>> => {
    const url = `/api/order`;
    return handleRequest(
      axiosClient.get(url, {
        headers: {
          Authorization: `Bearer ${getLocalStorage('access_token')}`,
        },
      }),
    );
  },
};

export default orderApi;
