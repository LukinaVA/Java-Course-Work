function loadDep() {
    let department = document.getElementById("department");
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let departments = JSON.parse(this.responseText);
            for (let i = 0; i < departments.length; i++) {
                let dep = departments[i].name;
                department.options[i] = new Option(dep, dep);
            }
        }
    };

    xhttp.open("GET", "http://localhost:8080/departments/list", true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function searchById() {
    let id = document.getElementById("searchById").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let project = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Стоимость</th>\n' +
                '        <th>Отдел</th>\n' +
                '        <th>Дата начала</th>\n' +
                '        <th>Предполагаемая дата окончания</th>\n' +
                '        <th>Реальная дата окончания</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';

            html = html + '<tr><td>' + project.id + '</td>\n' +
                '        <td>' + project.name + '</td>\n' +
                '        <td>' + project.cost + '</td>\n' +
                '        <td>' + project.department.name + '</td>\n' +
                '        <td>' + project.dateBeg + '</td>' +
                '        <td>' + project.dateEnd + '</td>' +
                '        <td>' + project.dateEndReal + '</td>' +
                '        <td><button onclick="deleteProject(' + project.id + ')">Удалить</button></td></tr>';

            document.getElementById("projects").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/projects/list/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function searchByName() {
    let name = document.getElementById("searchByName").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let project = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Стоимость</th>\n' +
                '        <th>Отдел</th>\n' +
                '        <th>Дата начала</th>\n' +
                '        <th>Предполагаемая дата окончания</th>\n' +
                '        <th>Реальная дата окончания</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';

            html = html + '<tr><td>' + project.id + '</td>\n' +
                '        <td>' + project.name + '</td>\n' +
                '        <td>' + project.cost + '</td>\n' +
                '        <td>' + project.department.name + '</td>\n' +
                '        <td>' + project.dateBeg + '</td>' +
                '        <td>' + project.dateEnd + '</td>' +
                '        <td>' + project.dateEndReal + '</td>' +
                '        <td><button onclick="deleteProject(' + project.id + ')">Удалить</button></td></tr>';

            document.getElementById("projects").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/projects/" + name, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function deleteProject(id) {
    let xhttp = new XMLHttpRequest();

    xhttp.open("DELETE", "http://localhost:8080/projects/delete/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function addProject() {
    let name = document.getElementById("name").value;
    let cost = document.getElementById("cost").value;
    let department = document.getElementById("department").value;
    let dateBeg = document.getElementById("dateBeg").value;
    let dateEnd = document.getElementById("dateEnd").value;
    let dateEndReal = document.getElementById("dateEndReal").value;

    let xmlhttp = new XMLHttpRequest();

    xmlhttp.open("POST", "http://localhost:8080/projects/addProject");
    xmlhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({name: name, cost: cost, department: {name: department},
        dateBeg: dateBeg, dateEnd: dateEnd, dateEndReal: dateEndReal}));

    loadProjects();
}

function loadProjects() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let projects = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Название</th>\n' +
                '        <th>Стоимость</th>\n' +
                '        <th>Отдел</th>\n' +
                '        <th>Дата начала</th>\n' +
                '        <th>Предполагаемая дата окончания</th>\n' +
                '        <th>Реальная дата окончания</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';
            for (let i = 0; i < projects.length; i++) {
                let project = projects[i];
                html = html + '<tr><td>' + project.id + '</td>\n' +
                    '        <td>' + project.name + '</td>\n' +
                    '        <td>' + project.cost + '</td>\n' +
                    '        <td>' + project.department.name + '</td>\n' +
                    '        <td>' + project.dateBeg + '</td>' +
                    '        <td>' + project.dateEnd + '</td>' +
                    '        <td>' + project.dateEndReal + '</td>' +
                    '        <td><button onclick="deleteProject(' + project.id + ')">Удалить</button></td></tr>';
            }
            document.getElementById("projects").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/projects/list", true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function signOut() {
    localStorage.clear();
    window.location.href = "index.html";
}

loadProjects();