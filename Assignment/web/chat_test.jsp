<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Chat Demo</title>
    <link rel="stylesheet" href="css/chat-test.css" />
</head>
<body>
<noscript>
    <h2>Sorry! Your browser doesn't support Javascript</h2>
</noscript>

<%--<div id="userA-page">--%>
<%--    <div class="userA-page-container">--%>
<%--        <h1 class="title">Type your userA</h1>--%>
<%--        <form id="usernameForm" name="usernameForm">--%>
<%--            <div class="form-group">--%>
<%--                <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <button type="submit" class="accent userA-submit">Start Chatting</button>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>

<div id="chat-page">
    <div class="chat-container">
        <div class="chat-header">
            <h2></h2>
        </div>
        <ul id="messageArea">
        </ul>
        <div class="form-group">
            <div class="input-group clearfix">
                <form id="messageForm" onsubmit="function x(evt) {
                  evt.preventDefault();
                }" name="messageForm" nameForm="messageForm">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">Send</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/chat.js"></script>
</body>
</html>