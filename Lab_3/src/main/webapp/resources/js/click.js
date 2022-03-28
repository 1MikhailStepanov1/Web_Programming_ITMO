function shot(event){
    let x = (Math.round(event.pageX - $('#svg').offset().left - 200) +1) /30
    let y = (Math.round(event.pageY - $('#svg').offset().top -200) * (-1)) /30
    document.forms[2].elements[1].value = x
    document.forms[2].elements[2].value = y
    document.forms[2].elements[3].click()
}