window.onload = function (){
    const buttonsX = document.querySelectorAll('#x>label>input')
    $('#x>label>input').on('click', function (){
        for (let i =0 , max = buttonsX.length; i < max; i++){
            buttonsX[i].setAttribute('class', 'unclicked')
        }
        $(this).attr('class', 'clicked')
    }).hover(
        function () {
            if (this.getAttribute('class') !== 'clicked'){
                $(this).attr('class', 'hovered')
            }
        },
        function (){
            for (let i =0 , max = buttonsX.length; i < max; i++){
                if (buttonsX[i].getAttribute('class') !== 'clicked'){
                    buttonsX[i].setAttribute('class', 'unclicked')
                }
            }
        }
    )
}