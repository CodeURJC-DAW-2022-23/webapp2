const submitBtn = document.getElementById('submitBtn');
submitBtn.addEventListener('click', function() {
  const forms = document.querySelectorAll('form');
  const formData = new FormData();
  forms.forEach(function(form) {
    const elements = form.elements;
    for (let i = 0; i < elements.length; i++) {
      const element = elements[i];
      formData.append(element.name, element.value);   
    }
  });
  fetch('/globalFormSubmit', {
    method: 'POST',
    body: formData
  })
  .then(function(response) {
    // handle response
  })
  .catch(function(error) {
    // handle error
  });
});