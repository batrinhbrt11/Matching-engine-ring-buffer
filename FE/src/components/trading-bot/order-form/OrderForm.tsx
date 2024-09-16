'use client';

import Button from '@/components/ui/button/button';
import Input from '@/components/ui/forms/input';
import { Asset } from '@/models/wallet';
import { useMemo, useState } from 'react';
import { Tab, TabPanels, TabPanel } from '@/components/ui/tab';
import classNames from 'classnames';
import { motion } from 'framer-motion';
import TransactCoin from '@/components/ui/transact-coin';
import { Transition } from '@headlessui/react';
import { Listbox } from '@/components/ui/listbox';
import InputLabel from '@/components/ui/input-label';
import { ChevronDown } from '@/components/icons/chevron-down';
import orderApi from '@/api/orderApi';

interface OrderFormProps {
  asset: Asset;
}

const tabMenu = [
  {
    title: 'Buy',
    path: 'Buy',
  },
  {
    title: 'Sell',
    path: 'Sell',
  },
];

export default function OrderForm({ asset }: OrderFormProps) {
  const [selectedTab, setSelectedTab] = useState(tabMenu[0]);
  const [formData, setFormData] = useState({
    price: '',
    assetAmount: '',
  });

  const total = useMemo(
    () =>
      parseFloat(formData.price || '0') *
      parseFloat(formData.assetAmount || '0'),
    [formData],
  );

  const placeOrder = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const { ok } = await orderApi.placeOrder({
      orderType: selectedTab.path === 'Buy' ? 'BUY' : 'SELL',
      price: parseFloat(formData.price),
      assetAmount: parseFloat(formData.assetAmount),
      assetId: asset.id,
    });
    if (ok) {
      setFormData({
        price: '',
        assetAmount: '',
      });
    }
  };

  return (
    <>
      <form
        noValidate
        onSubmit={placeOrder}
        className="flex flex-col rounded-lg bg-white p-4 shadow-card dark:bg-light-dark sm:p-6 lg:h-full 2xl:px-8 "
      >
        <div className="mb-4 border-b border-dashed border-gray-200 pb-4">
          <InputLabel
            className="!mb-2 sm:!mb-3 "
            titleClassName="!capitalize !font-normal"
            title="Order Type"
          />

          <div className="relative">
            <Listbox value={selectedTab} onChange={setSelectedTab}>
              <Listbox.Button className="text-case-inherit letter-space-inherit flex h-10 w-full items-center justify-between rounded-lg border border-[#E2E8F0] bg-gray-200/50 px-4 text-sm font-medium text-gray-900 outline-none transition-shadow duration-200 hover:border-gray-900 hover:ring-1 hover:ring-gray-900 dark:border-gray-700 dark:bg-light-dark dark:text-gray-100 dark:hover:border-gray-600 dark:hover:ring-gray-600 sm:h-12 sm:px-5">
                <div className="flex items-center">{selectedTab.title}</div>
                <ChevronDown />
              </Listbox.Button>
              <Transition
                leave="transition ease-in duration-100"
                leaveFrom="opacity-100"
                leaveTo="opacity-0"
              >
                <Listbox.Options className="absolute left-0 z-10 mt-1 grid w-full origin-top-right gap-0.5 rounded-lg border border-gray-200 bg-white p-1 shadow-large outline-none dark:border-gray-700 dark:bg-light-dark xs:p-2">
                  {tabMenu.map((option) => (
                    <Listbox.Option key={option.title} value={option}>
                      {({ selected }) => (
                        <div
                          className={classNames(
                            'flex cursor-pointer items-center rounded-md px-3 py-2 text-sm text-gray-900 transition dark:text-gray-100',
                            selected
                              ? 'bg-gray-200/70 font-medium dark:bg-gray-600/60'
                              : 'hover:bg-gray-100 dark:hover:bg-gray-700/70',
                          )}
                        >
                          {option.title}
                        </div>
                      )}
                    </Listbox.Option>
                  ))}
                </Listbox.Options>
              </Transition>
            </Listbox>
          </div>
        </div>
        <div className="flex-grow">
          <div className="grid grid-cols-1 gap-6">
            <div className="text-sm font-normal">
              <p className="mb-2 text-[#111827] dark:text-white sm:mb-3">
                Price(USDT)
              </p>
              <div className="relative">
                <Input
                  type="text"
                  placeholder="0.00"
                  autoComplete="off"
                  value={formData.price}
                  onChange={(e) =>
                    setFormData({ ...formData, price: e.target.value })
                  }
                  inputClassName="border-[#E2E8F0] dark:!bg-light-dark reset-password-pin-code appearance-none rounded-lg placeholder:!text-gray-500 !bg-gray-200/50 !text-sm !font-medium pr-16 pl-4"
                />
                <span className="absolute right-5 top-1/2 -translate-y-1/2">
                  USDT
                </span>
              </div>
            </div>
            <div className="text-sm font-normal">
              <p className="mb-2 text-[#111827] dark:text-white sm:mb-3">
                Amount ({asset.coin.symbol})
              </p>
              <div className="relative">
                <Input
                  type="number"
                  placeholder="0.00"
                  autoComplete="off"
                  value={formData.assetAmount}
                  onChange={(e) =>
                    setFormData({ ...formData, assetAmount: e.target.value })
                  }
                  inputClassName="border-[#E2E8F0] dark:!bg-light-dark reset-password-pin-code appearance-none rounded-lg placeholder:!text-gray-500 !bg-gray-200/50 !text-sm !font-medium pr-16 pl-4"
                />
                <span className="absolute right-5 top-1/2 -translate-y-1/2">
                  {asset.coin.symbol}
                </span>
              </div>
            </div>
            <div className="text-sm font-normal">
              <p className="mb-2 text-[#111827] dark:text-white sm:mb-3">
                Total (USDT)
              </p>
              <div className="relative">
                <Input
                  type="text"
                  placeholder="0.00"
                  autoComplete="off"
                  value={total.toString()}
                  disabled
                  // onChange={(e: ChangeEvent<HTMLInputElement>) =>
                  //   setState(e.target.valueAsNumber)
                  // }
                  inputClassName="border-[#E2E8F0] dark:!bg-light-dark reset-password-pin-code appearance-none rounded-lg placeholder:!text-gray-500 !bg-gray-200/50 !text-sm !font-medium pr-16 pl-4"
                />
                <span className="absolute right-5 top-1/2 -translate-y-1/2">
                  USDT
                </span>
              </div>
            </div>
          </div>
        </div>
        <Button
          type="submit"
          shape="rounded"
          className="mt-8 w-full !font-bold uppercase dark:bg-blue-800"
        >
          Create
        </Button>
      </form>
    </>
  );
}
