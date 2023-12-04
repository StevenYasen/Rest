let editForm = document.getElementById("formEdit");

function getUserFieldsForModal(id) {
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
    let editUserRoles = [];
    let markedRoles = editForm.roles.selectedOptions;
    for (let i = 0; i < markedRoles.length; i++) {
        editUserRoles.push({name:markedRoles[i].textContent});
    }
    let bodyInfo = JSON.stringify({
        id: editForm.idEdit.value,
        username: editForm.usernameEdit.value,
        lastname: editForm.lastnameEdit.value,
        password: editForm.passwordEdit.value,
        email: editForm.emailEdit.value,
        roles: editUserRoles
    });
    let req = {
        method: "PATCH",
        headers: {"Content-Type": "application/json"},
        body: bodyInfo
    }
    fetch(`/api/admin`, req)
        .then(answer => {
            alert("answer");
        });


});