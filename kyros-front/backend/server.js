const express = require('express');
const server = express();

// Configurar headers para evitar cache
server.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-cache, no-store, must-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  next();
});

// Configuração do json-server
const jsonServer = require('json-server');
const router = jsonServer.router('backend/db.json'); // ou o caminho para o seu arquivo JSON
const middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(router);

const PORT = 3001;
server.listen(PORT, () => {
  console.log(`JSON Server is running on port ${PORT}`);
});