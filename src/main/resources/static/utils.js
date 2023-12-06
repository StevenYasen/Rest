function setOptionRoles(selectIdToAttach, highlights = false, curUserRoles = []) {
    fetch("/api/admin/roles")
        .then(resp => resp.json())
        .then(roles => {
            let selectEdit = document.getElementById(selectIdToAttach);
            let appendix = ``;
            let checkRoles;
            let hLight = "";
            for (let i = 0; i < roles.length; i++) {
                if (highlights) {
                    checkRoles = curUserRoles.map(r => r.name + "/").toString().indexOf(roles[i].name) !== -1;
                    hLight = (checkRoles) ? "selected" : "";
                }
                appendix += `<option value="${i + 1}" ${hLight}>${roles[i].name.substring(5)}</option>`;
            }
            selectEdit.innerHTML = appendix;
        });
}