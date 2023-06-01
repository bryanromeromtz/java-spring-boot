

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
    .then(data => data.text())
    .then(responseText => {
            if (responseText === "OK") {
                window.location.href = 'usuarios.html';
            } else if (responseText === "FAIL") {
                // Acciones a realizar en caso de credenciales inválidas
                console.log("Credenciales inválidas");
            } else {
                // Acciones a realizar en caso de otra respuesta
                console.log("Respuesta desconocida");
            }
    })
    .catch(error => {
        console.error(error);
        // Manejo de errores
    });
}
