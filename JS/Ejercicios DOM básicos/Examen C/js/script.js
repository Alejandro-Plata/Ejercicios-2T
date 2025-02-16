/* ================================== */
/* Ej.1 🎨 Paleta de Colores (+Alt)*/
/* ================================== */

//Seleccionamos el rectángulo
const rectangulo = document.querySelector(".paleta");

document.addEventListener("keydown", (e)=>{
e.preventDefault();
    console.log(e.key);

    if(e.key ==="Alt"){

        document.addEventListener("keydown", (e)=>{

            switch(e.key){

                case "r": rectangulo.style.backgroundColor = "red";
                    break;
                case "g": rectangulo.style.backgroundColor = "green";
                        break;
                case "b": rectangulo.style.backgroundColor = "blue";
                    break;
                case "y": rectangulo.style.backgroundColor = "yellow";
                    break;
                case "p": rectangulo.style.backgroundColor = "pink";
                    break;
                case "w": rectangulo.style.backgroundColor = "white";
                    break;

            }

        })
    }
    

});

/* ================================== */
/* Ej.2 🖼️ Carrusel de imágenes */
/* ================================== */

const inputCarrusel = document.querySelector("input");
const botonCarrusel = document.querySelector("button");
const imagenGaleria = document.querySelector("img[alt = 'Imagen Principal']");
let intervaloCarrusel;
let indice = 0;

botonCarrusel.addEventListener("click", ()=>{

    clearInterval(intervaloCarrusel);

    let arrayImagenes = ["img/img1.webp","img/img2.webp","img/img3.webp","img/img4.webp","img/img5.webp"]

    if(inputCarrusel.value < 1 || inputCarrusel.value > 10){
        return;
    }

    intervaloCarrusel = setInterval(()=>{
        imagenGaleria.setAttribute("src", arrayImagenes[indice++]);
        if(indice>4){
            indice = 0;
        }

    }, inputCarrusel.value*1000)


});

/* ================================== */
/* Ej.3 🎚️ Control de Volumen */
/* ================================== */

const controladorVol = document.querySelector(".volumen");
let contadorVol = 0;

controladorVol.addEventListener("wheel", (e)=>{

    e.preventDefault();

    if(e.deltaY<0){
        const elementoVol = document.createElement("div");
        elementoVol.classList.add("nivel");
        if(contadorVol>10){
            controladorVol = 0;
        } 
        elementoVol.textContent = contadorVol++;
        controladorVol.appendChild(elementoVol);
        
    } else if(e.deltaY>0){

        if(controladorVol.lastElementChild){
            if(contadorVol>0){
                contadorVol--;
            }
            controladorVol.removeChild(controladorVol.lastElementChild);
        }
    }

});

/* ================================== */
/* Ej.4: 🚦 Semáforo Interactivo */
/* ================================== */

const semaforo = document.querySelector(".semaforo");
const semVerde = document.querySelector(".verde");
const semAmarillo = document.querySelector(".amarillo");
const semRojo = document.querySelector(".rojo");

semVerde.addEventListener("click", (e)=>{
    
    e.stopPropagation();
    semVerde.classList.toggle("activa");
    semAmarillo.classList.remove("activa");
    semRojo.classList.remove("activa");

});

semAmarillo.addEventListener("click", (e)=>{

    e.stopPropagation();
    semAmarillo.classList.toggle("activa");
    semVerde.classList.remove("activa");
    semRojo.classList.remove("activa");

})


semRojo.addEventListener("click", (e)=>{
    
    e.stopPropagation();
    semRojo.classList.toggle("activa");
    semVerde.classList.remove("activa");
    semAmarillo.classList.remove("activa");

});

semaforo.addEventListener("contextmenu", (e)=>{

    e.preventDefault();
    semRojo.classList.remove("activa");
    semVerde.classList.remove("activa");
    semAmarillo.classList.remove("activa");
});

semaforo.addEventListener("click", (e)=>{

    semRojo.classList.add("activa");
    semVerde.classList.add("activa");
    semAmarillo.classList.add("activa");

});



