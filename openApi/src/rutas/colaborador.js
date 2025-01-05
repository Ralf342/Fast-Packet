const express = require('express');
const router = express.Router();

let colaboradores = []; 

// ws registrar colaborador
router.post('/registro', (req, res) => {
    const { noPersonal, contrasenia, nombre, rol } = req.body;
    if (noPersonal && contrasenia) {
        const nuevoColaborador = { idColaborador: colaboradores.length + 1, noPersonal, contrasenia, nombre, rol };
        colaboradores.push(nuevoColaborador);
        res.status(201).json({ mensaje: 'Colaborador registrado exitosamente', colaborador: nuevoColaborador });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

// ws eliminar colaborador
router.delete('/eliminarColaborador/:idColaborador', (req, res) => {
    const idColaborador = parseInt(req.params.idColaborador);
    colaboradores = colaboradores.filter(c => c.idColaborador !== idColaborador);
    res.json({ mensaje: 'Colaborador eliminado' });
});

// ws editar colaborador
router.put('/editarColaborador', (req, res) => {
    const { idColaborador, noPersonal, nombre, rol } = req.body;
    const colaborador = colaboradores.find(c => c.idColaborador === idColaborador);
    if (colaborador) {
        colaborador.noPersonal = noPersonal || colaborador.noPersonal;
        colaborador.nombre = nombre || colaborador.nombre;
        colaborador.rol = rol || colaborador.rol;
        res.json({ mensaje: 'Colaborador actualizado', colaborador });
    } else {
        res.status(404).json({ mensaje: 'Colaborador no encontrado' });
    }
});

// ws obtener todos los colaboradores
router.get('/obtenerColaboradores', (req, res) => {
    res.json(colaboradores);
});

module.exports = router;
