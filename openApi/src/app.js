const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const swaggerUi = require('swagger-ui-express');
const yaml = require('yamljs');

const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.json());

const openapiSpec = yaml.load('./openapi.yaml');
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(openapiSpec));

//rutas
app.use('/login', require('./rutas/login'));
app.use('/envio', require('./rutas/envio'));
app.use('/colaborador', require('./rutas/colaborador'));
app.use('/unidad', require('./rutas/unidad'));
app.use('/paquete', require('./rutas/paquete'));
app.use('/tipo', require('./rutas/tipo'));
app.use('/cliente', require('./rutas/cliente'));

app.listen(port, () => {
    console.log(`Servidor escuchando en http://localhost:${port}`);
    console.log(`Documentación Swagger disponible en http://localhost:${port}/api-docs`);
});
