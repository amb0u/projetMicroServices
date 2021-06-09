$(document).ready(function () {

    //Modal for adding an agence  !
    $('.card .card-body #addBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $('#myModal').modal();
    })

});
