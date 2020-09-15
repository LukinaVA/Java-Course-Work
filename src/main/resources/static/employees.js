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
            let employee = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Имя</th>\n' +
                '        <th>Отчество</th>\n' +
                '        <th>Фамилия</th>\n' +
                '        <th>Должность</th>\n' +
                '        <th>Зарплата</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';

            html = html + '<tr><td>' + employee.id + '</td>\n' +
                '        <td>' + employee.firstName + '</td>\n' +
                '        <td>' + employee.patherName + '</td>\n' +
                '        <td>' + employee.lastName + '</td>' +
                '        <td>' + employee.position + '</td>' +
                '        <td>' + employee.salary + '</td>' +
                '        <td><button onclick="deleteEmployee(' + employee.id + ')">Удалить</button></td></tr>';

            document.getElementById("employees").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/employees/list/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function deleteEmployee(id) {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status >= 400) {
                alert("Отказ в доступе!");
            }
            window.location.reload();
        }
    };

    xhttp.open("DELETE", "http://localhost:8080/employees/delete/" + id, true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function addEmployee() {
    let firstName = document.getElementById("firstName").value;
    let patherName = document.getElementById("patherName").value;
    let lastName = document.getElementById("lastName").value;
    let position = document.getElementById("position").value;
    let salary = document.getElementById("salary").value;

    let department = document.getElementById("department").value;

    let xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status >= 400) {
                alert("Отказ в доступе!");
            }
        }
    };

    xmlhttp.open("POST", "http://localhost:8080/employees/addEmployee");
    xmlhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({firstName: firstName, patherName: patherName, lastName: lastName,
        position: position, salary: salary, department: department}));

    loadEmployees();
}

function loadEmployees() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let employees = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>ID</th>\n' +
                '        <th>Имя</th>\n' +
                '        <th>Отчество</th>\n' +
                '        <th>Фамилия</th>\n' +
                '        <th>Должность</th>\n' +
                '        <th>Зарплата</th>\n' +
                '        <th>Удалить</th>\n' +
                '    </tr>';
            for (let i = 0; i < employees.length; i++) {
                let employee = employees[i];
                html = html + '<tr><td>' + employee.id + '</td>\n' +
                    '        <td>' + employee.firstName + '</td>\n' +
                    '        <td>' + employee.patherName + '</td>\n' +
                    '        <td>' + employee.lastName + '</td>' +
                    '        <td>' + employee.position + '</td>' +
                    '        <td>' + employee.salary + '</td>' +
                    '        <td><button onclick="deleteEmployee(' + employee.id + ')">Удалить</button></td></tr>';

            }
            document.getElementById("employees").innerHTML = html;
        }
    };

    xhttp.open("GET", "http://localhost:8080/employees/list", true);
    xhttp.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("Authentication"));
    xhttp.send();
}

function signOut() {
    localStorage.clear();
    window.location.href = "index.html";
}

loadEmployees();