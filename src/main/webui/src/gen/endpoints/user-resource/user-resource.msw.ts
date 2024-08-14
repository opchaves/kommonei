/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import { faker } from '@faker-js/faker';
import { HttpResponse, delay, http } from 'msw';
import type { UserResponse } from '../../models';

export const getGetApiUsersIdResponseMock = (
  overrideResponse: Partial<UserResponse> = {},
): UserResponse => ({
  createdAt: faker.word.sample(),
  email: faker.word.sample(),
  id: faker.word.sample(),
  name: faker.word.sample(),
  ...overrideResponse,
});

export const getPostApiUsersLoginMockHandler = (
  overrideResponse?:
    | unknown
    | ((info: Parameters<Parameters<typeof http.post>[1]>[0]) => Promise<unknown> | unknown),
) => {
  return http.post('*/api/users/login', async (info) => {
    await delay(1000);
    if (typeof overrideResponse === 'function') {
      await overrideResponse(info);
    }
    return new HttpResponse(null, { status: 200 });
  });
};

export const getPostApiUsersRegisterMockHandler = (
  overrideResponse?:
    | unknown
    | ((info: Parameters<Parameters<typeof http.post>[1]>[0]) => Promise<unknown> | unknown),
) => {
  return http.post('*/api/users/register', async (info) => {
    await delay(1000);
    if (typeof overrideResponse === 'function') {
      await overrideResponse(info);
    }
    return new HttpResponse(null, { status: 200 });
  });
};

export const getGetApiUsersIdMockHandler = (
  overrideResponse?:
    | UserResponse
    | ((
        info: Parameters<Parameters<typeof http.get>[1]>[0],
      ) => Promise<UserResponse> | UserResponse),
) => {
  return http.get('*/api/users/:id', async (info) => {
    await delay(1000);

    return new HttpResponse(
      JSON.stringify(
        overrideResponse !== undefined
          ? typeof overrideResponse === 'function'
            ? await overrideResponse(info)
            : overrideResponse
          : getGetApiUsersIdResponseMock(),
      ),
      { status: 200, headers: { 'Content-Type': 'application/json' } },
    );
  });
};
export const getUserResourceMock = () => [
  getPostApiUsersLoginMockHandler(),
  getPostApiUsersRegisterMockHandler(),
  getGetApiUsersIdMockHandler(),
];
