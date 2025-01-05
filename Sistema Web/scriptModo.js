const modoActivo = localStorage.getItem('modo') || 'oscuro'; 
document.body.classList.add(modoActivo); 

const URL_WS = 'http://localhost:8084/WSFastPacket/api/paquete/consultarPaquetePorEnvio/';

//CONSUMO DE SERVICIOS WEB

/*async function obtenerPaquete() {
    const inputData = document.getElementById('inp-numeroGuia');
    const numeroGuia = inputData.value; // Obtén el valor del input
    const listaPagos = document.getElementById('lista-pagos'); // Contenedor para mostrar información

    listaPagos.innerHTML = '<p>Cargando información...</p>'; // Mensaje temporal

    try {
        const respuesta = await fetch(URL_WS + numeroGuia, { method: 'GET' });

        if (respuesta.ok) {
            const data = await respuesta.json();
            console.log(data); // Para depuración
            mostrarInformacion(listaPagos, data); // Muestra los datos en el contenedor
        } else {
            throw new Error(`Error en la petición: ${respuesta.status}`);
        }
    } catch (error) {
        console.error('Error en la petición:', error);
        listaPagos.innerHTML = '<p>Hubo un error al consultar la información.</p>';
    }
}*/

async function obtenerPaquete() {
    const inputData = document.getElementById('inp-numeroGuia');
    const numeroGuia = inputData.value.trim(); // Obtén el valor del input y elimina espacios en blanco

    if (!numeroGuia) {
        alert('Por favor, ingresa un número de guía válido.');
        return;
    }

    // Guarda el número de guía en localStorage
    localStorage.setItem('numeroGuia', numeroGuia);

    // Redirige a ruta.html
    window.location.href = 'ruta.html';
}


function mostrarInformacion(listaPagos, pagos){
    listaPagos.innerHTML='';
    pagos.forEach(pago => {
        const pagoElemento = document.createElement('div');
        pagoElemento.className='lista-elemento';
        pagoElemento.innerHTML=`
        <strong>Alto: </strong>${pago.alto} <br>
        <strong>Ancho: </strong>${pago.ancho} <br>
        <strong>descripcion: </strong>${pago.descripcion} <br>
        <strong>idPaquete: </strong>${pago.idPaquete}<br>
        <strong>Peso: </strong>${pago.peso} <br>
        <strong>Profundidad: </strong>${pago.profundidad}
        `;
        listaPagos.appendChild(pagoElemento);
    });
}


//fun pa cambiar modos
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

const botonModo = document.getElementById('btn-cambiar-modo');
if (botonModo) {
    botonModo.addEventListener('click', cambiarModo); 
}
