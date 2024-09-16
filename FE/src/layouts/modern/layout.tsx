import cn from 'classnames';
import Header from '@/layouts/header/header';
import Sidebar from '@/layouts/sidebar/_default';

export default function ModernLayout({
  children,
  contentClassName,
}: React.PropsWithChildren<{ contentClassName?: string }>) {
  return (
    <div className="rtl:xl:pr-72 rtl:2xl:pr-80 ">
      <Header />
      <main
        className={cn(
          'min-h-[100vh] px-4 pb-16 pt-4 sm:px-6 sm:pb-20 lg:px-8 xl:pb-24 3xl:px-10 3xl:pt-0.5 w-full',
          contentClassName,
        )}
      >
        {children}
      </main>
    </div>
  );
}
