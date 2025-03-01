/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import { useMutation } from '@tanstack/react-query';
import type {
  MutationFunction,
  UseMutationOptions,
  UseMutationResult,
} from '@tanstack/react-query';
import { useCallback } from 'react';
import type { AuthRequest, AuthResponse, UserRequest } from '../../models';
import { useAxiosMutator } from '../../../AxiosMutator';
import type { ErrorType } from '../../../AxiosMutator';

export const usePostApiAuthLoginHook = () => {
  const postApiAuthLogin = useAxiosMutator<AuthResponse>();

  return useCallback(
    (authRequest: AuthRequest) => {
      return postApiAuthLogin({
        url: `/api/auth/login`,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        data: authRequest,
      });
    },
    [postApiAuthLogin],
  );
};

export const usePostApiAuthLoginMutationOptions = <
  TError = ErrorType<unknown>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>,
    TError,
    { data: AuthRequest },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>,
  TError,
  { data: AuthRequest },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const postApiAuthLogin = usePostApiAuthLoginHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>,
    { data: AuthRequest }
  > = (props) => {
    const { data } = props ?? {};

    return postApiAuthLogin(data);
  };

  return { mutationFn, ...mutationOptions };
};

export type PostApiAuthLoginMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>
>;
export type PostApiAuthLoginMutationBody = AuthRequest;
export type PostApiAuthLoginMutationError = ErrorType<unknown>;

export const usePostApiAuthLogin = <TError = ErrorType<unknown>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>,
    TError,
    { data: AuthRequest },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthLoginHook>>>,
  TError,
  { data: AuthRequest },
  TContext
> => {
  const mutationOptions = usePostApiAuthLoginMutationOptions(options);

  return useMutation(mutationOptions);
};
export const usePostApiAuthRegisterHook = () => {
  const postApiAuthRegister = useAxiosMutator<unknown>();

  return useCallback(
    (userRequest: UserRequest) => {
      return postApiAuthRegister({
        url: `/api/auth/register`,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        data: userRequest,
      });
    },
    [postApiAuthRegister],
  );
};

export const usePostApiAuthRegisterMutationOptions = <
  TError = ErrorType<unknown>,
  TContext = unknown,
>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>,
    TError,
    { data: UserRequest },
    TContext
  >;
}): UseMutationOptions<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>,
  TError,
  { data: UserRequest },
  TContext
> => {
  const { mutation: mutationOptions } = options ?? {};

  const postApiAuthRegister = usePostApiAuthRegisterHook();

  const mutationFn: MutationFunction<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>,
    { data: UserRequest }
  > = (props) => {
    const { data } = props ?? {};

    return postApiAuthRegister(data);
  };

  return { mutationFn, ...mutationOptions };
};

export type PostApiAuthRegisterMutationResult = NonNullable<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>
>;
export type PostApiAuthRegisterMutationBody = UserRequest;
export type PostApiAuthRegisterMutationError = ErrorType<unknown>;

export const usePostApiAuthRegister = <TError = ErrorType<unknown>, TContext = unknown>(options?: {
  mutation?: UseMutationOptions<
    Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>,
    TError,
    { data: UserRequest },
    TContext
  >;
}): UseMutationResult<
  Awaited<ReturnType<ReturnType<typeof usePostApiAuthRegisterHook>>>,
  TError,
  { data: UserRequest },
  TContext
> => {
  const mutationOptions = usePostApiAuthRegisterMutationOptions(options);

  return useMutation(mutationOptions);
};
