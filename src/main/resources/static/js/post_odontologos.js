window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_odontologo');


    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,

        };

        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 console.log(data);
                 showResponse('success', 'Odontólogo agregado');
                 resetUploadForm();
            })
            .catch(error => {
                  showResponse('error', 'Error, intente nuevamente');
                  resetUploadForm();
            });
    });

    function showResponse(type, message) {
            const responseContainer = document.querySelector('#response');
            responseContainer.innerHTML = `<div class="alert alert-${type} alert-dismissible">` +
                `<button type="button" class="close" data-dismiss="alert">&times;</button>` +
                `<strong>${type.charAt(0).toUpperCase() + type.slice(1)}</strong> ${message}` +
                `</div>`;
            responseContainer.style.display = 'block';
        }

    function resetUploadForm(){
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
         document.querySelector('#apellido').value = "";

    }

});