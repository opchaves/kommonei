/**
 * Generated by orval v7.0.1 🍺
 * Do not edit manually.
 * KomMonei Antd Monorepo
 * Quarkus monorepo demonstrating Panache Mongo REST server with AntD UI client
 * OpenAPI spec version: 1.0.0
 */

/**
 * Activity Response Object
 */
export interface ActivityResponse {
  /** Activity category */
  category: string;
  /** Activity creation date */
  createdAt: string;
  /** Activity description */
  description: string;
  /** Activity handling date */
  handledAt: string;
  /** Activity Object Id */
  id: string;
  /** Activity name */
  name: string;
  /** Activity payment status */
  paid: boolean;
  /** Activity price */
  price: number;
  /** Activity update date */
  updatedAt: string;
  /** User Object Id */
  userId: string;
}
