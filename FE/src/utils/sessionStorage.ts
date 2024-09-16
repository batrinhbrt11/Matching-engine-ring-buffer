const getLocalStorage = (key: string): string | null => {
  if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
    return localStorage.getItem(key);
  }
  return null;
};
const setLocalStorage = (key: string, value: any) => {
  const parsedValue = typeof value === 'object' ? JSON.stringify(value) : value;
  localStorage.setItem(key, parsedValue as string);
};

const removeLocalStorage = (key: string) => localStorage.removeItem(key);

const isEmptyObject = (obj: object): boolean =>
  !obj ||
  (Object.keys(obj).length === 0 &&
    Object.getPrototypeOf(obj) === Object.prototype);

export { getLocalStorage, setLocalStorage, removeLocalStorage, isEmptyObject };
