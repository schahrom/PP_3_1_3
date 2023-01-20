
$(document).ready(function () {

    $('.table .editBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href')


         $.get(href, function (user) {
           $('.myForm #id').val(user.id);
           $('.myForm #username').val(user.username);
           $('.myForm #lastName').val(user.lastName);
           $('.myForm #age').val(user.age);
           $('.myForm #email').val(user.email);
           $('.myForm #password').val(user.password);
           $('.myForm #role').val(user.roles[0].name);
    })

        $('#editModal').modal();

        $('.table .deleteBtn').on('click', function () {

            $.get(href, function (user) {
                $('.myDeleteForm #dId').val(user.id);
                $('.myDeleteForm #dUsername').val(user.username);
                $('.myDeleteForm #dLastName').val(user.lastName);
                $('.myDeleteForm #dAge').val(user.age);
                $('.myDeleteForm #dEmail').val(user.email);
                $('.myDeleteForm #dPassword').val(user.password);
                $('.myDeleteForm #dRole').val(user.roles[0].name);
                $('.myDeleteForm #delRef').attr('href', 'http://localhost:8080/admin/delete/'+user.id);
            })

            $('#deleteModal').modal();
        });
    });
});
