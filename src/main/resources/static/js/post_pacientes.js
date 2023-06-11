window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaDeIngreso: document.querySelector('#fechaDeIngreso').value,
            /*domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },*/
            email: document.querySelector('#email').value
        };

        const url = '/pacientes';
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
                showResponse('success', 'Paciente agregado');
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

    function resetUploadForm() {
        document.querySelector('#nombre').value = '';
        document.querySelector('#apellido').value = '';
        document.querySelector('#cedula').value = '';
        document.querySelector('#fechaDeIngreso').value = '';
        document.querySelector('#calle').value = '';
        document.querySelector('#numero').value = '';
        document.querySelector('#localidad').value = '';
        document.querySelector('#provincia').value = '';
        document.querySelector('#email').value = '';
    }

});