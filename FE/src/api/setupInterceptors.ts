import { AxiosResponse } from 'axios';
import axiosClient from './axiosClient';
import AxiosResponseData from '@/models/axios';
import { BadRequestFieldError, HttpResponse } from '@/models/http';
import { isEmptyObject, removeLocalStorage } from '@/utils/sessionStorage';

const setup = () => {
  axiosClient.interceptors.request.use(
    (config) => config,
    (error) => Promise.reject(error),
  );

  axiosClient.interceptors.response.use(
    // @ts-expect-error: we want to return the different data type
    (response: AxiosResponse<AxiosResponseData>) => {
      const { status, data: responseData, headers } = response;

      const data: HttpResponse<object> = {
        status,
        ok: true,
        body: responseData,
      };

      if (headers.link) {
        data.pagination = {
          total: Number(headers['x-total-count']),
        };
      }

      return data;
    },
    ({ response }) => {
      const { status, data } = response as AxiosResponse<AxiosResponseData>;
      const fieldErrors: BadRequestFieldError = {};

      if (data?.violations?.length) {
        data.violations.forEach(() => {
          const { field, message } = data.violations[0];

          if (fieldErrors[field]) {
            fieldErrors[field].push(message);
          } else {
            fieldErrors[field] = [message];
          }
        });
      }

      if (status === 401) {
        removeLocalStorage('access_token');
      }
      const error: HttpResponse = {
        status,
        ok: false,
        error: {
          unauthorized: status === 401,
          badRequest: status === 400,
          notFound: status === 404,
          clientError: status >= 400 && status <= 499,
          serverError: status >= 500 && status <= 599,
          message: data.detail,
          title: `${data.detail}-title`,
          fieldErrors: isEmptyObject(fieldErrors) ? undefined : fieldErrors,
          errors: data.errors,
        },
      };

      return Promise.reject(error);
    },
  );
};

export default setup;
