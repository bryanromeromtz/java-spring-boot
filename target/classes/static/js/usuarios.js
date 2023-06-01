// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
  cargarUsuarios();
});


const cargarUsuarios = () => {
    fetch("api/usuarios", {
        method: 'GET',
        headers: getHeaders()
    })
      .then(response => response.json())
      .then(data => {
                console.log(data);
                let html = "";
                data.forEach(usuario => {
                    let botonEliminarUsuario = `
                         <button
                            class="btn btn-danger btn-circle btn-sm"
                            onclick="eliminarUsuario(${usuario.id})"
                            >
                            <i class="fas fa-trash"></i>
                         </button>`;
                    let usuarioHtml = `<tr id="usuario-${usuario.id}">
                                <td>${usuario.id}</td>
                                <td>${usuario.nombre + " " + usuario.apellido}</td>
                                <td>${usuario.telefono}</td>
                                <td>${usuario.email}</td>
                                <td>
                                   ${botonEliminarUsuario}
                                </td>
                            </tr>`;
                    html += usuarioHtml;
                });
             document.querySelector("#usuarios tbody").outerHTML = html;
      });

}

const getHeaders = () => {
    return  {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

const eliminarUsuario = (id) => {
    fetch(`api/usuarios/${id}`, {
        method: 'DELETE',
        headers: getHeaders()
    })
    .then(res => res.text()) // or res.json()
    .then(res => {
        document.getElementById(`usuario-${id}`).remove();
    })
}