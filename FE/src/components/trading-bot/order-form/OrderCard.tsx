import { OrderResponse } from '@/models/order';

interface OrderCardProps {
  details: OrderResponse;
}

export default function OrderCard({ details }: OrderCardProps) {
  return (
    <div className="rounded-lg border border-[#E2E8F0] bg-gray-100 dark:border-gray-700 dark:bg-light-dark dark:shadow-card">
      <div className="p-3 pb-4">
        <div className="mb-4 flex items-start justify-between">
          <div className="text-sm font-medium uppercase">
            <h2 className="mb-1 text-[#111827] dark:text-white">
              {details.asset.chain.symbol}/ {details.asset.coin.symbol}
            </h2>
            <p className="text-gray-500 dark:text-gray-400">
              {details.orderType}
            </p>
          </div>
        </div>
        <div className="flex flex-wrap gap-4">
          <div className="text-xs font-normal uppercase">
            <p className="text-gray-500 dark:text-gray-400">Price</p>
            <p className="text-[#111827] dark:text-white">{details.price}</p>
          </div>
          <div className="text-xs font-normal uppercase">
            <p className="text-gray-500 dark:text-gray-400">Amount</p>
            <p className="text-[#111827] dark:text-white">
              {details.remainingAmount}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
