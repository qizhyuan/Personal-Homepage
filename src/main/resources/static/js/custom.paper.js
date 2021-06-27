window.onload = function(){
	var s = document.getElementsByTagName("span");
	for (var i = 0; i < s.length; ++i) {
		var str = s[i].innerHTML;
		s[i].innerHTML = str.replace(/Z. Qi/, "<b>Z. Qi</b>");
	}
};

