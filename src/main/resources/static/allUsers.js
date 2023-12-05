let tablebody = document.getElementById("tbody");
let user_roles = document.getElementById('rls');
showAll();

function showAll() {
    fetch("/api/admin/users")
        .then(response => response.json())
        .then(users => {
            let tr;
            for (let i = 0; i < users.length; i++) {
                tr = document.createElement('tr');
                tr.setAttribute("id", `user${users[i].id}`)
                tr.innerHTML = `
        <td>${users[i].id}</td>
        <td>${users[i].username}</td>
        <td>${users[i].lastname}</td>
        <td>${users[i].email}</td>            
        <td>${users[i].roles.map(role => " " + role.name.substring(5))}</td>
        <td><button class="btn btn-info" data-toggle="modal" data-target="#editModal" onclick="getUserFieldsForEditModal(${users[i].id})">Edit</button></td>
        <td><button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" onclick="getUserFieldsForDelModal(${users[i].id})">Delete</button></td>                                
    `;
                tablebody.append(tr);
            }

        });


    fetch("http://localhost:8080/api/admin/user")
        .then((response) => {
            return response.json();
        })
        .then(user => {
            let rls = `${user.roles.map(role => " " + role.name.substring(5))}`;
            user_roles.innerHTML = `<h5><strong>${user.email}</strong> with roles: <strong>${rls}</strong></h5>`;
        });
}


//Для редактирования пользователя

let editForm = document.getElementById("formEdit");

function getUserFieldsForEditModal(id) {
    fetch(`/api/admin/${id}`)
        .then(response => response.json())
        .then(user => {
            editForm.idEdit.value = user.id;
            editForm.usernameEdit.value = user.username;
            editForm.lastnameEdit.value = user.lastname;
            editForm.passwordEdit.value = user.password;
            editForm.emailEdit.value = user.email;
        });
}

editForm.addEventListener('submit', editUserListener => {
    editUserListener.preventDefault();
    let editUserRoles = [];
    let markedRoles = editForm.roles.selectedOptions;
    for (let i = 0; i < markedRoles.length; i++) {
        editUserRoles.push({name: markedRoles[i].textContent});
    }
    let bodyInfo = JSON.stringify({
        id: editForm.idEdit.value,
        username: editForm.usernameEdit.value,
        lastname: editForm.lastnameEdit.value,
        password: editForm.passwordEdit.value,
        email: editForm.emailEdit.value,
        roles: editUserRoles
    });
    let reqToEdit = {
        method: "PATCH",
        headers: {"Content-Type": "application/json"},
        body: bodyInfo
    }
    fetch(`/api/admin`, reqToEdit)
        .then(response => response.json())
        .then(updatedUser => {
            let editUserRow = document.getElementById(`user${updatedUser.id}`);
            editUserRow.innerHTML = `
                <td>${updatedUser.id}</td>
                <td>${updatedUser.username}</td>
                <td>${updatedUser.lastname}</td>
                <td>${updatedUser.email}</td>            
                <td>${updatedUser.roles.map(role => " " + role.name.substring(5))}</td>
                <td><button class="btn btn-info" data-toggle="modal" data-target="#editModal" onclick="getUserFieldsForEditModal(${updatedUser.id})">Edit</button></td>
                <td><button class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" onclick="getUserFieldsForDelModal(${updatedUser.id})">Delete</button></td> 
            `;
            document.getElementById("editModalClose").click();
        });
});

//Удаление пользователя
let deleteForm = document.getElementById("deleteForm")

function getUserFieldsForDelModal(id) {
    fetch(`/api/admin/${id}`)
        .then(response => response.json())
        .then(user => {
            deleteForm.idDelete.value = user.id;
            deleteForm.usernameDel.value = user.username;
            deleteForm.lastnameDel.value = user.lastname;
            deleteForm.emailDelete.value = user.email;
        });
}

deleteForm.addEventListener('submit', delUserListener => {
    delUserListener.preventDefault();
    let reqToDelete = {
        method: "DELETE",
        headers: {"Content-Type": "application/json"},
        body: deleteForm.idDelete.value,
    };
    fetch(`/api/admin`, reqToDelete)
        .then(response => response.json())
        .then(userToDelId => {
            let rowToRemove = document.getElementById(`user${userToDelId}`);
            rowToRemove.remove();
            document.getElementById("deleteModalClose").click();
        });
});

//Для добавления пользователя

