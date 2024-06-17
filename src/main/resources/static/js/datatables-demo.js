// Call the dataTables jQuery plugin
$(document).ready(function() {

  const username = localStorage.getItem("username");

  if (!username) {
    window.location.replace("/login");
  }

  $("#username").text(username);

  const token = localStorage.getItem("token");

  $.ajax({
    url: '/report/summary', // The URL to the API endpoint
    type: 'GET',
    dataType: 'json',
    headers: {
      "Authorization": "Bearer "+token,
      "Content-Type": "application/json"
    },
    success: function(json) {
      const dataSet = json.data.map((v, k) => ({
        ...v,
        index: k,
        lob: v.causeOfClaim === null ? `Subtotal ${v.lob}` : v.lob
      }));
      $('#dataTable').DataTable({
        data: dataSet,
        columns: [
          { data: "lob" },
          { data: "causeOfClaim" },
          { data: "totalClaimsBurden"},
          { data: "totalCount" },
          { data: "index", "visible": false }
        ],
        "order": [[4, 'asc']],
        createdRow: function(row, data, dataIndex) {
              if (data.causeOfClaim === null) {
                  $(row).addClass('row-blue');
              }
              if (data.causeOfClaim === "") {
                  $(row).addClass('row-black');
              }
          }
      });
    },
    error: function(error) {
      console.error('Error:', error);
    }
  });

  
});
