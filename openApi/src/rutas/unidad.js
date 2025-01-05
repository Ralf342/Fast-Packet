const express = require('express');
const router = express.Router();

let unidades = []; 

// ws registrar unidad
router.post('/registrarUnidad', (req, res) => {
    const { vin, nii, modelo, marca, idTipoUnidad } = req.body;
    if (vin && nii && modelo && marca && idTipoUnidad) {
        const nuevaUnidad = { idUnidad: unidades.length + 1, vin, nii, modelo, marca, idTipoUnidad };
        unidades.push(nuevaUnidad);
        res.status(201).json({ mensaje: 'Unidad registrada exitosamente', unidad: nuevaUnidad });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

// ws editar unidad
router.put('/editarUnidad', (req, res) => {
    const { idUnidad, vin, nii, modelo, marca } = req.body;
    const unidad = unidades.find(u => u.idUnidad === idUnidad);
    if (unidad) {
        unidad.vin = vin || unidad.vin;
        unidad.nii = nii || unidad.nii;
        unidad.modelo = modelo || unidad.modelo;
        unidad.marca = marca || unidad.marca;
        res.json({ mensaje: 'Unidad actualizada', unidad });
    } else {
        res.status(404).json({ mensaje: 'Unidad no encontrada' });
    }
});

// ws baja unidad
router.delete('/bajaUnidad/:idUnidad', (req, res) => {
    const idUnidad = parseInt(req.params.idUnidad);
    unidades = unidades.filter(u => u.idUnidad !== idUnidad);
    res.json({ mensaje: 'Unidad eliminada' });
});

// ws buscar unidad por NII
router.get('/buscarUnidadNII/:nii', (req, res) => {
    const unidad = unidades.find(u => u.nii === req.params.nii);
    if (unidad) {
        res.json(unidad);
    } else {
        res.status(404).json({ mensaje: 'Unidad no encontrada' });
    }
});

// ws btener todas las unidades
router.get('/obtenerUnidad', (req, res) => {
    res.json(unidades);
});

module.exports = router;

