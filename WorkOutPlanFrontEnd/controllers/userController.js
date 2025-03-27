import {setAlert} from "../util/alert.js";
import {EMAIL, NAME} from "../util/regex.js";

$("#signup-btn").click(function () {
  if (validationInputs()) {
    let user = {
      fullName: $("#fullName").val(),
      gender: $("#gender").val(),
      email: $("#email").val(),
      password: $("#password").val(),
      country: $("#country").val(),
      role: "USER"
    }
    $.ajax({
      url: "http://localhost:8080/api/v1/user/register",
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(user),
      success: function (response) {
        if (response.statusCode === 201) {
          setAlert("success","Account created successfully!");
          localStorage.setItem("authToken", response.data.token);
          localStorage.setItem("loginIn", "true");
          window.location.href = "index.html";
        } else {
          setAlert("error" , response.message);
        }
      },
      error: function (xhr, status, error) {
        console.error(xhr.responseText);
        setAlert("error","An error occurred while creating the account. Please try again later.");
      }
    })
  }
});

let validationInputs = () => {
  if (NAME.test($("#fullName").val())) {
    if ($("#gender").val().trim() !== "") {
      if (EMAIL.test($("#email").val())) {
        if ($("#password").val().trim() !== "") {
          if ($("#country").val().trim() !== "") {
            return true;
          } else {
            setAlert("error", "Please select a country !!")
          }
        } else {
          setAlert("error", "Please enter a password !!")
        }
      } else {
        setAlert("error", "Please enter a valid email address !!")
      }
    } else {
      setAlert("error" , "Please enter a gender !!")
    }
  } else {
    setAlert("error" , "Please enter a name !!")
  }
  return false;
}
