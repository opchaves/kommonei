import Axios, { AxiosError, RawAxiosRequestConfig } from 'axios';

const baseURL = import.meta.env.REACT_APP_API_SERVER ?? '/';

export const AXIOS_INSTANCE = Axios.create({ baseURL });

export const useAxiosMutator = <T>(): ((config: RawAxiosRequestConfig) => Promise<T>) => {
  return (config: RawAxiosRequestConfig) => {
    const source = Axios.CancelToken.source();
    const promise = AXIOS_INSTANCE({ ...config, cancelToken: source.token }).then(
      ({ data }) => data,
    );

    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    promise.cancel = () => {
      source.cancel('Query was cancelled by React Query!');
    };

    return promise;
  };
};

export default useAxiosMutator;

// In some case with react-query and swr you want to be able to override the return error type so you can also do it here like this
export type ErrorType<Error> = AxiosError<Error>;
