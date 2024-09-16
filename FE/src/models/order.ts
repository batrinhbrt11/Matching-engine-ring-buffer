import { Asset } from './wallet';

export interface OrderRequest {
  orderType: 'BUY' | 'SELL';
  price: number;
  assetAmount: number;
  assetId: number;
}

export interface OrderResponse {
  id: number;
  orderType: 'BUY' | 'SELL';
  orderStatus: 'OPEN' | 'CANCEL' | 'SUCCESS';
  price: string;
  remainingAmount: string;
  asset: Asset;
}
