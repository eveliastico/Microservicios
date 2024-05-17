document.addEventListener("DOMContentLoaded", function () {
    const stateSelect = document.getElementById('id_invernadero');
    const form = document.getElementById('registroForm');

    // Función para cargar los datos desde la API
    function cargarDatosAPI() {
        fetch('http://localhost:8080/api/invernadero/all')
            .then(response => response.json())
            .then(data => {
                data.forEach(item => {
                    const option = document.createElement('option');
                    option.value = item.id; // Valor del option, puedes cambiarlo según tu API
                    option.text = item.nombre; // Texto visible en el ComboBox
                    stateSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar datos desde la API:', error));
    }

    // Llamada a la función para cargar los datos al cargar la página
    cargarDatosAPI();

    //-----------------------------------------------------------------------------

    // Agregar evento de clic al botón Registrar
    const btnRegistrar = document.querySelector('.formbold-btn');
    btnRegistrar.addEventListener('click', function(event) {
        event.preventDefault(); // Evita que el formulario se envíe de manera tradicional

        // Obtener los valores de los campos del formulario y enviarlos a la API
        enviarDatosAPI();
    });

    // Función para enviar los datos del formulario a la API de sensor
    function enviarDatosAPI() {
        // Obtener los valores de los campos del formulario
        const idInvernadero = document.getElementById('id_invernadero').value;
        const marcaSensor = document.getElementById('marca').value;
        const timeStamp = new Date().toISOString(); // Obtener la fecha y hora actual en formato ISO

        // Crear el objeto JSON con los datos del sensor
        const sensorData = {
            invernaderoId: idInvernadero,
            fechaHora: timeStamp,
            marca: marcaSensor
        };

        // Enviar el objeto JSON a la API para registrar el nuevo sensor
        fetch('http://localhost:8092/api/sensor/create-new-sensor', {
            method: 'POST',
            body: JSON.stringify(sensorData),
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            }
        })
        .then(response => {
            if(response.ok){
                alert('El sensor se ha registrado correctamente.')
            }else{
                throw new Error('El servidor no respondio con OK');
            }
        })
        .then(data => {
            console.log('Respuesta de la API:', data);
            // Puedes agregar lógica adicional aquí, como mostrar un mensaje de éxito al usuario
        })
        .catch(error => {
            console.error('Error al enviar los datos a la API:', error);
            // Puedes manejar el error y mostrar un mensaje al usuario si es necesario
        });
    }
    //-----------------------------
});