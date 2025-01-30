var data = [
    {
        img: "css/imagenes/1.jpg", // Imagen representativa del servicio de plomería
        country: "Quito", // País donde se ofrece el servicio
        place: "Plomero", // Ciudad donde se ofrece el servicio
        describe: "Ofrecemos servicios profesionales de plomería, incluyendo reparaciones de tuberías, instalaciones y mantenimiento. Nuestros expertos aseguran las mejores soluciones para tus necesidades de plomería.",
    },
   
];

const introduce = document.querySelector(".introduce");
const ordinalNumber = document.querySelector(".ordinal-number");
const thumbnailListWrapper = document.querySelector(".thumbnail-list .wrapper");
const nextBtn = document.querySelector(".navigation .next-button");

let currentIndex = 0;

// Función para inicializar el contenido
function initializeContent() {
    introduce.innerHTML = "";
    ordinalNumber.innerHTML = "";
    thumbnailListWrapper.innerHTML = "";

    // Llenar introduce y ordinalNumber con el primer elemento
    introduce.innerHTML += `
        <div class="wrapper" style="--idx: ${currentIndex}">
            <span>
                <h5 class="country">${data[currentIndex].country}</h5>
            </span>
            <span>
                <h1 class="place">${data[currentIndex].place}</h1>
            </span>
            <span>
                <p class="describe">${data[currentIndex].describe}</p>
            </span>
            <span>
                <button class="discover-button">Discover</button>
            </span>
        </div>
    `;

    ordinalNumber.innerHTML += `<h2>${currentIndex + 1}</h2>`;

    // Llenar thumbnailListWrapper con las miniaturas
    data.forEach((item, index) => {
        if (index === 0) {
            thumbnailListWrapper.innerHTML += `
                <div class="thumbnail" zoom>
                    <img src="${item.img}" alt="">
                </div>
            `;
        } else {
            thumbnailListWrapper.innerHTML += `
                <div class="thumbnail" style="--idx: ${index - 1}">
                    <img src="${item.img}" alt="">
                </div>
            `;
        }
    });

    // Activar el primer elemento
    introduce.children[0].classList.add("active");
    ordinalNumber.children[0].classList.add("active");
}

// Función para actualizar el contenido al hacer clic en "Siguiente"
function updateContent() {
    nextBtn.disabled = true;

    // Clonar el primer thumbnail
    const clone = thumbnailListWrapper.children[0].cloneNode(true);
    clone.classList.remove("zoom");

    // Añadir el clon al final de la lista
    thumbnailListWrapper.appendChild(clone);

    // Aplicar zoom al segundo elemento
    setTimeout(() => {
        thumbnailListWrapper.children[1].classList.add("zoom");
        thumbnailListWrapper.children[0].remove();
        nextBtn.disabled = false;
    }, 200);

    // Ajustar los índices de los demás elementos
    for (let i = 2; i < thumbnailListWrapper.childElementCount; i++) {
        thumbnailListWrapper.children[i].style.setProperty('--idx', i - 2);
    }

    // Actualizar el índice
    if (currentIndex < data.length - 1) {
        currentIndex++;
    } else {
        currentIndex = 0;
    }

    // Aplicar efecto de salida antes de limpiar el contenido
    introduce.children[0].classList.add("fade-out");
    ordinalNumber.children[0].classList.add("fade-out");

    // Esperar a que termine la animación de desvanecimiento
    setTimeout(() => {
        // Limpiar el contenido anterior y actualizar el texto e imagen
        introduce.innerHTML = "";
        ordinalNumber.innerHTML = "";

        // Actualizar introduce y ordinalNumber con el nuevo índice
        introduce.innerHTML += `
            <div class="wrapper" style="--idx: ${currentIndex}">
                <span>
                    <h5 class="country">${data[currentIndex].country}</h5>
                </span>
                <span>
                    <h1 class="place">${data[currentIndex].place}</h1>
                </span>
                <span>
                    <p class="describe">${data[currentIndex].describe}</p>
                </span>
                <span>
                    <button class="discover-button">Discover</button>
                </span>
            </div>
        `;

        ordinalNumber.innerHTML += `<h2>${currentIndex + 1}</h2>`;

        // Añadir clase 'active' al nuevo índice
        introduce.children[0].classList.add("active");
        ordinalNumber.children[0].classList.add("active");
    }, 500);
}

// Inicializar el contenido al cargar la página
initializeContent();

// Agregar evento de clic al botón "Siguiente"
nextBtn.addEventListener("click", updateContent);