let tbl = document.getElementById('tblBody');
let user_roles = document.getElementById('rls');
let user_roles_tumbler = document.getElementById('rolesTumbler');

fetch("/api/user/roles")
    .then(resp => resp.json())
    .then(roles => {
        let appendix = ``;
        let node_id;
        let node_href;
        let node_val;
        let h_light;
        for (let i = 0; i < roles.length; i++) {
            if (roles[i].name.indexOf("ADMIN")!==-1) {
                node_id = 'id="admin"';
                node_href = 'href="/admin/users"';
                node_val = "Admin";
                h_light = "";
            } else {
                node_id = '';
                node_href = 'href="/user"';
                node_val = "User";
                h_light = "active";
            }
            appendix += `<li ${node_id} class="nav-item">
                            <a class="nav-link ${h_light}" ${node_href}>${node_val}</a>
                        </li>`;
        }
        user_roles_tumbler.innerHTML = appendix;

        fetch("http://localhost:8080/api/user")
            .then((response) => {
                return response.json();
            })
            .then(user => {
                let rls = `${user.roles.map(role => " " + role.name.substring(5))}`;
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
    });


