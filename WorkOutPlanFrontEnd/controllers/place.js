import {setAlert} from "../util/alert.js";

export let getAllPlaces = function () {
    $.ajax({
        url: "http://localhost:8080/api/v1/places/all",
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

getAllPlaces();

$("#savePlace").click(function () {
    let place = {
        name: $("#nameForPlace").val(),
        location: $("#locationForPlace").val(),
        tel: $("#telForPlace").val(),
        email: $("#emailForPlace").val()
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/places/save",
        method: "POST",
        headers : {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        data: JSON.stringify(place),
        contentType: "application/json",
        success: function(response) {
            if (response.statusCode === 201) {
                $("#staticBackdrop").modal("hide");
                getAllPlaces();
                setAlert("success" , "Save Place Successfully!!")
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr, status, error);
            setAlert("error", "Failed to Save Place!!")
        }
    })
})