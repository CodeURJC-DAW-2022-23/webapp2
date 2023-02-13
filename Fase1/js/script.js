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

function inicio() {
    elemento.innerHTML = `
    <button id="prev" class="btn">
    <i class="bx bxs-chevron-left"></i>
  </button>
  <div class="con-cards">
    <div class='widget'>
    <div class='widget__photo'></div>
    <div class='widget__button'>
        <a href="../html/detalles.html">Más Info</a>
    </div>
    <div class='widget__details'>
        <div class='widget__name'>
            Evento Apertura
        </div>
        <div class='widget__type'>
            Anger
        </div>
        <div class='widget__info'>
            <span>
                Universidad Rey Juan Carlos
            </span>
            <span>
                Móstoles
            </span>
        </div>
        <div class='widget__hidden'>
            <hr>
            <table class='widget__table'>
                <tr>
                    <td>
                        Fecha
                    </td>
                    <td>
                        17 de febrero 2021
                    </td>
                </tr>
                <tr>
                    <td>
                        Horario
                    </td>
                    <td>
                        9-12
                    </td>
                </tr>
                <tr>
                    <td>
                        Lugar
                    </td>
                    <td>
                        Salón de actos
                    </td>
                </tr>
                <tr>
                    <td>
                        Reserva
                    </td>
                    <td>
                        Si
                    </td>
                </tr>
                <tr>
                    <td>
                        Creditos
                    </td>
                    <td>
                        No
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class='widget'>
            <div class='widget__photo'></div>
            <div class='widget__button'>
                <a href="../html/detalles.html">Más Info</a>
            </div>
            <div class='widget__details'>
                <div class='widget__name'>
                    Evento Apertura
                </div>
                <div class='widget__type'>
                    Anger
                </div>
                <div class='widget__info'>
                    <span>
                        Universidad Rey Juan Carlos
                    </span>
                    <span>
                        Móstoles
                    </span>
                </div>
                <div class='widget__hidden'>
                    <hr>
                    <table class='widget__table'>
                        <tr>
                            <td>
                                Fecha
                            </td>
                            <td>
                                17 de febrero 2021
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Horario
                            </td>
                            <td>
                                9-12
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lugar
                            </td>
                            <td>
                                Salón de actos
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Reserva
                            </td>
                            <td>
                                Si
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Creditos
                            </td>
                            <td>
                                No
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class='widget'>
            <div class='widget__photo'></div>
            <div class='widget__button'>
                <a href="../html/detalles.html">Más Info</a>
            </div>
            <div class='widget__details'>
                <div class='widget__name'>
                    Evento Apertura
                </div>
                <div class='widget__type'>
                    Anger
                </div>
                <div class='widget__info'>
                    <span>
                        Universidad Rey Juan Carlos
                    </span>
                    <span>
                        Móstoles
                    </span>
                </div>
                <div class='widget__hidden'>
                    <hr>
                    <table class='widget__table'>
                        <tr>
                            <td>
                                Fecha
                            </td>
                            <td>
                                17 de febrero 2021
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Horario
                            </td>
                            <td>
                                9-12
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lugar
                            </td>
                            <td>
                                Salón de actos
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Reserva
                            </td>
                            <td>
                                Si
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Creditos
                            </td>
                            <td>
                                No
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class='widget'>
            <div class='widget__photo'></div>
            <div class='widget__button'>
                <a href="../html/detalles.html">Más Info</a>
            </div>
            <div class='widget__details'>
                <div class='widget__name'>
                    Evento Apertura
                </div>
                <div class='widget__type'>
                    Anger
                </div>
                <div class='widget__info'>
                    <span>
                        Universidad Rey Juan Carlos
                    </span>
                    <span>
                        Móstoles
                    </span>
                </div>
                <div class='widget__hidden'>
                    <hr>
                    <table class='widget__table'>
                        <tr>
                            <td>
                                Fecha
                            </td>
                            <td>
                                17 de febrero 2021
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Horario
                            </td>
                            <td>
                                9-12
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lugar
                            </td>
                            <td>
                                Salón de actos
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Reserva
                            </td>
                            <td>
                                Si
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Creditos
                            </td>
                            <td>
                                No
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        
        </div>
        <button id="next" class="btn">
          <i class="bx bxs-chevron-right"></i>
        </button>`
}
inicio();

eventos.addEventListener('click', cargar);

agenda.addEventListener('click', cargar);

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

function cargar(e) {

    let p = e.target.id

    switch (p) {
        case 'eventos': 
            inicio();
            next.addEventListener('click', handleScrollNext);
            prev.addEventListener('click', handleScrollPrev);

            break;
        case 'agenda': elemento.innerHTML = '<p>Proximamente</p>';
            break;

    }
}

