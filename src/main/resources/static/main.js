
$(document).ready(function () {

    $('.table #eBtn').on('click', function (event) {

        console.log("OKAY")
        $('.myForm #editModal').modal(function () {
            console.log("VERY GOOD!")
        });
    })
})

