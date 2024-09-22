export interface Wallet {
  id: number;
  availableBalance: string;
  freezeBalance: string;
  assetDto: Asset;
}

export interface Asset {
  id: number;
  coin: Coin;
  chain: Chain;
  nativeToken: boolean;
}

export interface Coin {
  id: number;
  name: string;
  symbol: string;
  image: string;
}

export interface Chain {
  id: number;
  name: string;
  symbol: string;
  image: string;
}
