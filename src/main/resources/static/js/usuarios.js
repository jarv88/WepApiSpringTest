// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios()
  $('#dataTable').DataTable();
});




async function cargarUsuarios(){

      const request = await fetch('api/usuarios', { //https://my.api.mockaroo.com/users.json?key=387f39a0
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        //body: JSON.stringify({a: 1, b: 'Textual content'})
      });
      const content = await request.json();

      console.log(content);

      let usuario="";

        //se recorre el json content y se concatena la cadena usuario con la estructura html de la tabla
      for(user of content){
      let telefono = user.telefono == null ? "-" : user.telefono;
      let botonEliminar = `<a href="#" onclick=deleteUsuario(${user.id}) class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>`
      usuario = usuario + `<tr><td>${user.id}</td><td>${user.nombre} ${user.apellido}</td><td>${telefono}</td><td>${user.email}</td><td>${botonEliminar}</td>`
      //console.log(user.full_name);
      }


      document.querySelector('#dataTable tbody').outerHTML=usuario;

}

async function deleteUsuario(id){

    if (confirm('Está seguro de eliminar el usuario?')){
        const request = await fetch('api/usuarios/'+id, { //https://my.api.mockaroo.com/users.json?key=387f39a0
                                method: 'DELETE',
                                headers: {
                                  'Accept': 'application/json',
                                  'Content-Type': 'application/json'
                                },
                              });

        location.reload()

    }


}