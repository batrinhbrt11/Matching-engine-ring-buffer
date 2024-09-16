'use client';

// static data
import { coinChangeData } from '@/data/static/trading-data';

import cn from 'classnames';
import { Fragment, useState } from 'react';
import Input from '@/components/ui/forms/input';
import SimpleBar from '@/components/ui/simplebar';
import { Listbox } from '@/components/ui/listbox';
import { Transition } from '@/components/ui/transition';
import Image from '@/components/ui/image';

// import icons
import { ChevronDown } from '@/components/icons/chevron-down';
import { Wallet } from '@/models/wallet';

interface CoinChangeProps {
  cryptoWallets: Wallet[];
  currentWallet?: Wallet;
  handleChangeWallet: (wallet: Wallet) => void;
}

export default function CoinChange({
  cryptoWallets,
  currentWallet = cryptoWallets[0],
  handleChangeWallet,
}: CoinChangeProps) {
  return (
    <>
      <Listbox value={currentWallet} onChange={handleChangeWallet}>
        <div className="relative">
          <Listbox.Button className="flex items-center gap-3 rounded-full bg-gray-100 p-2 py-1.5 pr-6 dark:bg-light-dark">
            <Image
              src={currentWallet.assetDto?.coin.image || ''}
              alt={currentWallet.assetDto?.coin.name || ''}
              width={20}
              height={20}
            />
            <span className="inline-block">
              <p className="font-medium uppercase text-brand dark:text-white sm:text-lg text-left">
                {currentWallet.assetDto?.coin.symbol}
              </p>
              <p className="text-sm capitalize text-gray-500 dark:text-gray-300 text-left">
                {currentWallet.assetDto?.coin.name}
              </p>
            </span>
            <span className=" ml-2 text-gray-500">
              <ChevronDown className="h-auto w-3" />
            </span>
          </Listbox.Button>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <Listbox.Options className="absolute -left-3 top-full z-20 mt-1 rounded-lg border bg-white pb-4 shadow-main dark:border-gray-700 dark:bg-light-dark min-[375px]:left-0 min-[375px]:w-[320px] sm:w-[400px] sm:pb-6">
              <SimpleBar style={{ height: 446 - 156 }} className="px-2">
                {cryptoWallets.map((wallet) => (
                  <Listbox.Option
                    key={`coin-data-${wallet.id}`}
                    className="grid cursor-pointer grid-cols-12 items-center gap-1 rounded-lg px-2 py-4 text-sm hover:bg-gray-100 dark:hover:bg-gray-600/60 sm:px-4"
                    value={wallet}
                  >
                    <p className="col-span-3 flex items-center gap-2 font-medium uppercase text-dark dark:text-gray-300">
                      <Image
                        src={wallet.assetDto?.coin.image || ''}
                        alt={wallet.assetDto?.coin.name || ''}
                        width={20}
                        height={20}
                      />
                      <span>{wallet.assetDto?.coin.name}</span>
                    </p>

                    <p className="col-span-2 text-end font-normal text-gray-500">
                      {wallet.availableBalance}
                    </p>
                  </Listbox.Option>
                ))}
              </SimpleBar>
            </Listbox.Options>
          </Transition>
        </div>
      </Listbox>
    </>
  );
}
