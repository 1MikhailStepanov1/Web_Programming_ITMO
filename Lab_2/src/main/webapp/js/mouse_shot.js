function mouse_shot(event){
    let r_value = $('#r_value').val();
    let coordX;
    let coordY;
    let x_val;
    let y_val;

    if (r_value !== null) {
        coordX = event.pageX - 194;
        coordY = event.pageY - 100;
        x_val = (coordX - 200) / 150 * r_value;
        y_val = (200 - coordY) / 150 * r_value;
        request(x_val, y_val, r_value);
    } else $('#error_r').html("Не введен R.\n")
}