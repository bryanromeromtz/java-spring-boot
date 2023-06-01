const formRegistrarUsuario = document.getElementById("form-registrar-usuario");

const registrarUsuario = () => {
    const nombre = document.getElementById("exampleFirstName").value;
    const apellido = document.getElementById("exampleLastName").value;
    const telefono = document.getElementById("exampleInputTelefono").value;
    const email = document.getElementById("exampleInputEmail").value;
    const password = document.getElementById("exampleInputPassword").value;
    const password2 = document.getElementById("exampleRepeatPassword").value;

    if (password != password2) {
        alert("Las contraseñas no coinciden, favor de verificar!!!!");
        return;
    }

    const data = {
        nombre,
        apellido,
        telefono,
        email,
        password
    };

    fetch("api/usuarios", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        if(data.id != 0) {
            alert("Usuario agragado con exito!!");
            setTimeout(function(){
                window.location.href = "login.html";
            }, 1500);

        }
    })
    .catch(error => {

        console.error(error);
    });
};

formRegistrarUsuario.addEventListener("submit", (event) => {
    event.preventDefault();
    registrarUsuario();
});
