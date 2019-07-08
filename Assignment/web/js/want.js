'use strict'

let want_list = [];
let cw,cSocket;

$(document).ready(function() {
    $('#pass_button').click(passBut);
    $('#crush_button').click(crushBut);
    passBut();
});

function isWListEmpty() {
    return want_list.length==0;
}

function passBut(){
    if(isWListEmpty()) {
        $("#findCrush").hide();
        return;
    }
    cw = want_list.shift();
    $(".profile-card__name").eq(0).text(cw[1]);
    $(".profile-card-inf__txt").eq(0).text(cw[2]);
    $(".profile-card-inf__txt").eq(1).text(cw[3]);
    $(".profile-card-inf__txt").eq(2).text(cw[4]);
    $(".profile-card__img").find("img").attr("src",cw[5]);
    $(".profile-card__txt").eq(0).text((cw[6]=="") ? "⠀":cw[6]);
}
function crushBut() {
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
        crush(uid,cw[0]);
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
                uncrush(uid,cw[0])
                Swal.fire(
                    'You uncrushed!'+cw[1]
                )
                x.value = "Crush";
            }
        })
    }
}

function crush(id, target) {
    cSocket = new WebSocket(WS_URL+"/crush");
    let getRequest = {
        'crush':true,
        'uid': id,
        'target': target
    };
    cSocket.onopen = function () {
        cSocket.send(JSON.stringify(getRequest));
    }
    cSocket.onmessage = function (evt) {
        console.log("RESULT: "+evt.data);
        cSocket.close();
    }
}

function uncrush(id, target){
    cSocket = new WebSocket(WS_URL+"/crush");
    let getRequest = {
        'crush':false,
        'uid': id,
        'target': target
    };
    cSocket.onopen = function () {
        cSocket.send(JSON.stringify(getRequest));
    }
    cSocket.onmessage = function (evt) {
        console.log("RESULT: "+evt.data);
        cSocket.close();
    }
}

function remove(id) {
    $('#list-group-item-'+id).remove();
}