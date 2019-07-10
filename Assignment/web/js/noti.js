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
        console.log(evt.data);
        let re = JSON.parse(evt.data);
        if(re.friend!=null){
            let friend = re.friend;
            for(let i=0;i<friend.length;i++){
                //add friend to chat engine
                friend[i]['fid']=re.fid[i];
                fid_usr_list[re.fid[i]] = [friend[i].uid, friend[i].avatar];
                addFriendToSide(friend[i]);
                obj={'avatar':friend[i].avatar,'head':friend[i].fullName,'content':' and you have becomed friend.'}
                addNoti(obj,1)

            }
        }
        if(re.crush!=null){
            re.crush.forEach(function (item) {
                addCrushOnMe(item);
                obj={'avatar':item.avatar,'head':item.fullName,'content':' has crushed you.'}
                addNoti(obj,2);
            })
        }
        nSocket.close();
    }
    setTimeout(getNoti, RELOAD_TIME);
}


// 1 is friend, 2 is crush
function addNoti(obj,type) {
	$('#notifications-count').show();
	$('#notifications-count').text(++notiCount);

	let noti = document.createElement('div');
    noti.classList.add('notification');
    noti.setAttribute('onclick',(type==2)?'showWhoCrush()':"$('#notification-dropdown').hide(200);");

	let imageWrap = document.createElement('div');
    imageWrap.classList.add('notification-image-wrapper');

    let notiImage = document.createElement('div');
    notiImage.classList.add('notification-image');

    let img = document.createElement('img');
    img.setAttribute('src',obj.avatar);

    notiImage.appendChild(img);
    imageWrap.appendChild(notiImage);
    noti.appendChild(imageWrap);

    let notiText = document.createElement('div');
    notiText.classList.add('notification-text');

    let text = document.createElement('span');
    text.classList.add('highlight');
    text.innerHTML = obj.head+" ";

    notiText.appendChild(text);
    notiText.innerHTML += obj.content;
    noti.appendChild(notiText);

    document.getElementsByClassName('dropdown-body')[0].prepend(noti);
}

function showWhoCrush() {
    $("#crushOnMe").show(200);
    $("#findCrush").hide(400);
    $("#chat").hide(400);
    $('#notification-dropdown').hide(200);
    disableChat();
}

function addCrushOnMe(obj) {
    $('.list-group').append(
        '<li id="list-group-item-'+obj.uid+'" class="list-group-item">\n' +
        '        <div class="row">\n' +
        '        <div class="col-2">\n' +
        '        <img src="'+obj.avatar+'" style="width: 50px; height: 50px;">\n' +
        '        </div>\n' +
        '        <div class="col-3">\n' +
        '        '+obj.fullName+'\n' +
        '        </div>\n' +
        '        <div class="col-2">\n' +
        '        '+obj.gender+'\n' +
        '        </div>\n' +
        '        <div class="col-1">\n' +
        '        '+obj.age+'\n' +
        '        </div>\n' +
        '        <div class="col-4 text-right text-light">\n' +
        '        <a onclick="crush('+uid+','+obj.uid+');\n' +
        '    remove('+obj.uid+')" class="btn profile-card__buttonMenu button--orange">Crush</a>\n' +
        '    <a onclick="uncrush('+obj.uid+','+uid+');\n' +
        '    remove('+obj.uid+')" class="btn profile-card__buttonMenu button--gray text-dark">Remove</a>\n' +
        '    </div>\n' +
        '    </div>\n' +
        '    </li>'
    )
}

function addFriendToSide(obj) {
    $('#contacts ul').append(
    '<li class="contact">\n' +
        '            <div class="wrap">\n' +
        '            <span class="contact-status online"></span>\n' +
        '            <img class="avatar" src="'+obj.avatar+'"/>\n' +
        '            <div class="meta">\n' +
        '            <p class="name">'+obj.fullName+'</p>\n' +
        '            <p class="read" id="ct_last_'+obj.fid+'">⠀</p>\n' +
        '        </div>\n' +
        '        <p class="fid" hidden>'+obj.fid+'</p>\n' +
        '        </div>\n' +
        '    </li>'
    )
    $('.messages').append('<ul id="chat-box-'+obj.fid+'" style="display: none"></ul>');
    $($("#contacts")).find(".contact").last().on("click",function(){
        contactClick(this);
    });
}


