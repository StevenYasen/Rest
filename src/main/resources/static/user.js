let tbl = document.getElementById('tblBody');
let user_roles = document.getElementById('rls');

fetch("http://localhost:8080/api/user")
    .then((response) => {
        return response.json();
    })
    .then(user => {
        let  rls = `${user.roles.map(role => " " + role.name.substring(5))}`;
        let row = `<tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.lastname}</td>                  
                        <td>${user.email}</td>
                        <td>${rls}</td>
                   </tr>`;
        tbl.innerHTML = row;
        user_roles.innerHTML = `<h5><strong>${user.email}</strong> with roles: <strong>${rls}</strong></h5>`;
        if (rls.indexOf("ADMIN") === -1) {
            document.getElementById('admin').style.display = "none";
        }
    });


