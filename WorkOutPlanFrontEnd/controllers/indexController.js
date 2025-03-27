import {setAlert} from "../util/alert.js";

document.addEventListener("DOMContentLoaded", function() {
    let myCarousel = new bootstrap.Carousel(document.querySelector("#heroCarousel"), {
        interval: 5000, // 5 seconds per slide
        ride: "carousel"
    });
});

let loginBtn = $("#loginBtn");
let signupBtn = $("#signupBtn");
let logoutBtn = $("#logoutBtn");
let content1 = $("#content1");
let content2 = $("#content2").hide();

let loginOrNot = function(){
    if (localStorage.getItem("loginIn") === "true"){
        loginBtn.hide();
        signupBtn.hide();
        logoutBtn.show();
    } else {
        logoutBtn.hide();
        loginBtn.show();
        signupBtn.show();
    }
}
loginOrNot();

$(".startBtn").click(function () {
    if (localStorage.getItem("loginIn") !== "true"){
        setAlert("error", "Login First !!")
    }
    if (localStorage.getItem("loginIn") === "true"){
        let token = localStorage.getItem("authToken");

        $.ajax({
            url: "http://localhost:8080/api/v1/auth/planCount",
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function (response) {
                if (response.statusCode === 200){
                    if (response.data <= 1) {
                        window.location.href = "firstPlan.html";
                    } else if (response.data === 2) {
                        window.location.href = "2DayPlan.html";
                    } else if(response.data === 3) {
                        window.location.href = "3DayPlan.html";
                    } else {
                        window.location.href = "newPlan.html";
                    }
                }
            },
            error: function (error) {
                console.error(error);
            }
        })
    }
});

let loadWeightCount = function () {
    var ctx = $("#myChart")[0].getContext("2d");

    $.ajax({
        url: "http://localhost:8080/api/v1/weightCounts/getWeight",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (response) {
            if (response.statusCode === 200){
                let weightCounts = [];
                let dates = [];

                response.data.forEach(function (weightCount) {
                    weightCounts.push(weightCount.weight);
                    dates.push(new Date(weightCount.date).toLocaleDateString());
                });

                dates.reverse();
                weightCounts.reverse();

                new Chart(ctx, {
                    type: "line",
                    data: {
                        labels: dates,
                        datasets: [
                            {
                                label: "Weights",
                                data: weightCounts,
                                borderColor: "#dc3545", // Red color
                                backgroundColor: "rgba(220, 53, 69, 0.1)",
                                borderWidth: 2,
                                fill: true,
                                tension: 0.4
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            x: { title: { display: true, text: "Dates" } },
                            y: { title: { display: true, text: "Weight (kg)" }, beginAtZero: true }
                        },
                        plugins: {
                            legend: { display: true, position: "top" }
                        }
                    }
                });
            }
        },
        error: function (error) {
            console.error(error);
        }
    })
}
loadWeightCount();

let loadBmiCounts = function () {
    var ctx = $("#myChart1")[0].getContext("2d");

    $.ajax({
        url: "http://localhost:8080/api/v1/bmi/getBmiCounts",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (response) {
            if (response.statusCode === 200){
                let bmiCounts = [];
                let dates = [];

                response.data.forEach(function (bmiCount) {
                    bmiCounts.push(bmiCount.bmi);
                    dates.push(new Date(bmiCount.date).toLocaleDateString());
                });

                dates.reverse();
                bmiCounts.reverse();

                // Register the custom plugin
                Chart.register({
                    id: "backgroundColor",
                    beforeDraw: (chart) => {
                        if (chart.canvas.id !== "myChart1") return;

                        const { ctx, chartArea, scales } = chart;
                        if (!chartArea) return; // Prevent issues when chart is not ready

                        const { top, bottom, left, right } = chartArea;
                        const { y } = scales;

                        const ranges = [
                            { min: 0, max: 18.5, color: "rgba(255, 255, 0, 0.3)" }, // Yellow for Underweight
                            { min: 18.5, max: 24.9, color: "rgba(0, 255, 0, 0.3)" }, // Green for Normal
                            { min: 25, max: 29.9, color: "rgba(255, 165, 0, 0.3)" }, // Orange for Overweight
                            { min: 30, max: 50, color: "rgb(243,68,68)" } // Red for Obese
                        ];

                        ranges.forEach(({ min, max, color }) => {
                            ctx.fillStyle = color;
                            ctx.fillRect(left, y.getPixelForValue(max), right - left, y.getPixelForValue(min) - y.getPixelForValue(max));
                        });
                    }
                });

                new Chart(ctx, {
                    type: "line",
                    data: {
                        labels: dates,
                        datasets: [
                            {
                                label: "BMI",
                                data: bmiCounts,
                                borderColor: "#007bff", // Blue color
                                backgroundColor: "rgba(0, 123, 255, 0.1)",
                                borderWidth: 2,
                                fill: true,
                                tension: 0.4
                            }
                        ]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        scales: {
                            x: { title: { display: true, text: "Dates" } },
                            y: {
                                title: { display: true, text: "BMI" },
                                beginAtZero: true
                            }
                        }
                    }
                });

            }
        },
        error: function (error) {
            console.error(error);
        }
    })
}
loadBmiCounts();

let today = new Date();
let formattedDate = today.toISOString().split('T')[0];

$("#weightSaveBtn").click(function () {
  if (validationWeight()) {
    let nowWeight;
    let weight = $("#weightInput").val().trim();
    if ($("#unit").text().trim() === "kg"){
      nowWeight = parseFloat(weight)
    } else {
      nowWeight = parseFloat(weight) * 0.453592;
    }

    let weightCount = {
      weight : nowWeight,
      date : formattedDate
    }

    $.ajax({
      url: "http://localhost:8080/api/v1/weightCounts/saveWeight",
      method: "POST",
      contentType: "application/json",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("authToken"),
      },
      data: JSON.stringify(weightCount),
      success: function (response) {
        if (response.statusCode === 201){
          $("#staticBackdrop").modal("hide");
          setAlert("success", "Add Weight Successfully")
          loadWeightCount();
        }
      },
      error: function (error) {
        console.error(error);
      }
    })
  }
});

let validationWeight = () => {
  if (/^\d*\.?\d+$/.test($("#weightInput").val())) {
   return true;
  } else {
    setAlert("error" ,"Please enter a valid weight!");
  }
  return false;
}

$("#setBmi").click(function () {
    content1.show();
    content2.hide();

    $(".toggle-btn button").removeClass("active");
    $("#kg-btn1").addClass("active");
    $("#cm-btn").addClass("active");
})

$("#saveBmi").click(function () {
  if(validationBmi()) {
    let nowWeight;
    let weight = $("#weightInput1").val().trim();
    if ($("#unit1").text().trim() === "kg"){
      nowWeight = parseFloat(weight)
    } else {
      nowWeight = parseFloat(weight) * 0.453592;
    }

    let nowHeight;
    let height = $("#heightInput").val().trim();
    if ($("#unit2").text().trim() === "cm"){
      nowHeight = parseFloat(height) / 100;
    } else {
      nowHeight = parseFloat(height) * 0.3048;
    }

    let bmi = nowWeight / Math.pow(nowHeight, 2);

    let bmiCount = {
      bmi : bmi,
      date : formattedDate
    }

    $.ajax({
      url: "http://localhost:8080/api/v1/bmi/saveBmiCounts",
      method: "POST",
      contentType: "application/json",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("authToken"),
      },
      data: JSON.stringify(bmiCount),
      success: function (response) {
        if (response.statusCode === 201){
          $("#staticBackdrop1").modal("hide");
          setAlert("success", "Add BMI Successfully")
          loadBmiCounts();
        }
      },
      error: function (error) {
        console.error(error);
      }
    })
  }
});

let validationBmi = () => {
  if (/^\d*\.?\d+$/.test($("#heightInput").val())) {
    return true;
  } else {
    setAlert("error","Please enter a valid height!");
  }
  return false;
}
