$('.navTrigger').click(function () {
    $(this).toggleClass('active');
    console.log("Clicked menu");
    $("#mainListDiv").toggleClass("show_list");
    $("#mainListDiv").fadeIn();
});

var eventos = document.getElementById('eventos');
var agenda = document.getElementById('agenda');
var elemento = document.getElementById('elemento');

// var buscador = document.getElementById('buscador');

// window.onscroll = function() {
//     var y = window.scrollY;
//     // console.log(y);

//     if (y>=274){
//         buscador.classList.add('sticky');
//     }
//     else{
//         buscador.classList.remove('sticky');
//     }
//   };


// eventos.addEventListener('click', cargar);

// agenda.addEventListener('click', cargar);

// manejar carrusel botones
const next = document.querySelector('#next')
const prev = document.querySelector('#prev')

function handleScrollNext() {
    const cards = document.querySelector('.con-cards');

    cards.scrollLeft = cards.scrollLeft += window.innerWidth / 2 > 600 ? window.innerWidth / 2 : window.innerWidth - 100;
    // cards.scrollLeft +=800;
}

function handleScrollPrev() {
    const cards = document.querySelector('.con-cards');

    cards.scrollLeft = cards.scrollLeft -= window.innerWidth / 2 > 600 ? window.innerWidth / 2 : window.innerWidth - 100;
    // cards.scrollLeft -=800;
}

next.addEventListener('click', handleScrollNext);
prev.addEventListener('click', handleScrollPrev);

// function cargar(e) {

//     let p = e.target.id

//     switch (p) {
//         case 'eventos': 
//             inicio();
//             next.addEventListener('click', handleScrollNext);
//             prev.addEventListener('click', handleScrollPrev);

//             break;
//         case 'agenda': elemento.innerHTML = '<p>Proximamente</p>';
//             break;

//     }
// }

