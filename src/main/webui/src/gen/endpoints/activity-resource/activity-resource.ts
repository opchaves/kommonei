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
import type { ActivityRequest } from '../../models';
import { useAxiosMutator } from '../../../AxiosMutator';
import type { ErrorType } from '../../../AxiosMutator';

export const useGetApiActivitiesHook = () => {
  const getApiActivities = useAxiosMutator<unknown>();

  return useCallback(
    (signal?: AbortSignal) => {
      return getApiActivities({ url: `/api/activities`, method: 'GET', signal });
    },
    [getApiActivities],
  );
};

export const getGetApiActivitiesQueryKey = () => {
  return [`/api/activities`] as const;
};

export const useGetApiActivitiesQueryOptions = <
  TData = Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
  TError = ErrorType<void>,
>(options?: {
  query?: Partial<
    UseQueryOptions<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>, TError, TData>
  >;
}) => {
  const { query: queryOptions } = options ?? {};

  const queryKey = queryOptions?.queryKey ?? getGetApiActivitiesQueryKey();

  const getApiActivities = useGetApiActivitiesHook();

  const queryFn: QueryFunction<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>> = ({
    signal,
  }) => getApiActivities(signal);

  return { queryKey, queryFn, ...queryOptions } as UseQueryOptions<
    Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
    TError,
    TData
  > & { queryKey: QueryKey };
};

export type GetApiActivitiesQueryResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>
>;
export type GetApiActivitiesQueryError = ErrorType<void>;

export function useGetApiActivities<
  TData = Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
  TError = ErrorType<void>,
>(options: {
  query: Partial<
    UseQueryOptions<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>, TError, TData>
  > &
    Pick<
      DefinedInitialDataOptions<
        Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
        TError,
        TData
      >,
      'initialData'
    >;
}): DefinedUseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useGetApiActivities<
  TData = Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
  TError = ErrorType<void>,
>(options?: {
  query?: Partial<
    UseQueryOptions<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>, TError, TData>
  > &
    Pick<
      UndefinedInitialDataOptions<
        Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
        TError,
        TData
      >,
      'initialData'
    >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey };
export function useGetApiActivities<
  TData = Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
  TError = ErrorType<void>,
>(options?: {
  query?: Partial<
    UseQueryOptions<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>, TError, TData>
  >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey };

export function useGetApiActivities<
  TData = Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>,
  TError = ErrorType<void>,
>(options?: {
  query?: Partial<
    UseQueryOptions<Awaited<ReturnType<ReturnType<typeof useGetApiActivitiesHook>>>, TError, TData>
  >;
}): UseQueryResult<TData, TError> & { queryKey: QueryKey } {
  const queryOptions = useGetApiActivitiesQueryOptions(options);

  const query = useQuery(queryOptions) as UseQueryResult<TData, TError> & { queryKey: QueryKey };

  query.queryKey = queryOptions.queryKey;

  return query;
}

export const usePostApiActivitiesHook = () => {
  const postApiActivities = useAxiosMutator<unknown>();

  return useCallback(
    (activityRequest: ActivityRequest) => {
      return postApiActivities({
        url: `/api/activities`,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        data: activityRequest,
      });
    },
    [postApiActivities],
  );
};

export const usePostApiActivitiesMutationOptions = <
  TError = ErrorType<void>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>,
    TError,
    { data: ActivityRequest },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>,
  TError,
  { data: ActivityRequest },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const postApiActivities = usePostApiActivitiesHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>,
    { data: ActivityRequest }
  > = (props) => {
    const { data } = props ?? {};

    return postApiActivities(data);
  };

  return { mutationFn, ...mutationOptions };
};

export type PostApiActivitiesMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>
>;
export type PostApiActivitiesMutationBody = ActivityRequest;
export type PostApiActivitiesMutationError = ErrorType<void>;

export const usePostApiActivities = <TError = ErrorType<void>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>,
    TError,
    { data: ActivityRequest },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof usePostApiActivitiesHook>>>,
  TError,
  { data: ActivityRequest },
  TContext
> => {
  const mutationOptions = usePostApiActivitiesMutationOptions(options);

  return useMutation(mutationOptions);
};
export const usePutApiActivitiesIdHook = () => {
  const putApiActivitiesId = useAxiosMutator<unknown>();

  return useCallback(
    (id: string, activityRequest: ActivityRequest) => {
      return putApiActivitiesId({
        url: `/api/activities/${id}`,
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        data: activityRequest,
      });
    },
    [putApiActivitiesId],
  );
};

export const usePutApiActivitiesIdMutationOptions = <
  TError = ErrorType<void>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>,
    TError,
    { id: string; data: ActivityRequest },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>,
  TError,
  { id: string; data: ActivityRequest },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const putApiActivitiesId = usePutApiActivitiesIdHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>,
    { id: string; data: ActivityRequest }
  > = (props) => {
    const { id, data } = props ?? {};

    return putApiActivitiesId(id, data);
  };

  return { mutationFn, ...mutationOptions };
};

export type PutApiActivitiesIdMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>
>;
export type PutApiActivitiesIdMutationBody = ActivityRequest;
export type PutApiActivitiesIdMutationError = ErrorType<void>;

export const usePutApiActivitiesId = <TError = ErrorType<void>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>,
    TError,
    { id: string; data: ActivityRequest },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof usePutApiActivitiesIdHook>>>,
  TError,
  { id: string; data: ActivityRequest },
  TContext
> => {
  const mutationOptions = usePutApiActivitiesIdMutationOptions(options);

  return useMutation(mutationOptions);
};
export const useDeleteApiActivitiesIdHook = () => {
  const deleteApiActivitiesId = useAxiosMutator<unknown>();

  return useCallback(
    (id: string) => {
      return deleteApiActivitiesId({ url: `/api/activities/${id}`, method: 'DELETE' });
    },
    [deleteApiActivitiesId],
  );
};

export const useDeleteApiActivitiesIdMutationOptions = <
  TError = ErrorType<void>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>,
    TError,
    { id: string },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>,
  TError,
  { id: string },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const deleteApiActivitiesId = useDeleteApiActivitiesIdHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>,
    { id: string }
  > = (props) => {
    const { id } = props ?? {};

    return deleteApiActivitiesId(id);
  };

  return { mutationFn, ...mutationOptions };
};

export type DeleteApiActivitiesIdMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>
>;

export type DeleteApiActivitiesIdMutationError = ErrorType<void>;

export const useDeleteApiActivitiesId = <TError = ErrorType<void>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>,
    TError,
    { id: string },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof useDeleteApiActivitiesIdHook>>>,
  TError,
  { id: string },
  TContext
> => {
  const mutationOptions = useDeleteApiActivitiesIdMutationOptions(options);

  return useMutation(mutationOptions);
};
