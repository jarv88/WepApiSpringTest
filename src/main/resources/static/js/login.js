// Call the dataTables jQuery plugin
$(document).ready(function() {

});




async function iniciarSesion(){
    let datos ={};

    datos.email=document.getElementById('txtEmailLogin').value;
    datos.password=document.getElementById('txtPasswordLogin').value;


      const request = await fetch('api/login', { //https://my.api.mockaroo.com/users.json?key=387f39a0
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      //const content = await request.json();
      const response = await request.text();
      if (response == "OK"){
        window.location.href='usuarios.html'
      } else{
        alert("Credenciales incorrectas");
      }




}
