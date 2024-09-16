import { CancelToken } from 'axios';

export interface BadRequestFieldError {
  [key: string]: string[];
}

export interface HttpError {
  unauthorized: boolean;
  badRequest: boolean;
  notFound: boolean;
  clientError: boolean;
  serverError: boolean;
  message: string;
  title?: string;
  fieldErrors?: BadRequestFieldError;
  errors?: any;
}

export interface HttpResponse<T = never> {
  status: number;
  ok: boolean;
  body?: T;
  pagination?: Pagination;
  error?: HttpError;
}

export interface BaseRequestQueryParam {
  page?: number;
  size?: number;
  sort?: string;
  cancelToken?: CancelToken;
  [key: string]: any;
}

export interface Pagination {
  // paging: any;
  total: number;
}

export interface AxiosHeaders {
  [key: string]: any;
}
