FROM node:21 AS base

# Install build dependencies for native modules
LABEL authors="kevinliu"
WORKDIR /usr/app

FROM base AS install
COPY package.json ./
RUN npm install

FROM base AS source
COPY --from=install /usr/app/node_modules node_modules
COPY . .

FROM source as dev
EXPOSE 5173
CMD ["npm", "run", "dev", "--", "--host"]

FROM source AS build
RUN npm run build

FROM nginx:stable-alpine AS prod
COPY --from=build /usr/app/dist /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
