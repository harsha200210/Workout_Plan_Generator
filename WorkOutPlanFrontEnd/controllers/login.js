import {setAlert} from "../util/alert.js";

$("#loginBtn").click(function() {
    let user = {
        email: $("#email").val().trim(),
        password: $("#password").val().trim()
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/auth/authenticate",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function (response) {
            if (response.statusCode === 201) {
                localStorage.setItem("authToken", response.data.token);
                localStorage.setItem("loginIn", "true");
                if (response.message === "USER") {
                    window.location.href = "index.html";
                } else {
                    window.location.href = "adminDashboard.html";
                }
                setAlert("success","Login Successful");
            } else {
                alert(response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error("Error:", error);
        }
    })
});