export interface LoginFormRequestModel {
  email: string;
  password: string;
}
export interface LoginFormResponseModel {
  accessToken: string;
  refreshToken: string;
}

export interface RegisterFormRequestModel {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: string;
}
