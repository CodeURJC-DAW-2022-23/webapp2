var page=6;
const site = document.getElementById('cardsSite');

const moreEvents = document.getElementById('moreEvents');
moreEvents.addEventListener('click', getMoreEvents);

window.addEventListener('load',inicialize());

function inicialize(){
  page=6;
  getMoreEvents();
}

function getMoreEvents(){
  var xhr = new XMLHttpRequest();
  xhr.open("GET", '/loadMore/'+ page);
  xhr.onload = function() {
    if (xhr.status === 200) {
      // console.log(xhr.responseText);
      site.innerHTML = xhr.responseText;
    }
    else {
      console.log("Request failed. Status: " + xhr.status);
    }
  };
  xhr.send();
  page+=6;
}