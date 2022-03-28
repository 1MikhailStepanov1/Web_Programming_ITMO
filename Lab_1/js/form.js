$('form').on('submit', function (event) {
    event.preventDefault()
    if (validation()) {
        $.ajax({
            url: 'input.php',
            type: 'POST',
            cache: false,
            dataType: "json",
            data:
                "x_value=" + document.querySelector('input[name="x_value"]:checked').value +
                "&y_value=" + document.getElementById("y_value").value +
                "&r_value=" + document.getElementById("r_value").value,
        }).done(add_row)
            .fail(processError)
    }
})

function processError(xhr, status, errorThrown) {
    alert("Sorry, there was a problem!");
    console.log("Error: " + errorThrown);
    console.log("Status: " + status);
    console.dir(xhr);
}

function clean_errors() {
    $('#error_y').html("")
    $('#error_r').html("")
}

window.onload = function () {
    document.querySelector('.form').onchange = function () {
        let y = $('#y_value').val();
        let x = $('input[name="x_value"]:checked').val();
        let r = $('#r_value').val();
        let dot = $('#dot');
        dot.attr('r', '3px');
        dot.attr('cx', 200 + 150 / r * x);
        dot.attr('cy', 200 - 150 / r * y);
        if (y === "" && r === ""){
            dot.attr('r', '0px');
        }
    }
}






