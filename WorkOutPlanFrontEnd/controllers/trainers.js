import {setAlert} from "../util/alert.js";

export let getAllTrainers = function () {
    $.ajax({
        url: "http://localhost:8080/api/v1/trainers/all",
        method: "GET",
        headers : {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function(response) {
            if (response.statusCode === 200) {
                $("#tblBody").html("");
                response.data.forEach(function (value, index) {
                    $("#tblBody").append(`<tr>
                        <td>${value.name}</td>
                        <td>${value.location}</td>
                        <td>${value.experience}</td>
                        <td>${value.tel}</td>
                        <td><a href="mailto:${value.email}">${value.email}</a></td>
                    </tr>`);
                });
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr, status, error);
        }
    })
}

getAllTrainers();

$("#saveTrainer").click(function () {
    let trainer = {
        name: $("#nameForTrainer").val(),
        location: $("#locationForTrainer").val(),
        experience: $("#ExperienceForTrainer").val(),
        tel: $("#telForTrainer").val(),
        email: $("#emailForTrainer").val()
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/trainers/save",
        method: "POST",
        headers : {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        data: JSON.stringify(trainer),
        contentType: "application/json",
        success: function(response) {
            if (response.statusCode === 201) {
                $("#staticBackdrop").modal("hide");
                getAllTrainers();
                setAlert("success" , "Save Trainer Successfully!!")
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr, status, error);
            setAlert("error", "Failed to Save Trainer!!")
        }
    })
})