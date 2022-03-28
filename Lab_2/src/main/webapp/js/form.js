$('form').on('submit', function (event) {
    event.preventDefault()
    if (validation()) {
        $.ajax({
            url: './controller',
            type: 'POST',
            cache: false,
            dataType: "json",
            data:
                "x_value=" + document.getElementById("x_value").value +
                "&y_value=" + document.querySelector('input[name="y_value"]:disabled').value +
                "&r_value=" + document.getElementById("r_value").value,
            success: function (data){
                console.log(data)
                add_row(data)
                draw_shot(data)
            }
        })

    }
})

function request(x, y, r){
    $.ajax({
        url: './controller',
        type: 'POST',
        cache: false,
        dataType: "json",
        data:
            "x_value=" + x +
            "&y_value=" + y +
            "&r_value=" + r,
        success: function (data){
            console.log(data)
            add_row(data)
            draw_shot(data)
        }
    })
}

function processError(xhr, status, errorThrown) {
    alert("Sorry, there was a problem!");
    alert("Error: " + errorThrown);
    alert("Status: " + status);
    console.dir(xhr);
}

function clean_errors() {
    const buttons = document.querySelectorAll('input[name="y_value"]')
    $('#error_x').html("")
    $('#error_r').html("")
    $('#error_y').html("")
    for (let i = 0, max = buttons.length; i<max; i++){
        buttons[i].disabled = false
    }
}

window.onload = function () {
    const buttons = document.querySelectorAll('input[name="y_value"]')
    $(':button').on('click', function () {
            $('#error_y').html("")
            for (let i = 0, max = buttons.length; i<max; i++){
                buttons[i].disabled = false
            }
            $(this).attr("disabled", "true")
        }
    )
    $('#r_value').on('change', function (){
            $('#error_r').html("")
        }
    )
}











