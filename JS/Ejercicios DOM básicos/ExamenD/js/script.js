/* ====================================== */
/* Ej.1 - Estrellas Fugaces               */
/* ====================================== */

let intervaloEstrella;
let estrellaMov;
let activo = false;

document.addEventListener("contextmenu", (e) => {
    e.preventDefault();
    if (activo) {
        document.removeEventListener("mousemove", estrellaMov);
        activo = false;
    } else {
        activo = true;
        estrellaMov = (e) => {
            e.preventDefault();
            console.log(e);
            const estrella = document.createElement("div");
            estrella.classList.add("estrella");
            estrella.textContent = "⭐";
            estrella.style.top = e.pageY + "px";
            estrella.style.left = e.pageX + "px";
            document.body.appendChild(estrella);

            setTimeout(() => {
                estrella.remove();
            }, 500);
        };
        document.addEventListener("mousemove", estrellaMov);
    }
});

/* ====================================== */
/* Ej.2 - Control de Barras              */
/* ====================================== */
    
//Para seleccionar la barra, tenemos que cambiar la clase.
//Hacemos un array de los rectángulos para facilitar la tarea:

const arrayBarras = document.querySelectorAll(".barra");
const contenedorBarras = document.querySelector(".barras");
let indice = 0; //Indice del array de Barras

//Escuchamos el evento de la rueda

contenedorBarras.addEventListener("wheel", (e) => {
    e.preventDefault();

    if (e.deltaY < 0) {
        if (indice < arrayBarras.length - 1) {
            arrayBarras[indice].classList.remove("seleccionada");
            indice++;
            arrayBarras[indice].classList.add("seleccionada");
        }
    } else if (e.deltaY > 0) {
        if (indice > 0) {
            arrayBarras[indice].classList.remove("seleccionada");
            indice--;
            arrayBarras[indice].classList.add("seleccionada");
        }
    }
});

contenedorBarras.addEventListener("click", () => {
    let altura = parseInt(arrayBarras[indice].style.height) || 50;
    if (altura < 300) {
        arrayBarras[indice].style.height = (altura + 10) + "px";
    }
});

contenedorBarras.addEventListener("contextmenu", (e) => {
    e.preventDefault();
    let altura = parseInt(arrayBarras[indice].style.height) || 50;
    if (altura > 50) {
        arrayBarras[indice].style.height = (altura - 10) + "px";
    }
});

/* ====================================== */
/* Ej.3 - Intermitentes           */
/* ====================================== */
    
const coche = document.getElementById("coche");
const intermitenteDcho = document.querySelector(".derecho");
const intermitenteIzq = document.querySelector(".izquierdo");
let intervaloParpadeoIzq;
let intervaloParpadeoDcho;
let intermitenteActivo = false;

document.addEventListener("keydown", (e) => {
    if (e.key === "ArrowLeft") {
        if (intervaloParpadeoDcho) {
            clearInterval(intervaloParpadeoDcho);
            intermitenteDcho.classList.remove("activo");
        }
        if (!intermitenteActivo || intervaloParpadeoIzq) {
            intermitenteActivo = true;
            intervaloParpadeoIzq = setInterval(() => {
                intermitenteIzq.classList.toggle("activo");
            }, 500);
        } else {
            clearInterval(intervaloParpadeoIzq);
            intermitenteIzq.classList.remove("activo");
            intermitenteActivo = false;
        }
    } else if (e.key === "ArrowRight") {
        if (intervaloParpadeoIzq) {
            clearInterval(intervaloParpadeoIzq);
            intermitenteIzq.classList.remove("activo");
        }
        if (!intermitenteActivo || intervaloParpadeoDcho) {
            intermitenteActivo = true;
            intervaloParpadeoDcho = setInterval(() => {
                intermitenteDcho.classList.toggle("activo");
            }, 500);
        } else {
            clearInterval(intervaloParpadeoDcho);
            intermitenteDcho.classList.remove("activo");
            intermitenteActivo = false;
        }
    }
});

/* ====================================== */
/* Ej.4 - Raton           */
/* ====================================== */

function moverRaton(){

    raton.style.top = Math.floor(Math.random() * (ratonera.clientHeight - 40)) + "px";
    raton.style.left = Math.floor(Math.random() * (ratonera.clientWidth - 40)) + "px";

}

const ratonera = document.querySelector(".ratonera");
const raton = document.querySelector(".raton");
let puntuacion = document.querySelector("h2 span");
let puntuacionMaxima = document.querySelectorAll("h2 span")[1];
let puntuacionMax = 0;
let puntuacionAcumulada = 0;

raton.addEventListener("click", (e)=>{

    e.stopImmediatePropagation();

    puntuacion.textContent = ++puntuacionAcumulada;
    
    if(puntuacionAcumulada>=puntuacionMax){
        puntuacionMaxima.textContent = puntuacionAcumulada;
        puntuacionMax++;
    }
    moverRaton();
})

ratonera.addEventListener("click", ()=>{

    puntuacion.textContent = 0;
    puntuacionAcumulada = 0;
    moverRaton();
    alert("Has fallado!");

})
