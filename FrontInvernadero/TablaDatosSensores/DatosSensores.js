document.addEventListener("DOMContentLoaded", function () {
    function obtenerDatosSensores() {
        return fetch('http://localhost:8080/api/sensor/all')
                .then(response => response.json())
                .then(data => {
                    renderizarDatos(data);
                    renderizarGraficas(data);
                })
                .catch(error => console.error('Error al obtener datos del backend:', error));
    }

    function renderizarDatos(data) {
        const tableBody = document.querySelector('#data-table tbody');
        tableBody.innerHTML = ''; //Sirve para limpiar la tabla
        data.forEach(row => {
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td>${row.id}</td>
                <td>${row.humedad}</td>
                <td>${row.temperatura}</td>
                <td>${row.fechaHora}</td>
            `;
            tableBody.appendChild(newRow);
        });
    }

    function renderizarGraficas(data) {

        const humidityData = data.map(item => ({x: item.id, y: item.humedad}));
        const temperatureData = data.map(item => ({x: item.id, y: item.temperatura}));
        const ctx = document.getElementById('registros-chart').getContext('2d');
        const chart = new Chart(ctx, {
            type: 'line',
            data: {
                datasets: [
                    {
                        label: 'Humedad',
                        data: humidityData,
                        backgroundColor: 'rgba(0, 0, 255, 0.2)',
                        borderColor: 'blue',
                        borderWidth: 1,
                        //yAxisID: 'y-axis-humidity',
                    },
                    {
                        label: 'Temperatura',
                        data: temperatureData,
                        backgroundColor: 'rgba(205, 92, 92, 0.2)',
                        borderColor: 'red',
                        borderWidth: 1,
                        //yAxisID: 'y-axis-temperature',
                    },
                ],
            },
            options: {
                scales: {
                    x: {
                        type: 'linear',
                        position: 'bottom',
                    },
                    y: {
                        beginAtZero: true,
                        //position: 'left',
                        //id: 'y-axis-humidity',
                    }
                    //y1: {
                        //beginAtZero: true,
                      //  position: 'right',
                        //id: 'y-axis-temperature',
                    //},
                },
            },
        });
    }

//Se llama la funcion para obtener los datos de los sensores.
    obtenerDatosSensores();

    //Funcion que permite descargar un archivo pdf con los datos de los sensores.
    function descargarTablaComoPDF() {

        const tabla = document.getElementById('data-table');
        html2canvas(tabla).then(canvas => {
            const pdf = new jsPDF('p', 'pt', 'letter');
            const imgData = canvas.toDataURL('image/png');
            const imgWidth = pdf.internal.pageSize.getWidth();
            const imgHeight = (canvas.height * imgWidth) / canvas.width;
            pdf.addImage(imgData, 'PNG', 0, 0, imgWidth, imgHeight);

            pdf.save("tabla_datos.pdf");
        });
    }
    const btnDescargarPDF = document.querySelector('.btnDescargar');
    btnDescargarPDF.addEventListener('click', descargarTablaComoPDF);

    //Funcion que permite descargar un archivo pdf con las graficas.
    function descargarGraficasComoPDF() {
        const graficaRegistrosCanvas = document.getElementById('registros-chart');

        Promise.all([
            html2canvas(graficaRegistrosCanvas)
        ]).then(([graficaRegistrosCanvas]) => {
            const pdf = new jsPDF('p', 'pt', 'letter');
            const imgWidth = pdf.internal.pageSize.getWidth();
            const imgHeight = imgWidth * 0.75;
            const graficaRegistrosImgData = graficaRegistrosCanvas.toDataURL('image/png');
            pdf.addImage(graficaRegistrosImgData, 'PNG', 40, 40, imgWidth - 80, imgHeight - 150);
            pdf.save("graficas_datos.pdf");
        });
    }

    const btnDescargarGraficas = document.querySelector('.btnDescargarGraficas');
    btnDescargarGraficas.addEventListener('click', descargarGraficasComoPDF);

});