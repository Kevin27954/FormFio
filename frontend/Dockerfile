FROM node:21 AS build

# Install build dependencies for native modules
LABEL authors="kevinliu"
WORKDIR /usr/app

FROM build AS install
RUN mkdir -p /temp/prod
COPY package.json /temp/prod/
RUN cd /temp/prod && npm install

FROM build AS prerelease
COPY --from=install /temp/prod/node_modules node_modules
COPY . .
RUN npm run build

FROM nginx:stable-alpine AS production
COPY --from=prerelease /usr/app/dist /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]