FROM node:14

WORKDIR /app

COPY . .

RUN npm install -g @angular/cli

RUN npm install

RUN npm run build

EXPOSE 4200

CMD ["npm", "start"]