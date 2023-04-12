/**
 * Crea las funciones necesarias para conexión con el aplicativo (back-end) y la base de datos.
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 */

/**
 * Función encargada de guardar un nuevo contacto en el servidor mediante una solicitud POST.
 */
function saveContact(){
    const form = document.querySelector("#form");
    form.addEventListener('submit', (e) =>{
        e.preventDefault();

        fetch('http://localhost:8080/save', {
        method:'POST',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            name: document.getElementById("newName").value,
            phone: document.getElementById("phone").value,
            email: document.getElementById("email").value,
            birth: document.getElementById("birth").value
        })
    })
        .then(response => response.json())
        .then(json => {
            alert("Se ha guardado el nuevo contacto")
            cleanTable()
            showContacts()
            console.log(json)})
        .catch(err => console.log(err));

    })
}

/**
 * Función encargada de hacer una solicitud GET al servidor para obtener todos los contactos y luego muestra los datos en una tabla en la página.
 */
function showContacts(){
    fetch('http://localhost:8080/all', {
    method:'GET',
    headers: {
        "Content-type": "application/json"
    }
})
    .then(response => response.json())
    .then(json => {
        console.log(json);
        showData(json);
    })
    .catch(err => console.log(err));

}

/**
 * Función encargada de limpiar el contenido de la tabla donde se muestran los contactos.
 */
function cleanTable(){
    const clean = document.querySelector("#contentTable");
    clean.innerHTML="";
}

/**
 * Función encargada de mostrar los datos de los contactos obtenidos del servidor en una tabla en la página.
 * @param items  Un arreglo de objetos que representan los contactos.
 */
function showData(items) {

    const tableBody = document.querySelector("#contentTable");
    items.forEach(item => {
        const row = document.createElement("tr");

        let columnId = document.createElement("td");
        columnId.textContent = item.id;
        row.appendChild(columnId);

        let columnName = document.createElement("td");
        columnName.textContent = item.name;
        row.appendChild(columnName);

        let columnPhone = document.createElement("td");
        columnPhone.textContent = item.phone;
        row.appendChild(columnPhone);

        let columnEmail = document.createElement("td");
        columnEmail.textContent = item.email;
        row.appendChild(columnEmail);

        let columnBirth = document.createElement("td");
        columnBirth.textContent = item.birth;
        row.appendChild(columnBirth);

        let columnSoftDelete = document.createElement("td");
        let contactSoftDelete= document.createElement("input");
        contactSoftDelete.type= 'button';
        contactSoftDelete.value = "Borrar";
        contactSoftDelete.class = "btnmin";
        contactSoftDelete.onclick = function (){
            console.log(item.id);
            updateDelete(item.id);
        }
        columnSoftDelete.appendChild(contactSoftDelete)
        row.appendChild(columnSoftDelete);

        let columnUpdate = document.createElement("td");
        let contactUpdate = document.createElement("input");
        contactUpdate.type = 'button';
        contactUpdate.value = "Actualizar";
        contactUpdate.id = "btnmodal"
        contactUpdate.class = "btn min";
        contactUpdate.setAttribute("data-toggle","modal");
        contactUpdate.setAttribute("data-target", "#exampleModalCenter")
        contactUpdate.onclick = function (){
            $(document).on('click', '#btnmodal', function (){
                $('#exampleModalCenter').modal('show')
            });
            console.log(item.id);
            findContact(item.id);
        }
        columnUpdate.appendChild(contactUpdate)
        row.appendChild(columnUpdate);

        let columnDelete = document.createElement("td");
        let contactDelete = document.createElement("input");
        contactDelete.type= 'button';
        contactDelete.value = "Eliminar totalmente";
        contactDelete.class = "btn min";
        contactDelete.onclick = function (){
            console.log(item.id);
            deleteContact(item.id);
        }
        columnDelete.appendChild(contactDelete)
        row.appendChild(columnDelete);


        tableBody.appendChild(row);
    })
}

/**
 * Función que busca un contacto por su id y muestra su información en un formulario de edición.
 * @param id  El id del contacto a buscar.
 */
 function findContact(id){
    let url = 'http://localhost:8080/get/' + id;
     fetch(url, {
         method:'GET',
         headers: {
             "Content-type": "application/json"
         },
     })
         .then(response => response.json())
         .then(json => {
             console.log(json)
             takeId.value = id;
             editName.value = json.name;
             editPhone.value = json.phone;
             editEmail.value = json.email;
             editBirth.value = json.birth
         })
         .catch(err => console.log(err));

 }

/**
 * Función que elimina completamente un contacto por su id.
 * @param id El id del contacto a eliminar.
 */
function deleteContact(id){
    console.log(id)
    console.log(typeof id)
   let url = "http://localhost:8080/delete/" + id
    console.log(url)
    fetch(url, {
        method:'DELETE',
    })
    .then(response => response.json())
        .then(json => {
            alert("Se ha eliminado completamente el contacto")
            console.log(json)
            cleanTable()
            showContacts()
        })
        .catch(err => console.log(err));

}

/**
 * Función que actualiza el campo 'deleted_at' de un contacto a la fecha y hora actuales, simulando una eliminación suave.
 * @param id El id del contacto a actualizar.
 */
function updateDelete(id){
    console.log(id);
    let url = 'http://localhost:8080/update/delete/'+ id + '?deleteAt=' +now1()
    console.log(url);
    fetch(url, {
        method:'PATCH',
    })
        .then(response => response.json())
        .then(json => {
            alert("Se ha borrado un contacto");
            console.log(json)
            cleanTable()
            showContacts()
        })
        .catch(err => console.log(err));
}

/**
 * Función que devuelve la fecha actual en formato ISO (AAAA-MM-DD).
 * @returns {string}La fecha actual en formato ISO (AAAA-MM-DD).
 */
function now1(){
    const date = new Date().toISOString().slice(0,10);
    console.log(date)
    return date
}

/**
 * Función que actualiza los datos de un contacto en la base de datos a través de una petición PUT a una API REST.
 */
function updateContact(){
        let id = document.getElementById('takeId').value
        let url = "http://localhost:8080/update/" + id
        console.log(url)
        fetch(url, {
            method: 'PUT',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                name: document.getElementById('editName').value,
                phone: document.getElementById("editPhone").value,
                email: document.getElementById("editEmail").value,
                birth: document.getElementById("editBirth").value
            }),
        })
            .then(response => response.json())
            .then(json =>{
                alert("Se ha actualizado un dato");
                console.log(json)
                cleanTable()
                showContacts()
            })
            .catch(err => console.log(err));
}

/**
 * Función que cierra el modal utilizado para editar contactos.
 */
function closer() {
    $(document).on('click', '#closeModal', function (){
        $('#exampleModalCenter').modal('hide')
    });
}