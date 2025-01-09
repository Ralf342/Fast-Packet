const modoActivo = localStorage.getItem('modo') || 'oscuro';
document.body.classList.add(modoActivo);

const URL_WS = 'http://localhost:8084/WSFastPacket/api/envio/buscarEnviosInfo/';

// Al cargar la página, ejecuta la lógica
document.addEventListener('DOMContentLoaded', async () => {
    const numeroGuia = localStorage.getItem('numeroGuia'); // Recupera el número de guía almacenado

    if (!numeroGuia) {
        alert('No se encontró un número de guía. Regresa a la página principal.');
        window.location.href = 'index.html';
        return;
    }

    const listaDestino = document.getElementById('destino');
    listaDestino.innerHTML = '<p>Cargando información...</p>'; // Mensaje temporal

    const listaOrigen = document.getElementById('origen');
    listaOrigen.innerHTML = '<p>Cargando información...</p>'; // Mensaje temporal

    const listaRuta = document.getElementById('ruta');
    listaRuta.innerHTML = '<p>Cargando información...</p>'; // Mensaje temporal

    try {
        const respuesta = await fetch(URL_WS + numeroGuia, { method: 'GET' });

        if (respuesta.ok) {
            const data = await respuesta.json();
            console.log(data); // Para depuración
            mostrarInformacionDestino(listaDestino, data); // Muestra los datos en el contenedor
            mostrarInformacionOrigen(listaOrigen, data);
            mostrarInformacionRuta(listaRuta, data);
        } else {
            throw new Error(`Error en la petición: ${respuesta.status}`);
        }
    } catch (error) {
        console.error('Error en la petición:', error);
        listaDestino.innerHTML = '<p>Hubo un error al consultar la información del destino.</p>';
        listaOrigen.innerHTML = '<p>Hubo un error al consultar la información del origen.</p>';
        listaRuta.innerHTML = '<p>Hubo un error al consultar la información de la ruta.</p>';
    }
});

// Función para mostrar la información en la página
function mostrarInformacionDestino(listaPagos, pagos) {
    listaPagos.innerHTML = ''; // Limpia el contenido previo
    pagos.forEach((pago) => {
        const pagoElemento = document.createElement('div');
        pagoElemento.className = 'lista-elemento';
        pagoElemento.innerHTML = `
            <h3>Destino</h3><br>
            <strong>Calle: </strong>${pago.calle} <br>
            <strong>Ciudad: </strong>${pago.ciudad} <br>
            <strong>Codigo Postal: </strong>${pago.codigoPostal} <br>
            <strong>Colonia: </strong>${pago.colonia}<br>
            <strong>Estado: </strong>${pago.estado}<br>
            <img src="recursos/unidad.png" alt="Marcador de destino" class="unidad">
        `;
        listaPagos.appendChild(pagoElemento);
    });
}

function mostrarInformacionOrigen(listaPagos, pagos) {
    listaPagos.innerHTML = ''; // Limpia el contenido previo
    pagos.forEach((pago) => {
        const pagoElemento = document.createElement('div');
        pagoElemento.className = 'lista-elemento';
        pagoElemento.innerHTML = `
            <h3>Origen</h3><br>
            <strong>Calle: </strong>${pago.calleOrigen} <br>
            <strong>Ciudad: </strong>${pago.ciudadOrigen} <br>
            <strong>Codigo Postal: </strong>${pago.codigoPostal} <br>
            <strong>Colonia: </strong>${pago.coloniaOrigen}<br>
            <strong>Estado: </strong>${pago.estadoOrigen}<br>
            <img src="recursos/envio.png" alt="Marcador de origen" class="envio">
        `;
        listaPagos.appendChild(pagoElemento);
    });
}

function mostrarInformacionRuta(listaPagos, pagos) {
    listaPagos.innerHTML = ''; // Limpia el contenido previo
    pagos.forEach((pago) => {
        const pagoElemento = document.createElement('div');
        pagoElemento.className = 'lista-elemento';
        pagoElemento.innerHTML = `
            <strong>Estado de entrega: </strong>${pago.estatus} <br>
            <strong>Fecha de la actualizacion: </strong>${pago.fechaModificacion} <br>
        `;
        listaPagos.appendChild(pagoElemento);
    });
}

// Función para cambiar modos (oscuro/claro)
function cambiarModo() {
    const esModoClaro = document.body.classList.contains('modo-claro');

    if (esModoClaro) {
        document.body.classList.remove('modo-claro');
        localStorage.setItem('modo', 'oscuro');
    } else {
        document.body.classList.add('modo-claro');
        localStorage.setItem('modo', 'claro');
    }
}

async function regresarBuscador() {
    window.location.href = 'index.html';
} 

const botonModo = document.getElementById('btn-cambiar-modo');
if (botonModo) {
    botonModo.addEventListener('click', cambiarModo);
}
