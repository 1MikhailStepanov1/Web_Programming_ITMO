function validation() {
    let resultX = ""
    let resultR = ""
    let resultY = ""
    let x = $('#x_value').val()
    let r = $('#r_value').val()
    let y = $('input[name="y_value"]:disabled').val()
    resultX = validateX(x)
    resultR = validateR(r)
    resultY = validateY(y)
    $('#error_x').html(resultX)
    $('#error_r').html(resultR)
    $('#error_y').html(resultY)

    if (resultX === "" && resultR === "" && resultY === "") return true
    else {
        return false
    }
}

function validateX(field) {
    if (field !== "") {
        if (/^(0$|-?[1-9]\d*(\.\d*[1-9]$)?|-?0\.\d*[1-9])$/.test(field)) {
            if (eval(field) >= -5 && eval(field) <= 5) {
                return ""
            } else return "Поле X должно быть в пределах (-5..5)."
        } else return "Поле X должно содержать только число.\n"
    } else return "Не введен X.\n"
}

function validateY(field){
    if (field !== undefined){
        return ""
    } else return "Не введен Y.\n"
}
function validateR(field) {
    if (field !== null) {
        return ""
    } else return "Не введен R.\n"
}