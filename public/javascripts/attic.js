/*	function scrollPager(direction) {
        	}
        	function initPager() {
        		var pager = document.getElementById("pager");
        		// Left <<
        		var newliLeft = document.createElement('li');
        		var newaLeft = document.createElement('a');
        		newaLeft.setAttribute('onclick','pagerOnClickLeft()');
        		newaLeft.setAttribute('id','pagerLeft');
        		newliLeft.setAttribute('class', 'disabled');
        		newaLeft.innerHTML="&laquo;";
        		pager.appendChild(newliLeft);
        		newliLeft.appendChild(newaLeft);
        		// buttons
        		var nbButtons = Math.min(lastPage, maxPagerSize);
        		for (var i = 0; i < nbButtons;i++) {
        			var newli = document.createElement('li');
        			var newa = document.createElement('a');
        			newli.appendChild(newa);
        			pager.appendChild(newli);
        		}
        		
        		// Right >>
				var newliRight = document.createElement('li');
        		var newaRight = document.createElement('a');
        		newaRight.setAttribute('onclick','pagerOnClickRight()');
        		newaRight.setAttribute('id','pagerRight');
        		newaRight.innerHTML="&raquo;";
        		pager.appendChild(newliRight);
        		newliRight.appendChild(newaRight);
        	}
        	
        	function fillPager() {    		
				var remainingSpace = maxPagerSize - 1;
        		// how much space I can add left
        		var canFillLeft = Math.min(Math.ceil(remainingSpace/2), currentPage - 1);
       			var canFillRight = lastPage - currentPage;
      			var leftBonus = 0;
       			var rightBonus = 0;
       			var fillLeft = 0;
       			if (canFillLeft > Math.ceil(remainingSpace/2)) {
       				leftBonus = canFillLeft - Math.ceil(remainingSpace/2);
       				fillLeft = Math.ceil(remainingSpace/2);
       			} else {
       				fillLeft = canFillLeft;
       			}
       			remainingSpace -= fillLeft;
       			if (canFillRight > Math.floor(remainingSpace/2)) {
       				rightBonus = canFillRight - Math.floor(remainingSpace/2);
       				fillRight = Math.floor(remainingSpace/2);
       			} else {
       				fillRight = canFillRight;
       			}
        		remainingSpace -= fillRight;
        		if (remainingSpace != 0) {
       				if (leftBonus > 0) {
       					var buf = fillLeft;
       					fillLeft += Math.min(leftBonus, remainingSpace);
       					remainingSpace += buf - fillLeft; 
       				}
       				if (rightBonus > 0) {
       					var buf = fillRight;
       					fillRight += Math.min(rightBonus, remainingSpace);
       					remainingSpace += buf - fillRight; 
       				}
       			}
        		console.log("building pager with " + lastPage + " pages");
           		console.log("building pager with left : " + fillLeft + " right: " + fillRight);
        		
        		var indexLeft = currentPage - fillLeft;
        		var indexRight = currentPage + fillRight;
        		// Add current page       		
        		
        		var pager = document.getElementById("pager");
				
				if (pager == null) {console.log("pager not found");}
				var node = document.getElementById("pagerLeft");
				if (node == null) {console.log("node is null before loop");} 
				console.log("before loop: " + indexLeft +  "," + indexRight);
        		for (var i = indexLeft; i <= indexRight; i++) {
        			node = pager.childNodes[i - indexLeft];
        			if (node == null) {console.log("node is null for i = " + i);}
        			console.log("In add button loop " + i);
        			//console.log("before inserting " + i - indexLeft + 1);
        			node.innerHTML = i;
        			node.setAttribute('onclick', 'onClickPager(' + i - indexLeft + 1 + ');');
        			// put currentpage as selected //node.setAttribute('', '');
				}
			}
        	function redrawPager() {
        		// Try to keep current page in the center
        	}*/