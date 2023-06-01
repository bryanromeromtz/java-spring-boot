

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
    .then(response => response.text())
    .then(responseText => {
        if (responseText !== "FAIL") {
            localStorage.token = responseText;
            localStorage.email = data.email;
            window.location.href = "usuarios.html";
        } else {
            console.log("Credenciales inválidas");
        }
    })
    .catch(error => {
        console.error(error);
        // Manejo de errores
    });
}

