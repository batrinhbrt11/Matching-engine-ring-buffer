'use client';

//images
import AuthorImage from '@/assets/images/author.jpg';
import BotFilterTab from '../trading-bot/bot-filter-tab';
import CoinBar from '../trading-bot/coin-bar';
import CoinList from '../trading-bot/coin-list';
import TradingChart from '../trading-bot/trading-chart';
import InvestForm from '../trading-bot/invest-form';
import InvestmentTab from '../trading-bot/investment-tab';
import {
  candlesDataTwo,
  coinCardData,
  volumeData,
} from '@/data/static/trading-data';
import OrderForm from '../trading-bot/order-form/OrderForm';
import { useEffect, useState } from 'react';
import { Wallet } from '@/models/wallet';
import walletApi from '@/api/walletApi';
import CoinChange from '../trading-bot/coin-change';
import SimpleBar from 'simplebar-react';
import CoinCard from '../trading-bot/coin-card';
import { OrderResponse } from '@/models/order';
import orderApi from '@/api/orderApi';
import OrderCard from '../trading-bot/order-form/OrderCard';
import { getLocalStorage } from '@/utils/sessionStorage';
import { useRouter } from 'next/navigation';
import { UserInfo } from '@/models/auth';
import authApi from '@/api/authApi';

export default function ModernScreen() {
  const [cryptoWallets, setCryptoWallets] = useState<Wallet[]>();
  const [currentWallet, setCurrentWallet] = useState<Wallet>();
  const [orders, setOrders] = useState<OrderResponse[]>([]);
  const [user, setUser] = useState<UserInfo>();
  const [fiatWallet, setFiatWallet] = useState<Wallet>();
  const router = useRouter();

  useEffect(() => {
    if (!getLocalStorage('access_token')) {
      router.push('/authentication');
    }
  }, [router]);

  const getCryptoWallet = async () => {
    const { ok, body } = await walletApi.getCryptoWallet();
    if (ok && body) {
      setCryptoWallets(body);
      setCurrentWallet(body[0]);
    }
  };

  const getCryptoWalletNoneSet = async () => {
    const { ok, body } = await walletApi.getCryptoWallet();
    if (ok && body) {
      setCryptoWallets(body);
    }
  };

  const getWallet = async () => {
    const { ok, body } = await walletApi.getWallet();

    if (ok && body) {
      setFiatWallet(body);
    }
  };

  const getOrders = async () => {
    const { ok, body } = await orderApi.getOrder();
    if (ok && body) {
      setOrders(body);
    }
  };

  const getUserInfo = async () => {
    const { ok, body } = await authApi.getUser();
    if (ok && body) {
      setUser(body);
    }
  };

  useEffect(() => {
    getCryptoWallet();
    getUserInfo();
    getWallet();
  }, []);

  useEffect(() => {
    let intervalId: NodeJS.Timeout;
    intervalId = setInterval(() => {
      getOrders();
    }, 5000);
    return () => clearInterval(intervalId);
  }, []);

  const handleChangeWallet = (wallet: Wallet) => {
    setCurrentWallet(wallet);
  };

  return (
    <>
      {currentWallet?.assetDto && (
        <div className="mt-4 grid grid-cols-12 gap-6 @container">
          <div className="order-3 col-span-full  @[107.5rem]:order-1  min-h-10">
            <div className="h-full rounded-lg bg-white p-4 pb-6 shadow-card dark:bg-light-dark sm:px-6 2xl:px-8 2xl:pb-9">
              <SimpleBar
                style={{ maxHeight: 450 }}
                className="-mx-0.5 px-0.5 @container"
              >
                <p className="mb-4">List Order</p>
                <div className="grid grid-cols-1 gap-3 @xs:grid-cols-2 @2xl:grid-cols-3 @6xl:grid-cols-4">
                  {/* {.map((item) => (
                      <CoinCard
                        key={`coin-card-details-${item.id}`}
                        details={item}
                      />
                    ))} */}
                  {orders &&
                    orders.length > 0 &&
                    orders.map((item) => (
                      <OrderCard key={item.id} details={item} />
                    ))}
                </div>
              </SimpleBar>
            </div>
          </div>
          {cryptoWallets && fiatWallet && (
            <div className="order-2 col-span-full @4xl:col-span-6 @6xl:order-2 @7xl:order-2 @7xl:col-span-4 @[107.5rem]:order-3 @[107.5rem]:col-span-3">
              <OrderForm
                asset={currentWallet?.assetDto}
                cryptoWallets={cryptoWallets}
                fiatWallet={fiatWallet}
                getWallet={getWallet}
                getCryptoWallet={getCryptoWalletNoneSet}
              />
            </div>
          )}

          <div className="order-1  col-span-8 @[107.5rem]:order-2 ">
            {cryptoWallets && (
              <CoinChange
                cryptoWallets={cryptoWallets}
                currentWallet={currentWallet}
                handleChangeWallet={handleChangeWallet}
              />
            )}
            <TradingChart data={candlesDataTwo} volumeData={volumeData} />
          </div>
        </div>
      )}

      {/* <InvestmentTab /> */}
    </>
  );
}
