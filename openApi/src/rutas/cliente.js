const express = require('express');
const router = express.Router();

let clientes = []; 

// ws egistrar cliente
router.post('/registrarCliente', (req, res) => {
    const { correo, nombre, telefono } = req.body;
    if (correo && nombre && telefono) {
        const nuevoCliente = { idCliente: clientes.length + 1, correo, nombre, telefono };
        clientes.push(nuevoCliente);
        res.status(201).json({ mensaje: 'Cliente registrado exitosamente', cliente: nuevoCliente });
    } else {
        res.status(400).json({ mensaje: 'Datos faltantes o incorrectos' });
    }
});

// ws ditar cliente
router.put('/editarCliente', (req, res) => {
    const { idCliente, correo, nombre, telefono } = req.body;
    const cliente = clientes.find(c => c.idCliente === idCliente);
    if (cliente) {
        cliente.correo = correo || cliente.correo;
        cliente.nombre = nombre || cliente.nombre;
        cliente.telefono = telefono || cliente.telefono;
        res.json({ mensaje: 'Cliente actualizado', cliente });
    } else {
        res.status(404).json({ mensaje: 'Cliente no encontrado' });
    }
});

// ws eliminar cliente
router.delete('/eliminarCliente/:idCliente', (req, res) => {
    const idCliente = parseInt(req.params.idCliente);
    clientes = clientes.filter(c => c.idCliente !== idCliente);
    res.json({ mensaje: 'Cliente eliminado' });
});

// ws buscar cliente por correo
router.get('/buscarClientePorCorreo/:correo', (req, res) => {
    const cliente = clientes.find(c => c.correo === req.params.correo);
    if (cliente) {
        res.json(cliente);
    } else {
        res.status(404).json({ mensaje: 'Cliente no encontrado' });
    }
});

// ws btener todos los clientes
router.get('/obtenerClientes', (req, res) => {
    res.json(clientes);
});

module.exports = router;
