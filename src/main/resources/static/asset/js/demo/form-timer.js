function startTimer(duration, display) 
	{
	    var timer = duration, minutes, seconds;
	    var counter = 0;
	    
	    setInterval(function () {
	        minutes = parseInt(timer / 60, 10)
	        seconds = parseInt(timer % 60, 10);
	        
	        minutes = minutes < 10 ? "0" + minutes : minutes;
	        seconds = seconds < 10 ? "0" + seconds : seconds;
	        
	        display.textContent = minutes + ":" + seconds;
	        timer = parseInt(timer);
	        if (--timer < 0) {			
	            timer = duration;
				alert("Test Completed, Thank You");
				document.getElementById("timeALL").value = 0;
				//window.location.href = "http://localhost:9091";
	        }
	        else{
	        	counter++
	    	    document.getElementById("limitTime").value = duration-counter;
	        }
	    }, 1000);
	}