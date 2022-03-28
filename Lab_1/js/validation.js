function validation() {
    let resultY = ""
    let resultR = ""
    let y = $('#y_value').val()
    let r = $('#r_value').val()
    resultY += validateY(y)
    resultR = validateR(r)

    $('#error_y').html(resultY)
    $('#error_r').html(resultR)

    if (resultY === "" && resultR === "") return true
    else {
        return false
    }
}

function validateY(field) {
    if (field !== "") {
        if (/^(0$|-?[1-9]\d*(\.\d*[1-9]$)?|-?0\.\d*[1-9])$/.test(field)) {
            if (eval(field) >= -5 && eval(field) <= 5) {
                return ""
            } else return "Поле Y должно быть в пределах (-5..5)."
        } else return "Поле Y должно содержать только число.\n"
    } else return "Не введен Y.\n"
}


function validateR(field) {
    if (field !== "") {
        if (/^(0$|-?[1-9]\d*(\.\d*[1-9]$)?|-?0\.\d*[1-9])$/.test(field) ) {
            if(eval(field) >= 2 && eval(field) <= 5){
                return ""
            } else return "Поле R должно быть в пределах (2..5)."
        } else return "Поле R должно содержать только число.\n"
    } else return "Не введен R.\n"
}