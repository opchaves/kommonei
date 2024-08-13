/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import { useMutation, useQuery } from '@tanstack/react-query';
import type {
  DefinedInitialDataOptions,
  DefinedUseQueryResult,
  MutationFunction,
  QueryFunction,
  QueryKey,
  UndefinedInitialDataOptions,
  UseMutationOptions,
  UseMutationResult,
  UseQueryOptions,
  UseQueryResult,
} from '@tanstack/react-query';
import { useCallback } from 'react';
import type {
  LoggerInfo,
  LoggerLevel,
  LoggingManagerGetAllParams,
  LoggingManagerUpdateBody,
} from '../../models';
import { useAxiosMutator } from '../../../AxiosMutator';
import type { ErrorType } from '../../../AxiosMutator';

/**
 * Get information on all loggers or a specific logger.
 * @summary Information on Logger(s)
 */
export const useLoggingManagerGetAllHook = () => {
  const loggingManagerGetAll = useAxiosMutator<LoggerInfo[]>();

  return useCallback(
    (params?: LoggingManagerGetAllParams, signal?: AbortSignal) => {
      return loggingManagerGetAll({ url: `/q/logging-manager`, method: 'GET', params, signal });
    },
    [loggingManagerGetAll],
  );
};

export const getLoggingManagerGetAllQueryKey = (params?: LoggingManagerGetAllParams) => {
  return [`/q/logging-manager`, ...(params ? [params] : [])] as const;
};

export const useLoggingManagerGetAllQueryOptions = <
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
  TError = ErrorType<void>,
>(
  params?: LoggingManagerGetAllParams,
  options?: {
    query?: Partial<
      UseQueryOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
        TError,
        TData
      >
    >;
  },
) => {
  const { query: queryOptions } = options ?? {};

  const queryKey = queryOptions?.queryKey ?? getLoggingManagerGetAllQueryKey(params);

  const loggingManagerGetAll = useLoggingManagerGetAllHook();

  const queryFn: QueryFunction<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>
  > = ({ signal }) => loggingManagerGetAll(params, signal);

  return { queryKey, queryFn, ...queryOptions } as UseQueryOptions<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
    TError,
    TData
  > & { queryKey: QueryKey };
};

export type LoggingManagerGetAllQueryResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>
>;
export type LoggingManagerGetAllQueryError = ErrorType<void>;

export function useLoggingManagerGetAll<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
  TError = ErrorType<void>,
>(
  params: undefined | LoggingManagerGetAllParams,
  options: {
    query: Partial<
      UseQueryOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
        TError,
        TData
      >
    > &
      Pick<
        DefinedInitialDataOptions<
          Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
          TError,
          TData
        >,
        'initialData'
      >;
  },
): DefinedUseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useLoggingManagerGetAll<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
  TError = ErrorType<void>,
>(
  params?: LoggingManagerGetAllParams,
  options?: {
    query?: Partial<
      UseQueryOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
        TError,
        TData
      >
    > &
      Pick<
        UndefinedInitialDataOptions<
          Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
          TError,
          TData
        >,
        'initialData'
      >;
  },
): UseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useLoggingManagerGetAll<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
  TError = ErrorType<void>,
>(
  params?: LoggingManagerGetAllParams,
  options?: {
    query?: Partial<
      UseQueryOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
        TError,
        TData
      >
    >;
  },
): UseQueryResult<TData, TError> & { queryKey: QueryKey };
/**
 * @summary Information on Logger(s)
 */

export function useLoggingManagerGetAll<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
  TError = ErrorType<void>,
>(
  params?: LoggingManagerGetAllParams,
  options?: {
    query?: Partial<
      UseQueryOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerGetAllHook>>>,
        TError,
        TData
      >
    >;
  },
): UseQueryResult<TData, TError> & { queryKey: QueryKey } {
  const queryOptions = useLoggingManagerGetAllQueryOptions(params, options);

  const query = useQuery(queryOptions) as UseQueryResult<TData, TError> & { queryKey: QueryKey };

  query.queryKey = queryOptions.queryKey;

  return query;
}

/**
 * Update a log level for a certain logger
 * @summary Update log level
 */
export const useLoggingManagerUpdateHook = () => {
  const loggingManagerUpdate = useAxiosMutator<void>();

  return useCallback(
    (loggingManagerUpdateBody: LoggingManagerUpdateBody) => {
      const formUrlEncoded = new URLSearchParams();
      if (loggingManagerUpdateBody.loggerLevel !== undefined) {
        formUrlEncoded.append('loggerLevel', loggingManagerUpdateBody.loggerLevel);
      }
      if (loggingManagerUpdateBody.loggerName !== undefined) {
        formUrlEncoded.append('loggerName', loggingManagerUpdateBody.loggerName);
      }

      return loggingManagerUpdate({
        url: `/q/logging-manager`,
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        data: formUrlEncoded,
      });
    },
    [loggingManagerUpdate],
  );
};

export const useLoggingManagerUpdateMutationOptions = <
  TError = ErrorType<unknown>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>,
    TError,
    { data: LoggingManagerUpdateBody },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>,
  TError,
  { data: LoggingManagerUpdateBody },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const loggingManagerUpdate = useLoggingManagerUpdateHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>,
    { data: LoggingManagerUpdateBody }
  > = (props) => {
    const { data } = props ?? {};

    return loggingManagerUpdate(data);
  };

  return { mutationFn, ...mutationOptions };
};

export type LoggingManagerUpdateMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>
>;
export type LoggingManagerUpdateMutationBody = LoggingManagerUpdateBody;
export type LoggingManagerUpdateMutationError = ErrorType<unknown>;

/**
 * @summary Update log level
 */
export const useLoggingManagerUpdate = <TError = ErrorType<unknown>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>,
    TError,
    { data: LoggingManagerUpdateBody },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof useLoggingManagerUpdateHook>>>,
  TError,
  { data: LoggingManagerUpdateBody },
  TContext
> => {
  const mutationOptions = useLoggingManagerUpdateMutationOptions(options);

  return useMutation(mutationOptions);
};
/**
 * This returns all possible log levels
 * @summary Get all available levels
 */
export const useLoggingManagerLevelsHook = () => {
  const loggingManagerLevels = useAxiosMutator<LoggerLevel[]>();

  return useCallback(
    (signal?: AbortSignal) => {
      return loggingManagerLevels({ url: `/q/logging-manager/levels`, method: 'GET', signal });
    },
    [loggingManagerLevels],
  );
};

export const getLoggingManagerLevelsQueryKey = () => {
  return [`/q/logging-manager/levels`] as const;
};

export const useLoggingManagerLevelsQueryOptions = <
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
  TError = ErrorType<unknown>,
>(options?: {
  query?: Partial<
    UseQueryOptions<
      Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
      TError,
      TData
    >
  >;
}) => {
  const { query: queryOptions } = options ?? {};

  const queryKey = queryOptions?.queryKey ?? getLoggingManagerLevelsQueryKey();

  const loggingManagerLevels = useLoggingManagerLevelsHook();

  const queryFn: QueryFunction<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>
  > = ({ signal }) => loggingManagerLevels(signal);

  return { queryKey, queryFn, ...queryOptions } as UseQueryOptions<
    Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
    TError,
    TData
  > & { queryKey: QueryKey };
};

export type LoggingManagerLevelsQueryResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>
>;
export type LoggingManagerLevelsQueryError = ErrorType<unknown>;

export function useLoggingManagerLevels<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
  TError = ErrorType<unknown>,
>(options: {
  query: Partial<
    UseQueryOptions<
      Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
      TError,
      TData
    >
  > &
    Pick<
      DefinedInitialDataOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
        TError,
        TData
      >,
      'initialData'
    >;
}): DefinedUseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useLoggingManagerLevels<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
  TError = ErrorType<unknown>,
>(options?: {
  query?: Partial<
    UseQueryOptions<
      Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
      TError,
      TData
    >
  > &
    Pick<
      UndefinedInitialDataOptions<
        Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
        TError,
        TData
      >,
      'initialData'
    >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useLoggingManagerLevels<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
  TError = ErrorType<unknown>,
>(options?: {
  query?: Partial<
    UseQueryOptions<
      Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
      TError,
      TData
    >
  >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey };
/**
 * @summary Get all available levels
 */

export function useLoggingManagerLevels<
  TData = Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
  TError = ErrorType<unknown>,
>(options?: {
  query?: Partial<
    UseQueryOptions<
      Awaited<ReturnType<ReturnType<typeof useLoggingManagerLevelsHook>>>,
      TError,
      TData
    >
  >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey } {
  const queryOptions = useLoggingManagerLevelsQueryOptions(options);

  const query = useQuery(queryOptions) as UseQueryResult<TData, TError> & { queryKey: QueryKey };

  query.queryKey = queryOptions.queryKey;

  return query;
}
