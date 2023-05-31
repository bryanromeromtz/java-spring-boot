// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
  cargarUsuarios();
});


const cargarUsuarios = () => {
    fetch("api/usuarios")
      .then(response => response.json())
      .then(data => {
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

const eliminarUsuario = (id) => {
    fetch(`api/delete-usuario/${id}`, {
        method: 'DELETE',
    })
    .then(res => res.text()) // or res.json()
    .then(res => {
        document.getElementById(`usuario-${id}`).remove();
    })
}