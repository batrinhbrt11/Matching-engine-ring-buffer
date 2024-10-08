import Logo from '@/components/ui/logo';
import Image from '@/components/ui/image';
import SignInForm from '@/components/auth/sign-in-form';

// import images and icons
import BitcoinImg from '@/assets/images/bit-coin.png';
import AnchorLink from '@/components/ui/links/anchor-link';
import routes from '@/config/routes';

export default function SignIn() {
  return (
    <div className="grid flex-grow grid-cols-1 gap-0 lg:grid-cols-[1fr_40%] 3xl:grid-cols-2">
      <div className="flex flex-col items-center justify-center py-14">
        <div className="grid w-full max-w-[408px] grid-cols-1 gap-4 px-4">
          <div className="mx-auto mb-2 w-20 lg:ml-0 xl:w-24">
            <Logo className="!w-full" />
          </div>
          <div className="mb-5 text-center lg:text-left">
            <h2 className="mb-2 text-xl font-medium uppercase dark:text-white lg:text-2xl">
              Welcome Back!
            </h2>
            <p className="text-sm text-[#4B5563] dark:text-gray-300">
              Please login account with your info
            </p>
          </div>

          <SignInForm />
          <p className="text-sm tracking-[0.5px] text-[#4B5563] dark:text-gray-300">
            Not member yet?{' '}
            <AnchorLink
              href={routes.signUp}
              className="font-medium underline hover:text-black/80 dark:text-gray-300"
            >
              Create an account
            </AnchorLink>
          </p>
        </div>
      </div>
      <div className="relative hidden bg-[#F3F4F6] lg:block">
        <Image src={BitcoinImg} alt="sign-up" fill className="object-cover" />
      </div>
    </div>
  );
}
