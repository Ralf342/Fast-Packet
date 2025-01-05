const express = require('express');
const router = express.Router();

let roles = [
    { idRol: 1, nombre: 'Admin' },
    { idRol: 2, nombre: 'Repartidor' },
]; 

let tiposVehiculos = [
    { idTipoUnidad: 1, tipo: 'Camión' },
    { idTipoUnidad: 2, tipo: 'Furgoneta' },
]; 

// ws obtener roles
router.get('/obtener-roles', (req, res) => {
    res.json(roles);
});

// ws obtener tipos de vehículos
router.get('/obtener-tipos-vehiculos', (req, res) => {
    res.json(tiposVehiculos);
});

module.exports = router;
