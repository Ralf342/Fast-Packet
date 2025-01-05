const express = require('express');
const router = express.Router();

let envios = []; // Mock de envíos

// ws registrar envío
router.post('/registrarEnvio', (req, res) => {
    const { numeroDeGuia, costo, destino, ciudad, estado, calle, colonia, numero, codigoPostal, idCliente, estatus } = req.body;
    if (numeroDeGuia && costo && destino && ciudad && estado && calle && colonia && numero && codigoPostal && idCliente && estatus) {
        const nuevoEnvio = { numeroDeGuia, costo, destino, ciudad, estado, calle, colonia, numero, codigoPostal, idCliente, estatus };
        envios.push(nuevoEnvio);
        res.status(201).json({ mensaje: 'Envío registrado exitosamente', envio: nuevoEnvio });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

// ws actualizar datos del envío
router.put('/actualizarEnvio', (req, res) => {
    const { numeroDeGuia, costo, destino, estatus } = req.body;
    const envio = envios.find(e => e.numeroDeGuia === numeroDeGuia);
    if (envio) {
        envio.costo = costo || envio.costo;
        envio.destino = destino || envio.destino;
        envio.estatus = estatus || envio.estatus;
        res.json({ mensaje: 'Envío actualizado', envio });
    } else {
        res.status(404).json({ mensaje: 'Envío no encontrado' });
    }
});

// ws consultar envío por número de guía
router.get('/consultarEnvio/:numeroDeGuia', (req, res) => {
    const envio = envios.find(e => e.numeroDeGuia === parseInt(req.params.numeroDeGuia));
    if (envio) {
        res.json(envio);
    } else {
        res.status(404).json({ mensaje: 'Envío no encontrado' });
    }
});

// ws actualizar estatus del envío
router.put('/actualizarEstatus', (req, res) => {
    const { numeroDeGuia, estatus } = req.body;
    const envio = envios.find(e => e.numeroDeGuia === numeroDeGuia);
    if (envio) {
        envio.estatus = estatus || envio.estatus;
        res.json({ mensaje: 'Estatus del envío actualizado', envio });
    } else {
        res.status(404).json({ mensaje: 'Envío no encontrado' });
    }
});

module.exports = router;
