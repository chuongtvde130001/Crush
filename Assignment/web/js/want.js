let want_list = [];
let cw;

function pass(){
    cw = want_list.shift();
    console.log(cw);
    $(".profile-card__name").eq(0).text(cw[1]);
    $(".profile-card-inf__txt").eq(0).text(cw[2]);
    $(".profile-card-inf__txt").eq(1).text(cw[3]);
    $(".profile-card-inf__txt").eq(2).text(cw[4]);
    $(".profile-card__img").find("img").attr("src",cw[5]);
    $(".profile-card__txt").eq(0).text(cw[6]);
}
function crush() {
    let x = document.getElementById('crush_button');
    if (x.value === 'Crush') {
        Swal.fire({
            title: 'You are crushing '+cw[1],
            width: 600,
            padding: '3em',
            backdrop: `
                rgba(0,0,123,0.4)
                url("img/heart.gif")
                center top
                no-repeat`
        })
        x.value = "Uncrush";
    } else if (x.value === 'Uncrush') {
        Swal.fire({
            title: 'Are you sure to uncrush '+cw[1],
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, uncrush!'
        }).then((result) => {
            if (result.value) {
                Swal.fire(
                    'You uncrushed!'+cw[1]
                )
                x.value = "Crush";
            }
        })
    }
}

$( document ).ready(function() {
    $('#pass_button').click(pass);
    $('#crush_button').click(crush);
    pass();
});