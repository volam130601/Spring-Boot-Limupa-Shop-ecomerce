//Checked All
$("#checkAll").click(function () {
    $('input:checkbox').not(this).prop('checked', this.checked);
});

//Delete One
function showConfirmModalDialog(id, name , urlName) {
    $('#deleteName').text(name);
    $('#yesOption').attr('href', '/admin/'+urlName+'/delete/' + id);
    $('#deleteModalId').modal('show')
}

//Delete many
function showDeleteAllModal(urlName) {
    var data = [];
    //Get array checked 'id' checkbox
    $("input:checkbox[name=checkOne]:checked").each(function () {
        data.push($(this).attr("id"));
    });
    $('#yesOptions').attr('href', '/admin/'+urlName+'/delete?ids=' + data);
    $('#deleteModalId_2').modal('show')
}
