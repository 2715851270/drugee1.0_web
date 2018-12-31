
/* 设置corpName */
function setChangeTitleAndCorpName(){
	// js控制title变更
	document.title    = setTitle;
	// js控制corpName内容
	document.getElementById('corpName').innerHTML = setCorpName;
}

function clearBackImg(){
	if(clearBkimg){
		// 清除背景图文字
		var backImg   = document.getElementById('container_top_img');
		backImg.style.backgroundImage = "url('/server/ui/image/container_top_clear.jpg')";
	}
}

function setFootCopyRight (){
	var footCopyRight = document.getElementById('container_bottom_content');
	footCopyRight.innerHTML = copyRight;
}

function setCoryRightWithPageDown(){
	var pageDownCopyRight = document.getElementById('copyRight');
	pageDownCopyRight.innerHTML = downCopyRight;
}

function clearLogo(){
	var logo = document.getElementById('logo');
	logo.style.display = 'none';
}

function clearServerTel(){
	var serverTel = document.getElementById('servertel');
	serverTel.style.display = 'none';
}


function init(){
	switch(type){
		case 'download':
			document.title    = setTitle;
			setCoryRightWithPageDown();
			clearLogo();
			break;
		case 'serverlogin':
			setChangeTitleAndCorpName();
			clearBackImg();
			setFootCopyRight();
			break;
		case 'serverManage':
			clearServerTel();
			break;
	}
}

init();