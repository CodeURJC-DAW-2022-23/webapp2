const moreEvents = document.getElementById('moreEvents');
submitBtn.addEventListener('click', getMoreEvents());

var page=1;

function getMoreEvents(){
    fetch('/eventsMore/'+page, {
        method: 'GET',
      })
}