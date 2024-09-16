export interface Wallet {
  icon: any;
  id: number;
  availableBalance: string;
  freezeBalance: string;
  assetDto: Asset | null;
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
