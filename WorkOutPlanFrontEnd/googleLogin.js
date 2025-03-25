import { initializeApp } from "https://www.gstatic.com/firebasejs/11.4.0/firebase-app.js";
import { getAuth, GoogleAuthProvider, signInWithPopup, signOut } from "https://www.gstatic.com/firebasejs/11.4.0/firebase-auth.js";

// 🔹 Replace with your Firebase Config
const firebaseConfig = {
    apiKey: "AIzaSyApOy-5mOfaw8IzkUVVjAxdbH2f7NdySn0",
    authDomain: "workplanproject-d1354.firebaseapp.com",
    projectId: "workplanproject-d1354",
    storageBucket: "workplanproject-d1354.appspot.com",
    messagingSenderId: "939344284763",
    appId: "1:939344284763:web:0f02a2d44ca1e124c6c37e"
};

// 🔹 Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new GoogleAuthProvider();

// 🔹 Google Login Function
$("#googleLoginBtn").click(function () {
    signInWithPopup(auth, provider)
        .then((result) => {
            const user = result.user;
            console.log("User Info:", user);

            localStorage.setItem("user", JSON.stringify({
                name: user.displayName,
                email: user.email,
                photo: user.photoURL
            }));

            let user1 = {
                email: user.email,
                fullName: user.displayName
            }

            $.ajax({
                url: "http://localhost:8080/api/v1/auth/google-login",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(user1),
                success: function (response) {
                    if (response.statusCode === 201) {
                        localStorage.setItem("authToken", response.data.token);
                        localStorage.setItem("loginIn", "true");
                        if (response.message === "USER") {
                            window.location.href = "index.html";
                        } else {
                            window.location.href = "adminDashboard.html";
                        }
                    } else {
                        alert(response.message);
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error:", error);
                }
            })

        })
        .catch((error) => {
            console.error(error.message);
        });
});

$('#logoutBtn').click(() => {
    signOut(auth)
        .then(() => {
            localStorage.removeItem("user");
            localStorage.removeItem("loginIn");
            localStorage.removeItem("authToken");
            window.location.href = "index.html";
        })
        .catch((error) => {
            console.error(error.message);
        });
})