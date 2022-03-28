function add_row(data) {
    let row = $('<tr>');
    for (let i in data) {
        $('<td>').html(data[i]).appendTo(row);
    }
    $(row).insertAfter($('table tr:last').eq(0));
}
