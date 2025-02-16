/* ================================== */
/* Ej.1 - Botón de cambio de tema     */
/* ================================== */

//Seleccionamos el boton
const botonModo = document.getElementById("modoBoton");
//Seleccionamos el icono
const icono = document.querySelector("button span");

botonModo.addEventListener("click", ()=>{

    document.body.classList.toggle("oscuro");

    if(document.body.classList.contains("oscuro")){
        icono.textContent = "light_mode";
    } else{
        icono.textContent = "dark_mode";
    }

});

/* ================================== */
/* Ej.2 - Galería de imágenes    */
/* ================================== */

//Seleccionamos las imágenes y la imagen principal
const imagenPrincipal = document.querySelector("img[alt = 'Imagen Principal']");
const imagenes = document.querySelectorAll(".miniatura");
const arrayImagenes = ["img/img1.webp", "img/img2.webp", "img/img3.webp", "img/img4.webp", "img/img5.webp"];
imagenes[0].addEventListener("click", ()=>{

    imagenP = imagenPrincipal.getAttribute("src");//Variable temporal
    imagenPrincipal.setAttribute("src",arrayImagenes[0]);
    arrayImagenes[0] = imagenP;
    imagenes[0].setAttribute("src", imagenP);

});

imagenes[1].addEventListener("click", ()=>{

    imagenP = imagenPrincipal.getAttribute("src");//Variable temporal
    imagenPrincipal.setAttribute("src", arrayImagenes[1]);
    arrayImagenes[1] = imagenP;
    imagenes[1].setAttribute("src", imagenP);

});

imagenes[2].addEventListener("click", ()=>{

    imagenP = imagenPrincipal.getAttribute("src");//Variable temporal
    imagenPrincipal.setAttribute("src", arrayImagenes[2]);
    arrayImagenes[2] = imagenP;
    imagenes[2].setAttribute("src", imagenP);

});

imagenes[3].addEventListener("click", ()=>{

    imagenP = imagenPrincipal.getAttribute("src");//Variable temporal
    imagenPrincipal.setAttribute("src", arrayImagenes[3]);
    arrayImagenes[3] = imagenP;
    imagenes[3].setAttribute("src", imagenP);

});

imagenes[4].addEventListener("click", ()=>{

    imagenP = imagenPrincipal.getAttribute("src");//Variable temporal
    imagenPrincipal.setAttribute("src", arrayImagenes[4]);
    arrayImagenes[4] = imagenP;
    imagenes[4].setAttribute("src", imagenP);

});

/* ================================== */
/* Ej.3 - ⏰ Temporizador    */
/* ================================== */

//Seleccionamos los elementos
const input = document.querySelector("input");
const botonTemp = document.querySelector(".conjunto button");
const temporizador = document.querySelector(".tempo");
let intervaloTemporizador;

botonTemp.addEventListener("click", ()=>{

    if(input.value > 10 || input.value < 0){
        return
    }

    for(let i = 0; i<input.value; i++){
        const cuadradoTemp = document.createElement("div");
        cuadradoTemp.classList.add("cuadrado");
        cuadradoTemp.textContent = i;
        temporizador.appendChild(cuadradoTemp);
    }

    intervaloTemporizador = setInterval(()=>{
        if(!temporizador.lastElementChild){
            clearInterval(intervaloTemporizador)
            return;
        }
        temporizador.removeChild(temporizador.lastElementChild);
    }, 1000);   


})

/* ================================== */
/* Ej.3 - Contador de vocales 🔤 */
/* ================================== */

const inputVocales = document.querySelectorAll("input")[1];
const contadorA = document.getElementById("contadorA");
const contadorE = document.getElementById("contadorE");
const contadorI = document.getElementById("contadorI");
const contadorO = document.getElementById("contadorO");
const contadorU = document.getElementById("contadorU");
const tablaVocales = document.getElementById("tablaVocales");
let contadorLetraA = 0;
let contadorLetraE = 0;
let contadorLetraI = 0;
let contadorLetraO = 0;
let contadorLetraU = 0;

inputVocales.addEventListener("keydown", (e)=>{

    switch(e.key.toUpperCase()){
        case "A": contadorA.textContent = ++contadorLetraA;
            break;
        case "E": contadorE.textContent = ++contadorLetraE;
            break;
        case "I": contadorI.textContent = ++contadorLetraI;
            break;
        case "O": contadorO.textContent = ++contadorLetraO;
            break;
        case "U": contadorU.textContent = ++contadorLetraU;
            break;
    }

});

tablaVocales.addEventListener("contextmenu", (e)=>{
    
    e.preventDefault();

    contadorLetraA = 0;
    contadorLetraE = 0;
    contadorLetraI = 0;
    contadorLetraO = 0;
    contadorLetraU = 0;

        contadorA.textContent = contadorLetraA;
           
        contadorE.textContent = contadorLetraE;
            
        contadorI.textContent = contadorLetraI;
           
        contadorO.textContent = contadorLetraO;
            
        contadorU.textContent = contadorLetraU;
           
        inputVocales.value = null;
})
