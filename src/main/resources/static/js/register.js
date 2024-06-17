$(document).ready(function() {
  $("#formRegister").submit(function(event) {
    event.preventDefault();

    const username = $("#username").val();
    const password = $("#password").val();

    $.ajax({
      url: '/user/register', // URL where you want to submit the form data
      type: 'POST', // HTTP method used
      contentType: "application/json",
      data: JSON.stringify({
        username,
        password
      }),
      processData: false, // Prevent jQuery from automatically processing the data
      success: function(response) { // Handle success response
        $('#submitText').removeClass("none");
        $('#loading').addClass("none");
        $('#alertSuccess').removeClass("none");
        setTimeout(() => {
          window.location.replace("/login");
        }, 3000);
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