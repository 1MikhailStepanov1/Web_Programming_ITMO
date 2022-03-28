function draw_shot(data){
    const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
    circle.setAttribute('r', '3');
    circle.setAttribute('cx', 200+150 / data.r * data.x);
    circle.setAttribute('cy', 200-150 / data.r * data.y);
    if (data.result === "TRUE"){
        circle.setAttribute('fill', 'green');
    } else circle.setAttribute('fill', 'red');
    document.getElementById('svg').appendChild(circle);
}