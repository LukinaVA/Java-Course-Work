function signIn() {
    let username = document.getElementById("userName").value;
    let pass = document.getElementById("password").value;

    fetch("http://localhost:8080/auth/signIn", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userName: username,
            password: pass
        })
    })
        .then( (response) => {
            if(response.status >= 400){
                return Promise.reject();
            }
            return response.json();
        }).then( (j) => JSON.stringify(j))
        .then( (data) => JSON.parse(data))
        .then( (obj) => localStorage.setItem("Authentication", obj["token"]))
        .then( () => window.location.href = "menu.html" )
        .catch( () => alert("Пользователь не найден. Пожалуйста зарегестрируйтесь."));
}