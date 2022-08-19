FROM node:18.2.0-alpine
WORKDIR /app
COPY package*.json ./
COPY . ./
RUN npm install -f
EXPOSE 3000
CMD ["npm", "run", "start"]
