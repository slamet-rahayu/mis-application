$(document).ready(function() {

  const username = localStorage.getItem("username");

  if (!username) {
    window.location.replace("/login");
  }

  const token = localStorage.getItem("token");

  $("#formUploadData").submit(function(event) {
    event.preventDefault();

    $('#submitText').addClass("none");
    $('#loading').removeClass("none");
    
    const formData = new FormData($(this)[0]);
    
    $.ajax({
      url: '/report/upload-excel', // URL where you want to submit the form data
      type: 'POST', // HTTP method used
      headers: {
        "Authorization": "Bearer "+token,
      },
      data: formData, // Form data
      processData: false, // Prevent jQuery from automatically processing the data
      contentType: false, // Prevent jQuery from setting content type
      success: function(response) { // Handle success response
        $('#alertSuccess').removeClass("none");
        $('#submitText').removeClass("none");
        $('#loading').addClass("none");
        setTimeout(() => {
          $('#alertSuccess').addClass("none"); // Display server response
        }, 3000);
      },
      error: function(error) { // Handle error response
        $('#alertError').removeClass("none");
        $('#submitText').removeClass("none");
        $('#loading').addClass("none");
        setTimeout(() => {
          $('#alertError').addClass("none"); // Display server response
        }, 3000);
      }
    });
  });
});