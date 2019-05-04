var t0Partial = null;
var t0fullTime = null;
var min = null;
var max = null;
var methodName = null;
var windowUrl = null;
var i = 0;

function savePartialResult(diff) {
    if (min == null || max == null) {
        min = diff;
        max = diff;
    } else if (min > diff) {
        min = diff;
    } else if (max < diff) {
        max = diff;
    }
}
function sendMessage(socket) {
    t0Partial = performance.now();
    var message = JSON.stringify({
        author : "bot",
        content: "bot",
        sendingTime: "2019-03-20T10:14:36.103Z"
    });
    socket.send(message);
}

function sendMessageIfNecessary(socket, repeatAmmount) {
    if(i < repeatAmmount){
        sendMessage(socket);
    }else{
        socket.close();
        var t1fullTime = performance.now();
        var data = {
            "method": methodName,
            "fullTime": t1fullTime - t0fullTime,
            "min": min,
            "max": max
        };
        postMessage(data);
    }
}

self.onmessage = function (event) {
    var baseUrl = event.data[0];
    var repeatAmmount = event.data[1];
    methodName = event.data[2];
    windowUrl = event.data[3];

    t0fullTime = performance.now();

    var url = new URL(baseUrl, windowUrl);
    url.protocol = url.protocol.replace('http', 'ws');
    const socket = new WebSocket(url.href);

    socket.onopen = function() {
        socket.addEventListener('message', function (event) {
            if (t0Partial != null) {
                var t1Partial = performance.now();
                savePartialResult(t1Partial - t0Partial);
                i++;
                sendMessageIfNecessary(socket, repeatAmmount);
            }
        });
    }


};