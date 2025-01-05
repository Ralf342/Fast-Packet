const express = require('express');
const router = express.Router();

let paquetes = []; // Mock de paquetes

// ws registrar paquete
router.post('/registrarPaquete', (req, res) => {
    const { idPaquete, alto, ancho, profundidad, descripcion, numeroDeGuia } = req.body;
    if (alto && ancho && profundidad && descripcion) {
        const nuevoPaquete = { idPaquete: paquetes.length + 1, alto, ancho, profundidad, descripcion, numeroDeGuia };
        paquetes.push(nuevoPaquete);
        res.status(201).json({ mensaje: 'Paquete registrado exitosamente', paquete: nuevoPaquete });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

// ws actualizar paquete
router.put('/actualizarPaquete', (req, res) => {
    const { idPaquete, alto, ancho, profundidad, descripcion } = req.body;
    const paquete = paquetes.find(p => p.idPaquete === idPaquete);
    if (paquete) {
        paquete.alto = alto || paquete.alto;
        paquete.ancho = ancho || paquete.ancho;
        paquete.profundidad = profundidad || paquete.profundidad;
        paquete.descripcion = descripcion || paquete.descripcion;
        res.json({ mensaje: 'Paquete actualizado', paquete });
    } else {
        res.status(404).json({ mensaje: 'Paquete no encontrado' });
    }
});

// ws consultar paquete por envío
router.get('/consultarPaquete/:numeroDeGuia', (req, res) => {
    const paquetesEnvio = paquetes.filter(p => p.numeroDeGuia === parseInt(req.params.numeroDeGuia));
    if (paquetesEnvio.length > 0) {
        res.json(paquetesEnvio);
    } else {
        res.status(404).json({ mensaje: 'No se encontraron paquetes para este envío' });
    }
});

// ws eliminar paquete
router.delete('/eliminarPaquete/:idPaquete', (req, res) => {
    const idPaquete = parseInt(req.params.idPaquete);
    paquetes = paquetes.filter(p => p.idPaquete !== idPaquete);
    res.json({ mensaje: 'Paquete eliminado' });
});

module.exports = router;
