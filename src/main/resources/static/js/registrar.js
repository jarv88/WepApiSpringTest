// Call the dataTables jQuery plugin
$(document).ready(function() {

});




async function registrarUsuario(){
    let datos ={};
    datos.nombre=document.getElementById('txtNombre').value;
    datos.apellido=document.getElementById('txtApellido').value;
    datos.email=document.getElementById('txtEmail').value;
    datos.password=document.getElementById('txtPassword').value;
    let repetirPassword =document.getElementById('txtRepetirPassword').value;

    if (repetirPassword!=datos.password){
        alert("Las contraseñas no coinciden");
        return;
    }

      const request = await fetch('api/usuarios', { //https://my.api.mockaroo.com/users.json?key=387f39a0
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const content = await request.json();



}
