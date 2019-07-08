let notiOpen = false;
let notiCount = 0;

let notiDot = $('.noti-dot');

let nSocket;
$(document).ready(function() {
    $('.dd-trigger').click(openNoti);
    getNoti();
});
// $(window).click(function(event) {
//    if(notiDot.has(event.target).length==0){
//    	$('#notification-dropdown').hide();
//    	notiOpen=false;
//    }
// });

function openNoti(){
	if(notiOpen){
		$('#notification-dropdown').hide();
		$('#dd-notifications-count').text('');
		notiOpen = false;
	}else{
		$('#notification-dropdown').show();
		$('#dd-notifications-count').text(notiCount);
		$('#notifications-count').text('');
		$('#notifications-count').hide();
		notiOpen = true;
		notiCount = 0;
	}
}

function getNoti() {
    // if (nSocket!=null && nSocket.readyState === WebSocket.OPEN)
    //     nSocket.close();
    console.log(":::GET NOTIFICATION");
    nSocket = new WebSocket(WS_URL+"/getNoti");
    let getRequest = {
        'uid': uid,
    };
    nSocket.onopen = function () {
        nSocket.send(JSON.stringify(getRequest));
    }
    nSocket.onmessage = function (evt) {
        console.log("GET NOTI RESULT: "+evt.data);
    }
    setTimeout(getNoti, RELOAD_TIME);
}



function addNoti() {
	$('#notifications-count').show();
	$('#notifications-count').text(++notiCount);

	let noti = document.createElement('div');
    noti.classList.add('notification');

	let imageWrap = document.createElement('div');
    imageWrap.classList.add('notification-image-wrapper');

    let notiImage = document.createElement('div');
    notiImage.classList.add('notification-image');

    let img = document.createElement('img');
    img.setAttribute('src','ava.png');

    notiImage.appendChild(img);
    imageWrap.appendChild(notiImage);
    noti.appendChild(imageWrap);

    let notiText = document.createElement('div');
    notiText.classList.add('notification-text');

    let text = document.createElement('span');
    text.classList.add('highlight');
    text.innerHTML = 'HIGHLIGHT';

    notiText.appendChild(text);
    notiText.innerHTML += "NORMAL TEXT"
    noti.appendChild(notiText);

    document.getElementsByClassName('dropdown-body')[0].appendChild(noti);
}



