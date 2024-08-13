/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import { z as zod } from 'zod';

/**
 * Check the health of the application
 * @summary An aggregated view of the Liveness, Readiness and Startup of this application
 */
export const microprofileHealthRootResponse = zod.object({
  data: zod.object({}).nullish(),
  name: zod.string().optional(),
  status: zod.enum(['DOWN', 'UP']).optional(),
});

/**
 * Check the liveness of the application
 * @summary The Liveness check of this application
 */
export const microprofileHealthLivenessResponse = zod.object({
  data: zod.object({}).nullish(),
  name: zod.string().optional(),
  status: zod.enum(['DOWN', 'UP']).optional(),
});

/**
 * Check the readiness of the application
 * @summary The Readiness check of this application
 */
export const microprofileHealthReadinessResponse = zod.object({
  data: zod.object({}).nullish(),
  name: zod.string().optional(),
  status: zod.enum(['DOWN', 'UP']).optional(),
});

/**
 * Check the startup of the application
 * @summary The Startup check of this application
 */
export const microprofileHealthStartupResponse = zod.object({
  data: zod.object({}).nullish(),
  name: zod.string().optional(),
  status: zod.enum(['DOWN', 'UP']).optional(),
});
