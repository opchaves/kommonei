import { defineConfig } from "orval";

export default defineConfig({
  kommonei: {
    input: {
      target: "./openapi.yaml",
    },
    output: {
      mode: "tags-split",
      target: "src/gen/endpoints",
      schemas: "src/gen/models",
      client: "react-query",
      mock: true,
      clean: true,
      prettier: false,
      override: {
        useDates: true,
        mutator: {
          path: "src/AxiosMutator.ts",
          name: "useAxiosMutator",
        },
        query: {
          useQuery: true,
        },
      },
    },
  },
  kommoneiZod: {
    input: {
      target: "./openapi.yaml",
    },
    output: {
      mode: "tags-split",
      client: "zod",
      target: "src/gen/endpoints",
      fileExtension: ".zod.ts",
      prettier: false,
    },
  },
});
