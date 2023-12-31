FROM node:14.20.0

WORKDIR /app

COPY . .

RUN npm install -g @angular/cli@9.1.15

RUN npm install

EXPOSE 4200

CMD ["npm", "start"]