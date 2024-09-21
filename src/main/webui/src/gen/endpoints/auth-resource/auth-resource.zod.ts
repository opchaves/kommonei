/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import { z as zod } from 'zod';

export const postApiAuthLoginBodyEmailRegExp = new RegExp('\\S');
export const postApiAuthLoginBodyPasswordMin = 10;

export const postApiAuthLoginBodyPasswordMax = 64;

export const postApiAuthLoginBodyPasswordRegExp = new RegExp(
  '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$',
);

export const postApiAuthLoginBody = zod.object({
  email: zod.string().regex(postApiAuthLoginBodyEmailRegExp),
  password: zod
    .string()
    .min(postApiAuthLoginBodyPasswordMin)
    .max(postApiAuthLoginBodyPasswordMax)
    .regex(postApiAuthLoginBodyPasswordRegExp),
});

export const postApiAuthLoginResponse = zod.object({
  token: zod.string(),
});

export const postApiAuthRegisterBodyNameRegExp = new RegExp('\\S');
export const postApiAuthRegisterBodyEmailRegExp = new RegExp('\\S');
export const postApiAuthRegisterBodyPasswordMin = 10;

export const postApiAuthRegisterBodyPasswordMax = 64;

export const postApiAuthRegisterBodyPasswordRegExp = new RegExp(
  '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,}$',
);

export const postApiAuthRegisterBody = zod.object({
  name: zod.string().regex(postApiAuthRegisterBodyNameRegExp),
  email: zod.string().regex(postApiAuthRegisterBodyEmailRegExp),
  roles: zod.array(zod.enum(['USER', 'ADMIN'])).optional(),
  password: zod
    .string()
    .min(postApiAuthRegisterBodyPasswordMin)
    .max(postApiAuthRegisterBodyPasswordMax)
    .regex(postApiAuthRegisterBodyPasswordRegExp),
});
