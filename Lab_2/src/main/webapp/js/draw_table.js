function add_row(data) {
    let row = $('<tr>');
    $('<td>').html(data.x).appendTo(row);
    $('<td>').html(data.y).appendTo(row);
    $('<td>').html(data.r).appendTo(row);
    $('<td>').html(data.currentTime).appendTo(row);
    $('<td>').html(data.duration).appendTo(row);
    $('<td>').html(data.result).appendTo(row);
    $(row).insertAfter($('table tr:last').eq(0));
}
