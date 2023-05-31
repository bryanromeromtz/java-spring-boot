

const formLogin = document.getElementById("form-login-user");

formLogin.addEventListener("submit", (e) => {
    iniciarSesion();
    e.preventDefault();
});





function iniciarSesion() {
    const email = document.getElementById("exampleInputEmail").value;
    const password = document.getElementById("exampleInputPassword").value;
    const data = {
        email,
        password
    };

    fetch("api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            return response.text(); // En caso de éxito, obtener el mensaje de texto
        } else {
            //throw new Error(response.statusText); // En caso de error, lanzar una excepción con el mensaje de error del servidor
            return response.text();
        }
    })
    .then(responseText => {
        //console.log(responseText); // Imprimir el mensaje de texto de la respuesta
        // Aquí puedes realizar las acciones necesarias según la respuesta del servidor
        if(responseText === "Credenciales válidas") {
            alert("Sesion Iniciada");
        } else {
            alert("Credenciales Incorrectas");
        }
    })
    .catch(error => {
        console.error(error);
        // Manejo de errores
    });
}
