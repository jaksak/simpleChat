myId = prompt("Set your nick: ", "jak");
lastMessageId = null;

function addMessage(userId, content, time){
	if(myId == userId){
		addOwnMessage(content, time);
	}else{
		addOtherMessage(userId, content, time);
	}
}

function addOwnMessage(content, time){
	var messageBox = document.getElementsByClassName("ChatLog")[0];
	messageBox.innerHTML += `  <li class="ChatLog__entry ChatLog__entry_mine">
    <img class="ChatLog__avatar" src="./img/av/2.png" />
    <p class="ChatLog__message">
      `+content+`
      <time class="ChatLog__timestamp">`+time+`</time>
    </p>
  </li>`;
}

function addOtherMessage(userId, content, time){
	var messageBox = document.getElementsByClassName("ChatLog")[0];
	messageBox.innerHTML += `<li class="ChatLog__entry">
	<div class="tooltip">
    <img class="ChatLog__avatar" src="./img/av/`+getImgFromId(userId)+`.png" />
	<span class="tooltiptext">`+userId+`</span>
	</div>
    <p class="ChatLog__message">
      `+content+`
      <time class="ChatLog__timestamp">`+time+`</time>
    </p>
  </li>`;
}

idToImgMap = new Map();

function getImgFromId(id){
	var img = idToImgMap.get(id);
	
	if(img == undefined){
		img = Math.floor(Math.random() * 82);
		idToImgMap.set(id, img);
	}
	return img;
}

window.sendMessageFunction = null;

document.addEventListener("DOMContentLoaded", function(){
	var textArea = document.getElementsByTagName("textarea")[0];
	
	textArea.addEventListener('keydown', function (e){
		if(e.key == "Enter"){
			var content = textArea.value;
			window.sendMessageFunction(myId, content)
			textArea.value = null;
		}
	}, false);
});