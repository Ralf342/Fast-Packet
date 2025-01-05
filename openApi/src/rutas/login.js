const express = require('express');
const router = express.Router();

router.get('/probarConexion', (req, res) => {
    res.send(true); // Simula conexión exitosa
});

router.post('/colaborador', (req, res) => {
    const { noPersonal, contrasenia } = req.body;
    if (noPersonal && contrasenia) {
        res.json({ noPersonal, nombre: 'Juan Pérez', rol: 'Admin' });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

module.exports = router;
