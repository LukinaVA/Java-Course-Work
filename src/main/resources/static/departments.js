function searchById() {
    let id = document.getElementById("searchById").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let department = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';

            html = html + '<tr><td>' + department.id + '</td>\n' +
                '        <td>' + department.name + '</td>\n' +
                '        <td><button onclick="deleteDepartment(' + department.id + ')">Удалить</button></td></tr>';

            document.getElementById("departments").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/departments/list/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function deleteDepartment(id) {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status >= 400) {
                alert("Отказ в доступе!");
            }
            window.location.reload();
        }
    };

    xhttp.open("DELETE", "http://localhost:8080/departments/delete/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function addDepartment() {
    let name = document.getElementById("name").value;

    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status >= 400) {
                alert("Отказ в доступе!");
            }
        }
    };

    xmlhttp.open("POST", "http://localhost:8080/departments/addDepartment");
    xmlhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: name}));

    loadDepartments();
}

function loadDepartments() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let departments = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';
            for (let i = 0; i < departments.length; i++) {
                let department = departments[i];
                html = html + '<tr><td>' + department.id + '</td>\n' +
                    '        <td>' + department.name + '</td>\n' +
                    '        <td><button onclick="deleteDepartment(' + department.id + ')">Удалить</button></td></tr>';

            }
            document.getElementById("departments").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/departments/list", true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function searchByName() {
    let name = document.getElementById("searchByName").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let department = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';

            html = html + '<tr><td>' + department.id + '</td>\n' +
                '        <td>' + department.name + '</td>\n' +
                '        <td><button onclick="deleteDepartment(' + department.id + ')">Удалить</button></td></tr>';

            document.getElementById("departments").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/departments/" + name, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function signOut() {
    localStorage.clear();
    window.location.href = "index.html";
}

loadDepartments();