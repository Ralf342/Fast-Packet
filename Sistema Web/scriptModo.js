const modoActivo = localStorage.getItem('modo') || 'oscuro'; 
document.body.classList.add(modoActivo); 

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
