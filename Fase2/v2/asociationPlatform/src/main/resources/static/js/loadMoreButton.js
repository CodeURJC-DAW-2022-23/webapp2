var page=6;
const site = document.getElementById('cardsSite');
const moreEvents = document.getElementById('moreEvents');
moreEvents.addEventListener('click', getMoreEvents);

function getMoreEvents(){
  fetch('/loadMore/'+ page, {
        method: 'GET',
      }).then(response => response.text()).then(data =>{
        site.innerHTML = data;
      })
  page+=6;
}