$(document).ready(function() {
  $("#formLogin").submit(function(event) {
    event.preventDefault();

    const username = $("#username").val();
    const password = $("#password").val();

    $('#submitText').addClass("none");
    $('#loading').removeClass("none");

    $.ajax({
      url: '/auth/login', // URL where you want to submit the form data
      type: 'POST', // HTTP method used
      contentType: "application/json",
      data: JSON.stringify({
        username,
        password
      }),
      processData: false, // Prevent jQuery from automatically processing the data
      success: function(response) { // Handle success response
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("username", response.data.username);
        $('#submitText').removeClass("none");
        $('#loading').addClass("none");
        window.location.replace("/");
      },
      error: function(error) { // Handle error response
       console.log(error.responseJSON);
       $('#submitText').removeClass("none");
       $('#loading').addClass("none");
       $('#alertError').removeClass("none");
       setTimeout(() => {
        $('#alertError').addClass("none"); // Display server response
      }, 3000);
      }
    });
  });

});