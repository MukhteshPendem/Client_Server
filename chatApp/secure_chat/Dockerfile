# syntax=docker/dockerfile:1

FROM node:18-alpine
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["java", "/src/main/java/com/appviewx/securechat/server/ChatServer.java"]
CMD ["java", "/src/main/java/com/appviewx/securechat/client/Client1.java"]
EXPOSE 3000
