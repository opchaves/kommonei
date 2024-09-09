/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */
import type { ActivityType } from './activityType';

/**
 * Activity input data
 */
export interface ActivityDTO {
  /** @pattern \S */
  category: string;
  createdAt?: string;
  description?: string;
  handledAt?: string;
  /** Activity Object Id */
  id?: string;
  /** @pattern \S */
  name: string;
  paid?: boolean;
  /** @minimum 0.01 */
  price?: number;
  type: ActivityType;
  updatedAt?: string;
  /** User Object Id */
  userId?: string;
}
