var lastMessageId = null;
var min = null;
var max = null;

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

self.onmessage = function (event) {
    var url = event.data[0];
    var repeatAmmount = event.data[1];
    var methodName = event.data[2];

    lastMessageId = null;
    min = null;
    max = null;

    var t0fullTime = performance.now();
    for (var i = 0; i < repeatAmmount; i++) {
        var t0Partial = performance.now();
        post(url, {
            author: "bot",
            content: "bot",
            sendingTime: "2019-03-20T10:14:36.103Z"
        });
        getNewMessages(url);
        var t1Partial = performance.now();
        savePartialResult(t1Partial - t0Partial);
    }
    var t1fullTime = performance.now();
    var data = {
        "method": methodName,
        "fullTime": t1fullTime - t0fullTime,
        "min": min,
        "max": max
    };
    postMessage(data);
};

function post(url, data) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, false);
    xhr.setRequestHeader("Content-Type", "application/json");
    var json = JSON.stringify(data);
    xhr.send(json);
}

var getJSON = function (url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, false);
    xhr.responseType = 'json';
    xhr.onload = function () {
        var status = xhr.status;
        if (status === 200) {
            callback(null, xhr.response);
        } else {
            callback(status, xhr.response);
        }
    };
    xhr.send();
};

function getNewMessages(url) {
    var adress = lastMessageId == null ? url : url + "?lastMessageId=" + lastMessageId;
    getJSON(adress,
        function (err, data) {
            if (err !== null) {
                alert('Something went wrong: ' + err);
            } else {
                data.forEach(function (entry) {
                    lastMessageId = entry.id;
                });
            }
        });
}