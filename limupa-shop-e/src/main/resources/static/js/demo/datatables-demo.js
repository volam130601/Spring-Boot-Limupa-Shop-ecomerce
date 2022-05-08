// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#productTable').DataTable({
    //check box first child
    columnDefs: [{
        orderable: false,
        targets: [0, 8],
    	},
    	{ "width": "14%", "targets": [1,8] },
    ],
    order: [[ 7, 'asc' ]],
    
  });
  
  $('#categoryTable').DataTable({
    //check box first child
    columnDefs: [ {
        orderable: false,
        targets: [0, 3]
    } ],
    order: [[ 1, 'asc' ]]
  });
  
  $('#userTable').DataTable({
    columnDefs: [ {
        orderable: false,
        targets: [0, 6]
    } ],
    order: [[ 1, 'asc' ]]
  });
  
  $('#roleTable').DataTable({
    columnDefs: [ {
        orderable: false,
        targets: [0, 3]
    } ],
    order: [[ 1, 'asc' ]]
  });
  
});
