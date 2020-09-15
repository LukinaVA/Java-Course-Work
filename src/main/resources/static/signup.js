function signUp() {
    let username = document.getElementById("userName").value;
    let pass = document.getElementById("password").value;

    fetch("http://localhost:8080/auth/signUp", {
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
                console.log("User with this nickname exists");
                return Promise.reject();
            }
            return response.json();
        }).then( () => alert("Регистрация прошла успешно. Можете авторизироваться."))
        .then( () => localStorage.clear() )
        .then( () => window.location.href = "signin.html" )
        .catch( () => alert("Вы уже зарегестрированы!"));
}