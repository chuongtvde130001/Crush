function contactClick(con) {
    let fid = $(con).find(".fid")[0].innerText;
    if (curChatActive == fid) {
        return;
    }
    let name = $(con).find(".name")[0].innerText;
    let avatar = $(con).find(".avatar")[0].src;

    $("#findCrush").hide(400);
    $("#crushOnMe").hide(400);
    $("#chat").show(200);
    $(".messages").show(200);
    $(".message-input").show(200);

    //Click Contact Process
    let current = $(header).find(".active");
    if (current[0] != null)
        current[0].className = current[0].className.replace(" active", "");
    con.className += " active";

    //Update Chat Info Header
    $("#chat-info-img").attr("src", avatar);
    $("#chat-info-usr-name").text(name);
    //Show Corresponding Chat Content
    $("#chat-box-" + fid).show();
    $("#chat-box-" + curChatActive).hide();
    curChatActive = fid;

    messageArea.scrollTop = messageArea.scrollHeight;

    //Update chat engine
    reconfigChat(fid);
    $("#ct_last_" + fid).addClass('read').removeClass('unread');
}