/*$(document).ready(function() {


});


*/
/*$(' .container .form-signin #validate').on('click', function (event) {

        var res;
        $.ajax({
            type: "POST", //GET, POST, PUT
            url: '/loginUser',  //the url to call
            data: {
                userName: $("#inputEmail").val(),
                password: $("#inputPassword").val()
            },     //Data sent to server
            dataType: "json",
            success:function (res) {
                window.localStorage.setItem("token", "Haha");
            }
        })
    });
    console.log(localStorage.getItem("token"));
*/

/*



$(document).ready(function() {

    $(' .container #myform #validate').on('click', function (event) {

        let resultat = $.ajax({
            type: "POST",
            url: '/loginUser',
            data: {
                userName: $("#inputEmail").val(),
                password: $("#inputPassword").val()
            },
            .done(function (data, textStatus, xhr) {
            console.log(xhr.getResponseHeader('Link'));
        });
        //this is mandatory other wise your from will be submitted.
        return false;
    });

});


 */
