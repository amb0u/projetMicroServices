$(document).ready(function () {

    //Modal for deleting an agence ; getting the confirmation !
    $(' .table100 #delBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    })

});
